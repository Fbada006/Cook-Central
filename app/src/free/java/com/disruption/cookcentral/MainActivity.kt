package com.disruption.cookcentral

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {
    private var mNavController: NavController? = null
    private var mAppBarConfiguration: AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initNavComponent()
        initTestAds()
    }

    private fun initTestAds() {

        val requestConfiguration = RequestConfiguration.Builder()
                .setTestDeviceIds(ArrayList(listOf(AdRequest.DEVICE_ID_EMULATOR)))
                .build()

        MobileAds.setRequestConfiguration(requestConfiguration)

        val adRequest = AdRequest.Builder()
                .build()
        (findViewById<View>(R.id.adView) as AdView).loadAd(adRequest)
    }

    private fun initNavComponent() {
        mAppBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations).build()

        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        NavigationUI.setupActionBarWithNavController(this, mNavController!!, mAppBarConfiguration!!)
        NavigationUI.setupWithNavController(bottomNavigationView, mNavController!!)

        mNavController!!.addOnDestinationChangedListener { _, destination, _ ->
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