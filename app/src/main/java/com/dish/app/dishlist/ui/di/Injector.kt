package com.dish.app.dishlist.ui.di

import android.content.Context
import com.dish.app.dishlist.bl.domain.DishesRepository
import com.dish.app.dishlist.bl.data.DishesRepositoryImpl
import com.dish.app.dishlist.bl.data.MockedDishDataStore
import com.dish.app.dishlist.bl.data.RemovedDishIdsDataStoreSharedPreferenceImpl
import com.dish.app.dishlist.bl.data.mapper.DishEntityToDescriptionDomainModelMapper
import com.dish.app.dishlist.bl.domain.DishListExecutor
import com.dish.app.dishlist.bl.domain.DishListExecutorImpl
import com.dish.app.dishlist.bl.domain.DishReducerImpl
import com.dish.app.dishlist.bl.domain.mappers.DishDescriptionToDishDomainModelMapper
import com.dish.app.logger.LoggerImpl
import com.dish.app.dishlist.ui.mappers.StateToModelMapper
import com.dish.app.dishlist.ui.binder.DishListBinder
import kotlinx.coroutines.Dispatchers

// TODO replace to some di framework
object Injector {

    fun createViewModel(context: Context): DishListBinder {
        return DishListBinder(
            dishListStore = createDishListStore(context),
            stateToModelMapper = StateToModelMapper(),
        )
    }

    private fun createDishListStore(context: Context): DishListExecutor {
        return DishListExecutorImpl(
            dishesRepository = createDishesRepository(context),
            ioDispatcher = Dispatchers.IO,
            mainDispatcher = Dispatchers.Main.immediate,
            reducer = DishReducerImpl(),
            dishDescriptionToDishDomainModelMapper = DishDescriptionToDishDomainModelMapper(),
            logger = LoggerImpl()
        )
    }

    private fun createDishesRepository(context: Context): DishesRepository {
        return DishesRepositoryImpl(
            dishDataStore = MockedDishDataStore,
            removedDishIdsDataStore = RemovedDishIdsDataStoreSharedPreferenceImpl(context),
            entityToDomainModelMapper = DishEntityToDescriptionDomainModelMapper(),
        )
    }
}