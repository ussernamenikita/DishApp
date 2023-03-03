package com.dish.app.dishlist.bl.domain

interface DishReducer {

    fun reduce(currentState: DishListState, message: Message): DishListState
}