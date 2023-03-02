package com.dish.app.dishlist.bl.data

import com.dish.app.dishlist.bl.domain.models.DishDescriptionDomainModel

interface DishesRepository {
    suspend fun getAllDishes(): List<DishDescriptionDomainModel>
    suspend fun getRemovedDishIds(): Set<String>
    suspend fun addToRemoved(dishIds: Set<String>): Set<String>
}