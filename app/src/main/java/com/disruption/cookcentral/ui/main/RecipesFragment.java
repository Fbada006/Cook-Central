package com.disruption.cookcentral.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.disruption.cookcentral.R;
import com.disruption.cookcentral.databinding.RecipesFragmentBinding;
import com.disruption.cookcentral.models.Recipe;

public class RecipesFragment extends Fragment {

    private RecipesFragmentBinding mBinding;
    private RecipesAdapter mAdapter;
    private RecipesViewModel mRecipesViewModel;

    public static RecipesFragment newInstance() {
        return new RecipesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.recipes_fragment, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new RecipesAdapter(this::onRecipeClick);
        RecyclerView recyclerView = mBinding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 1));
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);

        mRecipesViewModel = new ViewModelProvider(this).get(RecipesViewModel.class);

        observeViewModelForRecipes();
        observeViewModelForNavigation();
    }

    private void observeViewModelForRecipes() {
        mRecipesViewModel.mRecipeResource.observe(this, recipeResponseResource -> {
            switch (recipeResponseResource.status) {
                case SUCCESS:
                    if (recipeResponseResource.data != null && !recipeResponseResource.data.getRecipes().isEmpty()) {
                        mAdapter.submitList(recipeResponseResource.data.getRecipes());
                        mBinding.progressBar.setVisibility(View.GONE);
                        mBinding.errorText.setVisibility(View.GONE);
                    } else {
                        mBinding.progressBar.setVisibility(View.GONE);
                        mBinding.errorText.setVisibility(View.VISIBLE);
                        mBinding.errorText.setText(getString(R.string.no_data_to_display));
                    }
                    break;
                case ERROR:
                    mBinding.progressBar.setVisibility(View.GONE);
                    mBinding.errorText.setVisibility(View.VISIBLE);
                    mBinding.errorText.setText(getString(R.string.error_has_occurred, recipeResponseResource.message));
                    break;
                case LOADING:
                    mBinding.progressBar.setVisibility(View.VISIBLE);
                    mBinding.errorText.setVisibility(View.GONE);
                    break;
            }
        });
    }

    private void observeViewModelForNavigation() {
        mRecipesViewModel.getNavigateToRecipe().observe(this, recipe -> {
            if (recipe != null) {
                //Then the user has clicked on a recipe so navigation is required
                NavHostFragment.findNavController(this).navigate(
                        RecipesFragmentDirections.actionRecipesFragmentToDetailsFragment(recipe)
                );

                //Inform the ViewModel navigation is done to avoid triggering multiple events
                mRecipesViewModel.displayRecipeDetailsComplete();
            }
        });
    }

    private void onRecipeClick(Recipe recipe) {
        mRecipesViewModel.displayRecipeDetails(recipe);
    }
}
