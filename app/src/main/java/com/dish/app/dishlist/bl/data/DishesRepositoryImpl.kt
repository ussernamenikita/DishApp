package com.dish.app.dishlist.bl.data

import com.dish.app.dishlist.bl.data.mapper.DishEntityToDescriptionDomainModelMapper
import com.dish.app.dishlist.bl.domain.models.DishDescriptionDomainModel

class DishesRepositoryImpl(
    private val dishDataStore: DishDataStore,
    private val removedDishIdsDataStore: RemovedDishIdsDataStore,
    private val entityToDomainModelMapper: DishEntityToDescriptionDomainModelMapper,
) : DishesRepository {

    override suspend fun getAllDishes(): List<DishDescriptionDomainModel> {
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