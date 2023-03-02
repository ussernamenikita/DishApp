package com.dish.app.dishlist.bl.domain

import com.dish.app.dishlist.bl.domain.models.DishDomainModel

data class DishListState(
    val dishesList: List<DishDomainModel>? = null,
    val error: Throwable? = null,
    val isLoading: Boolean = true,
    val selectedDishIds: Set<String> = emptySet(),
)