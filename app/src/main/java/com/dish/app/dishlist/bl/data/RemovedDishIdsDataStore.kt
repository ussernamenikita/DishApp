package com.dish.app.dishlist.bl.data

interface RemovedDishIdsDataStore {
    suspend fun getRemovedDishIds(): Set<String>
    suspend fun addToRemoved(dishIds: Set<String>): Set<String>
}