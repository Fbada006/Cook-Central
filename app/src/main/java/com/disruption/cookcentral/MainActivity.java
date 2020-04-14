package com.disruption.cookcentral;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        init();
    }

    private void init() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(getTopLevelDestinations()).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.detailsFragment) {
                Objects.requireNonNull(getSupportActionBar()).hide();
                bottomNavigationView.setVisibility(View.GONE);
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
}
