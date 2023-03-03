package com.dish.app.dishlist.ui.items

import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.core.view.updateLayoutParams
import com.dish.app.R
import com.dish.app.databinding.DishListEmptyItemLayoutBinding
import com.dish.app.uikit.extensions.context
import com.xwray.groupie.viewbinding.BindableItem

class EmptyItem(
    @DimenRes private val itemHeight: Int,
) : BindableItem<DishListEmptyItemLayoutBinding>() {
    override fun bind(viewBinding: DishListEmptyItemLayoutBinding, position: Int) {
        viewBinding.root.updateLayoutParams<ViewGroup.LayoutParams> {
            height = viewBinding.context.resources.getDimensionPixelSize(itemHeight)
        }
    }

    override fun getLayout(): Int = R.layout.dish_list_empty_item_layout

    override fun initializeViewBinding(view: View): DishListEmptyItemLayoutBinding {
        return DishListEmptyItemLayoutBinding.bind(view)
    }
}