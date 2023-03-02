package com.dish.app.dishlist.bl.domain.models

data class DishDomainModel(
    val id: String,
    val isRemoved: Boolean,
    val dishDescription: DishDescriptionDomainModel,
)