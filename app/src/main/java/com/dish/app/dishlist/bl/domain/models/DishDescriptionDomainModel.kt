package com.dish.app.dishlist.bl.domain.models

class DishDescriptionDomainModel(
    val id: String,
    val name: String,
    val description: String?,
    val image: String?,
    val price: Int,
    val restaurantLogo: String?,
)
