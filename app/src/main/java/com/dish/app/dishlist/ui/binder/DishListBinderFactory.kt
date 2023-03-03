package com.dish.app.dishlist.ui.binder

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dish.app.dishlist.ui.di.Injector

class DishListBinderFactory(context: Context) : ViewModelProvider.Factory {

    private val appContext = context.applicationContext

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Injector.createViewModel(appContext) as T
    }
}