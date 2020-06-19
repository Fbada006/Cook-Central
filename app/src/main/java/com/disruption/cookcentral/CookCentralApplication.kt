package com.disruption.cookcentral

import androidx.multidex.MultiDexApplication
import androidx.work.*
import com.disruption.cookcentral.di.appModule
import com.disruption.cookcentral.work.RandomRecipeWorker
import org.koin.android.ext.koin.androidContext
import java.util.concurrent.TimeUnit

class CookCentralApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
        setUpRecipeWork()
    }

    private fun startKoin() {
        // start Koin!
        org.koin.core.context.startKoin {
            // declare used Android context
            androidContext(this@CookCentralApplication)
            // declare modules
            modules(appModule)
        }
    }

    private fun setUpRecipeWork() {
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .build()

        val request = PeriodicWorkRequest.Builder(RandomRecipeWorker::class.java, 1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                getString(R.string.random_recipe_worker_name),
                ExistingPeriodicWorkPolicy.KEEP,
                request
        )
    }
}