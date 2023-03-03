package com.dish.app.dishlist.ui.binder

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dish.app.dishlist.bl.domain.DishListExecutor
import com.dish.app.dishlist.bl.domain.Intent
import com.dish.app.dishlist.ui.mappers.StateToModelMapper
import com.dish.app.dishlist.ui.models.DishListUiModel
import com.dish.app.dishlist.ui.view.DishListFragmentView
import com.dish.app.livedatautils.LiveDataObserver
import com.dish.app.logger.LoggerExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

typealias Model = DishListUiModel

class DishListBinder(
    private val dishListStore: DishListExecutor,
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

    private val models: LiveData<Model> = mutableModels

    private var view: DishListFragmentView? = null

    private val modelRenderingObserver = LiveDataObserver<Model> {
        view?.render(it)
    }
    private val intentsObserver = LiveDataObserver<Intent?> { intent ->
        intent?.let(::onIntent)
    }

    fun bind(view: DishListFragmentView, lifecycleOwner: LifecycleOwner) {
        this.view = view
        this.view?.intents?.observe(lifecycleOwner, intentsObserver)
        models.observe(lifecycleOwner, modelRenderingObserver)
    }

    fun unbind() {
        models.removeObserver(modelRenderingObserver)
        view?.intents?.removeObserver(intentsObserver)
        view = null
    }

    private fun onIntent(intent: Intent) {
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