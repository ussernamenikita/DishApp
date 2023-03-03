package com.dish.app.dishlist.ui.view

import androidx.lifecycle.LiveData
import com.dish.app.dishlist.bl.domain.Intent
import com.dish.app.dishlist.ui.models.DishListUiModel

interface DishListFragmentView {
    fun render(dishListModel: DishListUiModel)

    val intents: LiveData<Intent?>
}