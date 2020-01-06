package com.zwl.playandroid.ui.square

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zwl.playandroid.Injections
import com.zwl.playandroid.R
import com.zwl.playandroid.base.BaseViewModelFragment
import com.zwl.playandroid.databinding.SquareFragmentBinding

/**
 * Create: 2020-01-02 10:18
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class SquareFragment : BaseViewModelFragment<SquareViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<SquareFragmentBinding>(
            inflater,
            R.layout.square_fragment,
            container,
            false
        ).apply {
            viewModel = this@SquareFragment.viewModel
            lifecycleOwner = this@SquareFragment.viewLifecycleOwner
            clickListener = View.OnClickListener {
                this@SquareFragment.viewModel.add()
            }
        }
        viewModel.title.observe(
            this,
            Observer<String> { Log.e("zzzz", "SquareFragment - onCreateView -> $it") })
        return binding.root
    }

    override fun initViewModel(dbName: String): SquareViewModel {
        return createVM(dbName)
    }

    private fun createVM(dbName: String): SquareViewModel {
        return ViewModelProviders.of(
            requireActivity(),
            Injections.providerSquareViewModelFactory(dbName)
        )[SquareViewModel::class.java]
    }
}