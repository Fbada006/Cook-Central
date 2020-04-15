package com.disruption.cookcentral;

import androidx.multidex.MultiDexApplication;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.disruption.cookcentral.work.RandomRecipeWorker;

import java.util.concurrent.TimeUnit;

public class CookCentralApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setUpRecipeWork();
    }

    private void setUpRecipeWork() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build();

        PeriodicWorkRequest request =
                new PeriodicWorkRequest.Builder(RandomRecipeWorker.class, 1, TimeUnit.DAYS)
                        .setConstraints(constraints)
                        .build();

        WorkManager.getInstance(getApplicationContext()).enqueueUniquePeriodicWork(
                getString(R.string.random_recipe_worker_name),
                ExistingPeriodicWorkPolicy.KEEP,
                request
        );
    }
}
