package com.dish.app.dishlist.ui.mappers

import com.dish.app.dishlist.bl.domain.DishListState
import com.dish.app.dishlist.bl.domain.Intent
import com.dish.app.dishlist.ui.items.DishListCardItem
import com.dish.app.dishlist.ui.models.DishListCardItemUiModel
import com.dish.app.dishlist.ui.models.DishListUiModel
import com.dish.app.dishlist.ui.viewmodel.Model
import com.xwray.groupie.Item

class StateToModelMapper {

    companion object {
        private const val PRICE_GROUPS_SEPARATOR = " "
    }

    fun map(state: DishListState, onIntent: (Intent) -> Unit): Model {
        return DishListUiModel(
            items = getItems(state, onIntent),
            isLoading = state.isLoading,
            isError = state.error != null,
        )
    }

    private fun getItems(state: DishListState, onIntent: (Intent) -> Unit): List<Item<*>>? {
        return state.dishesList?.map { dishModel ->
            val uiModel = DishListCardItemUiModel(
                id = dishModel.id,
                name = dishModel.dishDescription.name,
                description = dishModel.dishDescription.description,
                dishImageLink = dishModel.dishDescription.image,
                restaurantLogoLink = dishModel.dishDescription.restaurantLogo,
                price = "${dishModel.dishDescription.price.asPrice()} ₽",
                isChecked = state.selectedDishIds.contains(dishModel.id),
            )
            uiModel.onCheckBoxClicked = { isSelected ->
                if (isSelected) {
                    onIntent.invoke(Intent.Select(dishModel.id))
                } else {
                    onIntent.invoke(Intent.UnSelect(dishModel.id))
                }
            }
            DishListCardItem(uiModel)
        }
    }


    private fun Int.asPrice(): CharSequence {
        return "${
            toString().splitBySymbolGroups(
                groupSize = 3,
                groupSeparator = PRICE_GROUPS_SEPARATOR,
            )
        }"
    }

    private fun CharSequence.splitBySymbolGroups(
        groupSize: Int,
        groupSeparator: String = " ",
    ): CharSequence {
        if (length <= groupSize) return this
        val builder = StringBuilder(this)

        var index = builder.lastIndex
        var count = 1
        while (index > 0) {
            if (count % groupSize == 0) {
                builder.insert(index, groupSeparator)
            }
            count++
            index--
        }
        return builder
    }

}