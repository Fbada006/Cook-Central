package com.disruption.cookcentral.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.disruption.cookcentral.utils.Constants.BASE_URL;

public class RecipeApiServiceProvider {
    private static Retrofit retrofit;
    private static HttpLoggingInterceptor sHttpLoggingInterceptor =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);

    private static OkHttpClient sOkHttpClient = new OkHttpClient.Builder()
            .addInterceptor(sHttpLoggingInterceptor)
            .build();

    private static Retrofit retrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(sOkHttpClient)
                    .build();
        }
        return retrofit;
    }

    public static RecipeApiService getRecipeApiService() {
        return retrofitInstance().create(RecipeApiService.class);
    }
}
