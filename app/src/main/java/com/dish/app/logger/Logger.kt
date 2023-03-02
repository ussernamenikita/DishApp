package com.dish.app.logger

interface Logger {
    fun log(tag: String, message: String)
    fun log(tag: String, message: String, error: Throwable)
}