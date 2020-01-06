package com.zwl.playandroid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.zwl.playandroid.base.BaseViewModelFragment
import com.zwl.playandroid.databinding.LoginFragmentBinding

/**
 * Create: 2020-01-03 11:20
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class LoginFragment : BaseViewModelFragment<AccountViewModel>() {

    override fun initViewModel(dbName: String): AccountViewModel {
        return createViewModel(dbName)
    }

    private fun createViewModel(dbName: String): AccountViewModel {
        return ViewModelProviders.of(requireActivity())[AccountViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = LoginFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }
}