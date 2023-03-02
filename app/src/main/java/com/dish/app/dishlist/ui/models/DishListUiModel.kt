package com.dish.app.dishlist.ui.models

import com.xwray.groupie.Item

class DishListUiModel(
    val items: List<Item<*>>? = null,
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val showDeleteButton: Boolean = false,
)