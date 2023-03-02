package com.dish.app.dishlist.bl.domain

import com.dish.app.dishlist.bl.domain.models.DishDomainModel
import kotlinx.coroutines.flow.Flow

interface DishListStore {
    suspend fun process(intent: Intent)
    fun getState(): Flow<DishListState>
    fun dispose()

    sealed interface Message {
        object Loading : Message
        object ClearSelected : Message
        object RemovingInProgress : Message

        class UpdateRemovedItems(val newRemoved: Set<String>) : Message
        class SelectDish(val dishId: String, val selected: Boolean) : Message
        class UpdateAllDishes(val dishesDomainModels: List<DishDomainModel>) : Message
        class Error(val error: Throwable) : Message
    }
}