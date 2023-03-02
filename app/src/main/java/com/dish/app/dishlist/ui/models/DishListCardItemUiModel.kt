package com.dish.app.dishlist.ui.models

data class DishListCardItemUiModel(
    val id: String,
    val name: String,
    val description: String?,
    val dishImageLink: String?,
    val restaurantLogoLink: String?,
    val price: String,
    val isChecked: Boolean,
) {
    var onCheckBoxClicked: ((isSelected: Boolean) -> Unit)? = null
}