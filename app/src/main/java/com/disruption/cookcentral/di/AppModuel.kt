package com.disruption.cookcentral.di

import androidx.room.Room
import com.disruption.cookcentral.data.RecipeDatabase
import com.disruption.cookcentral.data.RecipeRepository
import com.disruption.cookcentral.ui.details.DetailsViewModel
import com.disruption.cookcentral.ui.favs.FavouritesViewModel
import com.disruption.cookcentral.ui.main.RecipesViewModel
import com.disruption.cookcentral.ui.search.SearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**The module containing the dependencies to inject*/
val appModule = module {
    //Single database
    single {
        Room.databaseBuilder(androidContext(),
                RecipeDatabase::class.java, "recipelist")
                .build()
    }

    //Single instance of repository
    single { RecipeRepository(get()) }

    //Provide viewModels
    viewModel { FavouritesViewModel(androidApplication()) }
    viewModel { DetailsViewModel(androidApplication()) }
    viewModel { RecipesViewModel() }
    viewModel { SearchViewModel() }
}