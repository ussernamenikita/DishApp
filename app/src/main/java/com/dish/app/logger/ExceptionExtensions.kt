package com.dish.app.logger

import android.util.Log
import com.dish.app.BuildConfig

fun Throwable.throwIfDebug(tag: String) {
    if (BuildConfig.DEBUG) {
        throw this
    }
    Log.e(tag, null, this)
}