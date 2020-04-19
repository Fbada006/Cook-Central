package com.disruption.cookcentral.network

import com.disruption.cookcentral.utils.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RecipeApiServiceProvider {

    private var retrofit: Retrofit? = null

    private fun retrofitInstance(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
        return retrofit
    }

    @JvmStatic
    val recipeApiService: RecipeApiService
        get() = retrofitInstance()!!.create(RecipeApiService::class.java)
}