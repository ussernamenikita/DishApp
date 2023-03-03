package com.dish.app.dishlist.bl.domain

import com.dish.app.dishlist.bl.domain.models.DishDomainModel

sealed interface Message {
    object Loading : Message
    object ClearSelected : Message
    object RemovingInProgress : Message

    class UpdateRemovedItems(val newRemoved: Set<String>) : Message
    class SelectDish(val dishId: String, val selected: Boolean) : Message
    class UpdateAllDishes(val dishesDomainModels: List<DishDomainModel>) : Message
    class Error(val error: Throwable) : Message
}

sealed interface Intent {
    class Select(val id: String) : Intent
    class UnSelect(val id: String) : Intent
    object Delete : Intent
    object RetryLoading : Intent
}

data class DishListState(
    val dishesList: List<DishDomainModel>? = null,
    val error: Throwable? = null,
    val isLoading: Boolean = true,
    val selectedDishIds: Set<String> = emptySet(),
    val removingInProgress: Boolean = false,
)