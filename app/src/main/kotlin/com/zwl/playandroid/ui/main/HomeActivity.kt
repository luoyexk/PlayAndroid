package com.zwl.playandroid.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zwl.playandroid.R
import com.zwl.playandroid.app.DEFAULT_DB_NAME
import com.zwl.playandroid.databinding.HomeActivityBinding
import com.zwl.playandroid.db.UserMappingDatabase
import com.zwl.playandroid.ui.DatabaseNameViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var databaseNameViewModel: DatabaseNameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<HomeActivityBinding>(this, R.layout.home_activity)
        UserMappingDatabase.setUp(this)
        createAppViewModel()
    }

    private fun createAppViewModel() {
        databaseNameViewModel = ViewModelProviders.of(this)[DatabaseNameViewModel::class.java]
        updateDbNme()
    }

    private fun updateDbNme() {
        val dbName = UserMappingDatabase.getUser()?.dbName ?: DEFAULT_DB_NAME
        databaseNameViewModel.postDbName(dbName)
    }

}
