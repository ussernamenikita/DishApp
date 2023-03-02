package com.dish.app.dishlist.bl.data.mapper

import com.dish.app.dishlist.bl.data.entity.DishEntity
import com.dish.app.dishlist.bl.domain.models.DishDescriptionDomainModel

class DishEntityToDescriptionDomainModelMapper {

    fun map(entity: DishEntity): DishDescriptionDomainModel {
        return DishDescriptionDomainModel(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            image = entity.image,
            price = entity.price,
            restaurantLogo = entity.restaurantLogo,
        )
    }
}