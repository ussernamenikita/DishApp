package com.dish.app.dishlist.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dish.app.R
import com.dish.app.dishlist.bl.domain.Intent
import com.dish.app.dishlist.ui.view.DishListFragmentViewImpl
import com.dish.app.dishlist.ui.viewmodel.DishListViewModel
import com.dish.app.dishlist.ui.viewmodel.DishListViewModelFactory
import com.dish.app.dishlist.ui.viewmodel.Model
import com.dish.app.livedatautils.LiveDataObserver

class DishListFragment : Fragment(R.layout.dish_list_fragment_layout) {

    private val viewModel by viewModels<DishListViewModel> {
        DishListViewModelFactory(requireContext())
    }

    private var viewImpl: DishListFragmentViewImpl? = null

    private val modelRenderingObserver = LiveDataObserver<Model> {
        viewImpl?.render(it)
    }
    private val intentsObserver = LiveDataObserver<Intent?> { intent ->
        intent?.let(viewModel::onIntent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewImpl = DishListFragmentViewImpl(view)
        viewImpl?.intents?.observe(viewLifecycleOwner, intentsObserver)
        viewModel.models.observe(viewLifecycleOwner, modelRenderingObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.models.removeObserver(modelRenderingObserver)
        viewImpl?.intents?.removeObserver(intentsObserver)
        viewImpl = null
    }
}