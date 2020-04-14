package com.disruption.cookcentral.work;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.disruption.cookcentral.network.RecipeApiServiceProvider;

public class FoodJokeWorker extends Worker {

    /**
     * @param context      is context
     * @param workerParams are the params for the worker
     */
    public FoodJokeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            RecipeApiServiceProvider.getRecipeApiService();
            return Result.success();
        } catch (Exception exception) {
            return Result.retry();
        }
    }
}
