package com.dish.app.dishlist.bl.domain.mappers

import com.dish.app.dishlist.bl.domain.models.DishDescriptionDomainModel
import com.dish.app.dishlist.bl.domain.models.DishDomainModel

class DishDescriptionToDishDomainModelMapper {

    fun map(
        description: DishDescriptionDomainModel,
        removedDishIds: Set<String>,
    ): DishDomainModel {
        return DishDomainModel(
            id = description.id,
            isRemoved = removedDishIds.contains(description.id),
            dishDescription = description,
        )
    }
}