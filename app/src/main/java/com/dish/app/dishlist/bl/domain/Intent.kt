package com.dish.app.dishlist.bl.domain

sealed interface Intent {
    class Select(val id: String) : Intent
    class UnSelect(val id: String) : Intent
    object Delete : Intent
    object RetryLoading : Intent
}
