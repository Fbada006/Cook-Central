package com.disruption.cookcentral;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private NavController mNavController;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initNavComponent();
        initTestAds();
    }

    private void initTestAds() {
        RequestConfiguration requestConfiguration
                = new RequestConfiguration.Builder()
                .setTestDeviceIds(new ArrayList<>(Collections.singletonList(AdRequest.DEVICE_ID_EMULATOR)))
                .build();
        MobileAds.setRequestConfiguration(requestConfiguration);

        AdRequest adRequest = new AdRequest.Builder()
                .build();
        ((AdView) findViewById(R.id.adView)).loadAd(adRequest);
    }

    private void initNavComponent() {
        mAppBarConfiguration = new AppBarConfiguration.Builder(getTopLevelDestinations()).build();

        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);

        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, mNavController);

        mNavController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.detailsFragment) {
                bottomNavigationView.setVisibility(View.GONE);
            } else {
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * Helper method for setting the top level destinations of the navigation
     */
    private Set<Integer> getTopLevelDestinations() {
        Set<Integer> topLevelDestinations = new HashSet<>();
        topLevelDestinations.add(R.id.recipesFragment);
        topLevelDestinations.add(R.id.favouritesFragment);
        topLevelDestinations.add(R.id.searchFragment);
        return topLevelDestinations;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mNavController, mAppBarConfiguration);
    }
}
