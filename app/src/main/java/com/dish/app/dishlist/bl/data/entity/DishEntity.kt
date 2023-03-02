package com.dish.app.dishlist.bl.data.entity

data class DishEntity(
    val id: String,
    val name: String,
    val description: String?,
    val image: String?,
    val price: Int,
    val restaurantLogo: String?,
)