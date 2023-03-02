package com.dish.app.dishlist.ui.view

import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dish.app.R
import com.dish.app.databinding.DishListFragmentLayoutBinding
import com.dish.app.dishlist.bl.domain.Intent
import com.dish.app.dishlist.ui.models.DishListUiModel
import com.dish.app.uikit.extensions.context
import com.dish.app.uikit.skeleton.ViewSkeletonAnimation
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Item

class DishListFragmentViewImpl(
    view: View,
) : DishListFragmentView {

    private val binding: DishListFragmentLayoutBinding = DishListFragmentLayoutBinding.bind(view)

    private val skeletonAnimation = ViewSkeletonAnimation()

    private val _intents: MutableLiveData<Intent?> = MutableLiveData(null)

    val intents: LiveData<Intent?> = _intents

    init {
        with(binding.dishListRv) {
            adapter = GroupieAdapter()
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
        binding.refreshLoadingVg.setOnRefreshListener {
            binding.refreshLoadingVg.isRefreshing = false
            _intents.value = Intent.RetryLoading
        }
    }

    override fun render(dishListModel: DishListUiModel) {
        with(binding) {
            renderDishes(dishListModel.items)
            renderIsLoading(dishListModel.isLoading)
            renderError(dishListModel.isError)
        }
    }

    private fun DishListFragmentLayoutBinding.renderError(error: Boolean) {
        if (error) {
            // TODO add error view
            Toast.makeText(context, R.string.dishListError, LENGTH_LONG).show()
        }
    }

    private fun DishListFragmentLayoutBinding.renderDishes(items: List<Item<*>>?) {
        (dishListRv.adapter as GroupieAdapter).update(items ?: emptyList())
        dishListRv.isVisible = items != null
    }

    private fun DishListFragmentLayoutBinding.renderIsLoading(loading: Boolean) {
        skeletonAnimation.showAndStartAnimation(start = loading, attachedTo = dishListSkeleton.root)
    }
}
