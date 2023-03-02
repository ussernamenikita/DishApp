package com.dish.app.dishlist.bl.domain

import com.dish.app.dishlist.bl.domain.models.DishDomainModel

interface SelectDishUseCase {
    suspend fun select(dish: DishDomainModel)
}