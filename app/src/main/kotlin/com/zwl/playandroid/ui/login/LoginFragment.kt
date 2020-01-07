package com.zwl.playandroid.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.zwl.playandroid.Injections
import com.zwl.playandroid.R
import com.zwl.playandroid.annotation.AccountType
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
        return ViewModelProviders.of(requireActivity(), Injections.providerAppViewModelFactory(requireContext(), dbName))[AccountViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<LoginFragmentBinding>(inflater, R.layout.login_fragment, container, false)
        binding.viewModel = viewModel
        binding.callback = object : Callback {
            override fun ensure(userName: String?, password: String?, confirmPassword: String?) {
                viewModel.ensure(userName, password, confirmPassword)
            }

            override fun changeType(type: Int) {
                viewModel.changeType(type)
                binding.invalidateAll()
            }
        }
        return binding.root
    }

    interface Callback {
        fun ensure(userName: String?, password: String?, confirmPassword: String?)
        fun changeType(type: Int)
    }
}