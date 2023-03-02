package com.dish.app.dishlist.bl.domain

import com.dish.app.dishlist.bl.data.DishesRepository
import com.dish.app.dishlist.bl.domain.mappers.DishDescriptionToDishDomainModelMapper
import com.dish.app.logger.Logger
import com.dish.app.logger.LoggerExceptionHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO add Mvi-kotlin framework
class DishListStoreImpl constructor(
    private val dishesRepository: DishesRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
    private val reducer: DishReducer,
    private val dishDescriptionToDishDomainModelMapper: DishDescriptionToDishDomainModelMapper,
    private val loggger: Logger,
) : DishListStore {

    companion object {
        private const val LOG_TAG = "DishListStore"
    }

    private val states = MutableStateFlow(DishListState())
    private var state: DishListState
        get() {
            return states.value
        }
        set(value) {
            states.value = value
        }

    private val exceptionHandler = LoggerExceptionHandler(LOG_TAG)
    private val scope = CoroutineScope(mainDispatcher + SupervisorJob() + exceptionHandler)

    init {
        scope.launch {
            requestAllDishes()
        }
    }

    override suspend fun process(intent: Intent) {
        when (intent) {
            Intent.Delete -> delete()
            is Intent.Select -> select(intent.id)
            is Intent.UnSelect -> unSelect(intent.id)
            Intent.RetryLoading -> requestAllDishes()
        }
    }

    override fun getState(): Flow<DishListState> {
        return states.asStateFlow()
    }

    override fun dispose() {
        scope.cancel()
    }

    private suspend fun requestAllDishes() {
        execute {
            dispatch(DishListStore.Message.Loading)
            val allDishesAsync = async {
                kotlin.runCatching { dishesRepository.getAllDishes() }
            }
            val removedDishesAsync = async {
                kotlin.runCatching { dishesRepository.getRemovedDishIds() }
            }

            val allDishes = allDishesAsync.await().getOrElse {
                loggger.log(LOG_TAG, "Error while trying to get dish list", it)
                dispatch(DishListStore.Message.Error(it))
                return@execute
            }
            val removedDishes = removedDishesAsync.await().getOrElse {
                loggger.log(LOG_TAG, "Error while trying to get removed dish list", it)
                dispatch(DishListStore.Message.Error(it))
                return@execute
            }

            val dishesDomainModels = allDishes.map {
                dishDescriptionToDishDomainModelMapper.map(it, removedDishes)
            }
            dispatch(DishListStore.Message.UpdateAllDishes(dishesDomainModels))
        }
    }

    private suspend fun delete() {
        execute {
            dispatch(DishListStore.Message.RemovingInProgress)
            dishesRepository.addToRemoved(state.selectedDishIds)
            val newRemoved = dishesRepository.getRemovedDishIds()
            dispatch(DishListStore.Message.ClearSelected)
            dispatch(DishListStore.Message.UpdateRemovedItems(newRemoved))
        }
    }

    private suspend fun select(dishId: String) {
        dispatch(DishListStore.Message.SelectDish(dishId = dishId, selected = true))
    }

    private suspend fun unSelect(dishId: String) {
        dispatch(DishListStore.Message.SelectDish(dishId = dishId, selected = false))
    }

    private suspend fun dispatch(message: DishListStore.Message) {
        withContext(mainDispatcher) {
            state = reducer.reduce(state, message)
        }
    }

    private suspend fun <T> execute(block: suspend CoroutineScope.() -> T): T {
        return withContext(ioDispatcher, block)
    }
}