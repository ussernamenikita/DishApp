package com.dish.app.dishlist.bl.domain

class DishReducerImpl : DishReducer {

    override fun reduce(
        currentState: DishListState,
        message: DishListStore.Message,
    ): DishListState {
        return when (message) {
            is DishListStore.Message.UpdateRemovedItems -> updateRemoved(currentState, message)
            is DishListStore.Message.SelectDish -> updateSelected(currentState, message)
            is DishListStore.Message.UpdateAllDishes -> updateAllDishes(currentState, message)
            DishListStore.Message.Loading -> setLoading(currentState)
            is DishListStore.Message.Error -> setError(currentState, message)
        }
    }

    private fun setError(
        currentState: DishListState,
        message: DishListStore.Message.Error,
    ): DishListState {
        return currentState.copy(dishesList = null, error = message.error, isLoading = false)
    }

    private fun setLoading(currentState: DishListState): DishListState {
        return currentState.copy(
            isLoading = true,
            dishesList = null,
            error = null
        )
    }

    private fun updateAllDishes(
        currentState: DishListState,
        message: DishListStore.Message.UpdateAllDishes,
    ): DishListState {
        return currentState.copy(
            dishesList = message.dishesDomainModels,
            error = null,
            isLoading = false,
        )
    }

    private fun updateSelected(
        currentState: DishListState,
        message: DishListStore.Message.SelectDish,
    ): DishListState {
        val newSelected = currentState
            .selectedDishIds
            .toMutableSet()
            .apply {
                if (message.selected) {
                    add(message.dishId)
                } else {
                    remove(message.dishId)
                }
            }
        return currentState.copy(selectedDishIds = newSelected)
    }

    private fun updateRemoved(
        state: DishListState,
        message: DishListStore.Message.UpdateRemovedItems,
    ): DishListState {
        val newRemoved = message.newRemoved
        val newDishesList = state.dishesList?.map {
            if (newRemoved.contains(it.id)) {
                it.copy(isRemoved = true)
            } else {
                it
            }
        }
        return state.copy(dishesList = newDishesList)
    }
}