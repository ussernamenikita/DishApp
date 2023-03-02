package com.dish.app.dishlist.ui.di

import android.content.Context
import com.dish.app.dishlist.bl.data.DishesRepository
import com.dish.app.dishlist.bl.data.DishesRepositoryImpl
import com.dish.app.dishlist.bl.data.MockedDishDataStore
import com.dish.app.dishlist.bl.data.RemovedDishIdsDataStoreSharedPreferenceImpl
import com.dish.app.dishlist.bl.data.mapper.DishEntityToDescriptionDomainModelMapper
import com.dish.app.dishlist.bl.domain.DishListStore
import com.dish.app.dishlist.bl.domain.DishListStoreImpl
import com.dish.app.dishlist.bl.domain.DishReducerImpl
import com.dish.app.dishlist.bl.domain.mappers.DishDescriptionToDishDomainModelMapper
import com.dish.app.logger.LoggerImpl
import com.dish.app.dishlist.ui.mappers.StateToModelMapper
import com.dish.app.dishlist.ui.viewmodel.DishListViewModel
import kotlinx.coroutines.Dispatchers

// TODO replace to some di framework
object Injector {

    fun createViewModel(context: Context): DishListViewModel {
        return DishListViewModel(
            dishListStore = createDishListStore(context),
            stateToModelMapper = StateToModelMapper(),
        )
    }

    private fun createDishListStore(context: Context): DishListStore {
        return DishListStoreImpl(
            dishesRepository = createDishesRepository(context),
            ioDispatcher = Dispatchers.IO,
            mainDispatcher = Dispatchers.Main.immediate,
            reducer = DishReducerImpl(),
            dishDescriptionToDishDomainModelMapper = DishDescriptionToDishDomainModelMapper(),
            loggger = LoggerImpl()
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