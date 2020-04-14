package com.disruption.cookcentral.ui.favs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

@SuppressWarnings("ALL")
public class FavouritesViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;

    public FavouritesViewModelFactory(Application application) {
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FavouritesViewModel.class)) {
            return (T) new FavouritesViewModel(mApplication);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
