package com.disruption.cookcentral.ui.search;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.disruption.cookcentral.R;
import com.disruption.cookcentral.databinding.FragmentSearchBinding;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";

    private FragmentSearchBinding mBinding;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);

        SearchViewModel searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        searchViewModel.searchRecipe("cheese");

        searchViewModel.getSearchedRecipes().observe(this, searchedRecipeResponseResource -> {
            switch (searchedRecipeResponseResource.status) {
                case SUCCESS:
                    Log.e(TAG, "onCreateView: -------------------- SUCCESS " + searchedRecipeResponseResource.data.getResults());
                    break;
                case ERROR:
                    Log.e(TAG, "onCreateView: -------------------- ERROR " + searchedRecipeResponseResource.message);
                    break;
                case LOADING:
                    break;
            }
        });

        return mBinding.getRoot();
    }

}
