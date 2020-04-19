package com.disruption.cookcentral.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.disruption.cookcentral.models.RecipeResponse
import com.disruption.cookcentral.network.RecipeApiServiceProvider.recipeApiService
import com.disruption.cookcentral.utils.Constants
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class RandomRecipeWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        return try {
            recipeApiService.getRandomRecipes(1, Constants.API_KEY)!!
                    .subscribeOn(Schedulers.io())
                    .subscribe(object : Subscriber<RecipeResponse?> {

                        override fun onSubscribe(s: Subscription) {}

                        override fun onNext(recipeResponse: RecipeResponse?) {
                            val recipe = recipeResponse!!.recipes[0]

                            WorkerUtils.makeRandomRecipeNotification(applicationContext,
                                    recipe.title, recipe.summary)
                        }

                        override fun onError(t: Throwable) {}
                        override fun onComplete() {}
                    })
            Result.success()
        } catch (exception: Exception) {
            Result.retry()
        }
    }
}