package com.zwl.playandroid.ui.system

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zwl.playandroid.R
import com.zwl.playandroid.base.BaseFragment
import com.zwl.playandroid.databinding.SystemFragmentBinding


class SystemFragment : BaseFragment() {

    private lateinit var viewModel: SystemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SystemFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SystemViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
