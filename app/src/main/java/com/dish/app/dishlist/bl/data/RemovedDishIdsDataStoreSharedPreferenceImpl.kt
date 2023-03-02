package com.dish.app.dishlist.bl.data

import android.content.Context
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RemovedDishIdsDataStoreSharedPreferenceImpl(context: Context) : RemovedDishIdsDataStore {

    companion object {
        private const val REMOVED_DISH_IDS_SHARED_PREFERENCES_KEY = "RemovedDishIds"
    }

    private val appContext = context.applicationContext
    private val sharedPreferences = appContext.getSharedPreferences(
        REMOVED_DISH_IDS_SHARED_PREFERENCES_KEY,
        Context.MODE_PRIVATE
    )
    private val mutex = Mutex()

    override suspend fun getRemovedDishIds(): Set<String> {
        return getRemovedIdsAsSet()
    }

    override suspend fun addToRemoved(dishIds: Set<String>): Set<String> {
        val resultSet = getRemovedIdsAsSet()
        resultSet.addAll(dishIds)
        saveRemovedIds(resultSet)
        return resultSet
    }

    private suspend fun saveRemovedIds(resultSet: Set<String>) {
        mutex.withLock {
            sharedPreferences
                .edit()
                .putStringSet(REMOVED_DISH_IDS_SHARED_PREFERENCES_KEY, resultSet)
                .commit()
        }
    }

    private fun getRemovedIdsAsSet(): MutableSet<String> {
        return sharedPreferences
            .getStringSet(REMOVED_DISH_IDS_SHARED_PREFERENCES_KEY, emptySet())!!
            .toMutableSet()
    }

}