package com.disruption.cookcentral.work;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.disruption.cookcentral.models.Recipe;
import com.disruption.cookcentral.models.RecipeResponse;
import com.disruption.cookcentral.network.RecipeApiServiceProvider;
import com.disruption.cookcentral.utils.Constants;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.schedulers.Schedulers;

public class RandomRecipeWorker extends Worker {

    /**
     * @param context      is context
     * @param workerParams are the params for the worker
     */
    public RandomRecipeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            RecipeApiServiceProvider.getRecipeApiService().getRandomRecipes(1, Constants.API_KEY)
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<RecipeResponse>() {
                        @Override
                        public void onSubscribe(Subscription s) {

                        }

                        @Override
                        public void onNext(RecipeResponse recipeResponse) {
                            Recipe recipe = recipeResponse.getRecipes().get(0);
                            WorkerUtils.makeRandomRecipeNotification(getApplicationContext(),
                                    recipe.getTitle(), recipe.getSummary());
                        }

                        @Override
                        public void onError(Throwable t) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
            return Result.success();
        } catch (Exception exception) {
            return Result.retry();
        }
    }
}
