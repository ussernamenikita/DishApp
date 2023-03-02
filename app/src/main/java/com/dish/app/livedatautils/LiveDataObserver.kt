package com.dish.app.livedatautils

import androidx.lifecycle.Observer

class LiveDataObserver<T>(val callback: (T) -> Unit) : Observer<T> {
    override fun onChanged(t: T) {
        callback(t)
    }
}