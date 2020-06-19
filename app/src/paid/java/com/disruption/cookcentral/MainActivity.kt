package com.disruption.cookcentral

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mNavController: NavController? = null
    private var mAppBarConfiguration: AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initNavComponent()
    }

    private fun initNavComponent() {
        mAppBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations).build()
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        NavigationUI.setupActionBarWithNavController(this, mNavController!!, mAppBarConfiguration!!)
        NavigationUI.setupWithNavController(bottomNavigationView, mNavController!!)

        mNavController!!.addOnDestinationChangedListener { controller: NavController?, destination: NavDestination, arguments: Bundle? ->
            if (destination.id == R.id.detailsFragment) {
                bottomNavigationView.visibility = View.GONE
            } else {
                bottomNavigationView.visibility = View.VISIBLE
            }
        }
    }

    private val topLevelDestinations = hashSetOf(
            R.id.recipesFragment,
            R.id.favouritesFragment,
            R.id.searchFragment
    )

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(mNavController!!, mAppBarConfiguration!!)
    }
}