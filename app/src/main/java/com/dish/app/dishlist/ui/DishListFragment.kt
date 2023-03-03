package com.dish.app.dishlist.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dish.app.R
import com.dish.app.dishlist.ui.binder.DishListBinder
import com.dish.app.dishlist.ui.binder.DishListBinderFactory
import com.dish.app.dishlist.ui.view.DishListFragmentViewImpl

class DishListFragment : Fragment(R.layout.dish_list_fragment_layout) {

    private val binder by viewModels<DishListBinder> {
        DishListBinderFactory(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewImpl = DishListFragmentViewImpl(view)
        binder.bind(view = viewImpl, lifecycleOwner = viewLifecycleOwner)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binder.unbind()
    }
}