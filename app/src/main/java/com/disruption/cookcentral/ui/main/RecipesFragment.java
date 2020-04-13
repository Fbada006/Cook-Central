package com.disruption.cookcentral.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.disruption.cookcentral.R;

public class RecipesFragment extends Fragment {

    private RecipesViewModel mRecipesViewModel;

    public static RecipesFragment newInstance() {
        return new RecipesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe_item, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecipesViewModel = new ViewModelProvider(this).get(RecipesViewModel.class);
        // TODO: Use the ViewModel
    }

}
