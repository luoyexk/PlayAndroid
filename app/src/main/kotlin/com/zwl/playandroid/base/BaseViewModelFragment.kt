package com.zwl.playandroid.base

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.zwl.playandroid.Injections
import com.zwl.playandroid.app.DEFAULT_DB_NAME
import com.zwl.playandroid.ui.DatabaseNameViewModel
import com.zwl.playandroid.ui.PlayAndroidViewModel

/**
 * Create: 2019-12-31 11:36
 * version:
 * desc:
 *
 * @author Zouweilin
 */
abstract class BaseViewModelFragment<VM : BaseViewModel> : BaseFragment() {

    open lateinit var databaseNameViewModel: DatabaseNameViewModel
    open lateinit var viewModel: VM

    abstract fun initViewModel(dbName: String): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAppViewModel()
    }

    private fun createAppViewModel() {
        databaseNameViewModel =
            ViewModelProviders.of(this.requireActivity())[DatabaseNameViewModel::class.java]
        databaseNameViewModel.dbName.observe(this,
            Observer<String> { name ->
                name?.also {
                    viewModel = initViewModel(name)
                }
            })
        databaseNameViewModel.dbName.value?.also {
            viewModel = initViewModel(it)
        }
    }

}