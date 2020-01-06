package com.zwl.playandroid.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zwl.playandroid.base.BaseFragment
import com.zwl.playandroid.databinding.DiscoverFragmentBinding

/**
 * Create: 2020-01-02 10:28
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class DiscoverFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DiscoverFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}