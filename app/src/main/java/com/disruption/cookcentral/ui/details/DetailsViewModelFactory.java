package com.disruption.cookcentral.ui.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

@SuppressWarnings("ALL")
public class DetailsViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application mApplication;

    public DetailsViewModelFactory(Application application) {
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
            return (T) new DetailsViewModel(mApplication);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
