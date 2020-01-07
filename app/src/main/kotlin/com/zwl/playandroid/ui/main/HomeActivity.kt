package com.zwl.playandroid.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.zwl.playandroid.R
import com.zwl.playandroid.app.DEFAULT_DB_NAME
import com.zwl.playandroid.databinding.HomeActivityBinding
import com.zwl.playandroid.databinding.NavHeaderMainBinding
import com.zwl.playandroid.db.UserMappingDatabase
import com.zwl.playandroid.ui.DatabaseNameViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var databaseNameViewModel: DatabaseNameViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<HomeActivityBinding>(this, R.layout.home_activity)
        UserMappingDatabase.setUp(this)
        createAppViewModel()
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.menu_favourite), binding.drawerLayout
        )
        val navController = findNavController(R.id.nav_host)
//        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        drawer = binding.drawerLayout
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun createAppViewModel() {
        databaseNameViewModel = ViewModelProviders.of(this)[DatabaseNameViewModel::class.java]
        updateDbNme()

    }

    private fun updateDbNme() {
        val dbName = UserMappingDatabase.getUser()?.dbName ?: DEFAULT_DB_NAME
        databaseNameViewModel.postDbName(dbName)
    }

    fun onClick(view: View) {
        val navController = findNavController(R.id.nav_host)
        navController.navigate(R.id.account_login_register)
        drawer.closeDrawer(Gravity.LEFT)
    }

}
