package com.dish.app.dishlist.ui.items

import android.view.View
import com.bumptech.glide.Glide
import com.dish.app.R
import com.dish.app.databinding.DishListCardViewLayoutBinding
import com.dish.app.dishlist.ui.models.DishListCardItemUiModel
import com.dish.app.uikit.extensions.context
import com.dish.app.uikit.extensions.getDrawable
import com.dish.app.uikit.extensions.getLoadingPlaceholder
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

class DishListCardItem(
    private val dishListItemUiModel: DishListCardItemUiModel,
) : BindableItem<DishListCardViewLayoutBinding>() {

    companion object {
        private val PAYLOAD_STUB = Any()
    }

    override fun bind(viewBinding: DishListCardViewLayoutBinding, position: Int) {
        with(viewBinding) {
            // Чтобы не отправлялись события выставления состояния во время байдинга
            dishSelectedCb.setOnCheckedChangeListener(null)
            dishSelectedCb.isChecked = dishListItemUiModel.isChecked
            dishNameTv.text = dishListItemUiModel.name
            dishPriceTv.text = dishListItemUiModel.price
            loadDishImage()
            loadRestaurantLogo()
            dishDescriptionTv.text = dishListItemUiModel.description
            dishSelectedCb.setOnCheckedChangeListener { _, isChecked ->
                dishListItemUiModel.onCheckBoxClicked?.invoke(isChecked)
            }
        }
    }

    private fun DishListCardViewLayoutBinding.loadRestaurantLogo() {
        Glide
            .with(context)
            .load(dishListItemUiModel.restaurantLogoLink)
            .placeholder(context.getLoadingPlaceholder())
            .error(getDrawable(R.drawable.ic_emoji_food_beverage_48px))
            .into(restaurantLogoIv)
    }

    private fun DishListCardViewLayoutBinding.loadDishImage() {
        Glide
            .with(context)
            .load(dishListItemUiModel.dishImageLink)
            .placeholder(context.getLoadingPlaceholder())
            .error(getDrawable(R.drawable.different_food))
            .into(dishImageIv)
    }

    override fun getLayout(): Int = R.layout.dish_list_card_view_layout

    override fun initializeViewBinding(view: View): DishListCardViewLayoutBinding {
        return DishListCardViewLayoutBinding.bind(view)
    }

    override fun isSameAs(other: Item<*>): Boolean {
        return other is DishListCardItem && other.dishListItemUiModel.id == dishListItemUiModel.id
    }

    override fun hasSameContentAs(other: Item<*>): Boolean {
        return other is DishListCardItem && other.dishListItemUiModel == dishListItemUiModel
    }

    override fun getChangePayload(newItem: Item<*>): Any {
        return PAYLOAD_STUB
    }
}
