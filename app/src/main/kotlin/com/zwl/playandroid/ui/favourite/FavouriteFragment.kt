package com.zwl.playandroid.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.zwl.playandroid.Injections
import com.zwl.playandroid.base.BaseViewModelFragment
import com.zwl.playandroid.databinding.FavouriteFragmentBinding

/**
 * Create: 2020-01-07 14:33
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class FavouriteFragment : BaseViewModelFragment<FavouriteViewModel>() {
    override fun initViewModel(dbName: String): FavouriteViewModel {
        return ViewModelProviders.of(requireActivity(), Injections.providerAppViewModelFactory(requireContext(), dbName))[FavouriteViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FavouriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.requestFavourite()
    }
}