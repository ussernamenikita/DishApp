package com.dish.app.logger

import android.util.Log

class LoggerImpl : Logger {
    override fun log(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun log(tag: String, message: String, error: Throwable) {
        Log.e(tag, null, error)
    }
}