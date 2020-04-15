package com.disruption.cookcentral.ui.search;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.disruption.cookcentral.R;
import com.disruption.cookcentral.databinding.FragmentSearchBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {
    private static final String TAG = "SearchFragment";

    private FragmentSearchBinding mBinding;
    private SearchViewModel mSearchViewModel;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        mBinding.searchView.setOnQueryTextListener(this);

        mBinding.infoText.setText(requireContext().getString(R.string.search_something_to_continue));
        mBinding.infoText.setVisibility(View.VISIBLE);

        SearchedRecipesAdapter adapter = new SearchedRecipesAdapter();
        initRv(adapter);

        mSearchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        mSearchViewModel.getSearchedRecipes().observe(this, recipeResponseResource -> {
            switch (recipeResponseResource.status) {
                case SUCCESS:
                    if (recipeResponseResource.data != null && !recipeResponseResource.data.getResults().isEmpty()) {
                        adapter.submitList(recipeResponseResource.data.getResults());
                        mBinding.progressBar.setVisibility(View.GONE);
                        mBinding.infoText.setVisibility(View.GONE);
                    } else {
                        mBinding.progressBar.setVisibility(View.GONE);
                        mBinding.infoText.setVisibility(View.VISIBLE);
                        mBinding.infoText.setText(getString(R.string.no_data_to_display));
                    }
                    break;
                case ERROR:
                    mBinding.progressBar.setVisibility(View.GONE);
                    mBinding.infoText.setVisibility(View.VISIBLE);
                    mBinding.infoText.setText(getString(R.string.error_has_occurred, recipeResponseResource.message));
                    break;
                case LOADING:
                    mBinding.progressBar.setVisibility(View.VISIBLE);
                    mBinding.infoText.setVisibility(View.GONE);
                    break;
            }
        });

        return mBinding.getRoot();
    }

    private void initRv(SearchedRecipesAdapter adapter) {
        RecyclerView recyclerView = mBinding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 1));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (!TextUtils.isEmpty(query)) {
            mSearchViewModel.searchRecipe(query);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            mBinding.infoText.setText(requireContext().getString(R.string.search_something_to_continue));
            mBinding.infoText.setVisibility(View.VISIBLE);
        } else {
            mBinding.infoText.setVisibility(View.GONE);
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).show();
    }
}
