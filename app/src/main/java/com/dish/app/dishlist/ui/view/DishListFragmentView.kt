package com.dish.app.dishlist.ui.view

import com.dish.app.dishlist.ui.models.DishListUiModel

interface DishListFragmentView {
    fun render(dishListModel: DishListUiModel)
}