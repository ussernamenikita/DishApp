package com.dish.app.dishlist.bl.domain

import kotlinx.coroutines.flow.Flow

interface DishListExecutor {
    suspend fun process(intent: Intent)
    fun getState(): Flow<DishListState>
    fun dispose()
}