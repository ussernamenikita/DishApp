package com.dish.app.dishlist.bl.data

import com.dish.app.dishlist.bl.data.mapper.DishEntityToDescriptionDomainModelMapper
import com.dish.app.dishlist.bl.domain.DishesRepository
import com.dish.app.dishlist.bl.domain.models.DishDescriptionDomainModel

class DishesRepositoryImpl(
    private val dishDataStore: DishDataStore,
    private val removedDishIdsDataStore: RemovedDishIdsDataStore,
    private val entityToDomainModelMapper: DishEntityToDescriptionDomainModelMapper,
) : DishesRepository {

    companion object {
        private var FAIL_REQUEST_STEP: Int = 3
    }

    private var requestCounter: Int = 0

    override suspend fun getAllDishes(): List<DishDescriptionDomainModel> {
        requestCounter++
        if (requestCounter % FAIL_REQUEST_STEP == 0) {
            error("Mocked internet exception")
        }
        return dishDataStore
            .getDishes()
            .map(entityToDomainModelMapper::map)
    }

    override suspend fun getRemovedDishIds(): Set<String> {
        return removedDishIdsDataStore.getRemovedDishIds()
    }

    override suspend fun addToRemoved(dishIds: Set<String>): Set<String> {
        return removedDishIdsDataStore.addToRemoved(dishIds)
    }

}