package com.dish.app.dishlist.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dish.app.dishlist.bl.domain.DishListStore
import com.dish.app.dishlist.bl.domain.Intent
import com.dish.app.logger.LoggerExceptionHandler
import com.dish.app.dishlist.ui.mappers.StateToModelMapper
import com.dish.app.dishlist.ui.models.DishListUiModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

typealias Model = DishListUiModel

class DishListViewModel(
    private val dishListStore: DishListStore,
    private val stateToModelMapper: StateToModelMapper,
) : ViewModel() {

    companion object {
        private const val LOG_TAG = "DishListViewModel"
    }

    private val mutableModels = MutableLiveData(Model())
    private val vmScopeErrorHandled = viewModelScope + LoggerExceptionHandler(LOG_TAG)
    private val subscription: Job = vmScopeErrorHandled.launch {
        dishListStore
            .getState()
            .collect {
                mutableModels.postValue(stateToModelMapper.map(it, ::onIntent))
            }
    }

    val models: LiveData<Model> = mutableModels

    fun onIntent(intent: Intent) {
        vmScopeErrorHandled.launch {
            dishListStore.process(intent)
        }
    }

    override fun onCleared() {
        super.onCleared()
        dishListStore.dispose()
        subscription.cancel()
    }
}