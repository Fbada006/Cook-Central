package com.disruption.cookcentral.ui.details;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.disruption.cookcentral.R;
import com.disruption.cookcentral.databinding.FragmentDetailsBinding;
import com.disruption.cookcentral.models.AnalyzedInstructions;
import com.disruption.cookcentral.models.Ingredients;
import com.disruption.cookcentral.models.Recipe;
import com.disruption.cookcentral.models.Steps;
import com.like.LikeButton;
import com.like.OnLikeListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding mBinding;
    private LikeButton mLikeButton;
    private Recipe mRecipe;
    private DetailsViewModel mDetailsViewModel;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_details, container, false);
        mLikeButton = mBinding.starButton;

        if (getArguments() != null) {
            mRecipe = DetailsFragmentArgs.fromBundle(getArguments()).getRecipe();
            setUpViews(mRecipe);
            setUpIngredientsRecyclerViews(mRecipe);
            setUpStepsRecyclerViews(mRecipe);

            DetailsViewModelFactory factory = new DetailsViewModelFactory(requireActivity().getApplication());
            mDetailsViewModel = new ViewModelProvider(this, factory).get(DetailsViewModel.class);

            setUpFavsListener();
            observeLikedState();
        }

        return mBinding.getRoot();
    }

    private void setUpViews(Recipe recipe) {
        mBinding.tvRecipeTime.setText(requireContext().getString(R.string.recipe_time, recipe.getReadyInMinutes(), recipe.getServings()));
        mBinding.tvRecipeName.setText(recipe.getTitle());
        mBinding.tvRecipeInstructions.setText(Html.fromHtml(recipe.getSummary()));
        Glide.with(requireContext())
                .load(recipe.getImage())
                .centerCrop()
                .placeholder(R.drawable.image_loading_animation)
                .error(R.drawable.ic_error)
                .into(mBinding.ivRecipeImage);
    }

    private void setUpIngredientsRecyclerViews(Recipe recipe) {
        IngredientAdapter adapter = new IngredientAdapter();
        mBinding.rvIngredientsList.setAdapter(adapter);
        mBinding.rvIngredientsList.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false));
        Set<Ingredients> ingredientsSet = new HashSet<>();
        for (AnalyzedInstructions instructions : recipe.getAnalyzedInstructions()) {
            for (Steps steps : instructions.getSteps()) {
                if (steps.getIngredients() != null && !steps.getIngredients().isEmpty()) {
                    ingredientsSet.addAll(steps.getIngredients());
                }
            }
        }

        if (!ingredientsSet.isEmpty()) {
            List<Ingredients> ingredients = new ArrayList<>(ingredientsSet);
            adapter.submitList(ingredients);
        } else {
            mBinding.tvIngredientsError.setVisibility(View.VISIBLE);
        }
    }

    private void setUpStepsRecyclerViews(Recipe recipe) {
        StepAdapter adapter = new StepAdapter();
        mBinding.rvInstructionsList.setAdapter(adapter);
        mBinding.rvInstructionsList.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        for (AnalyzedInstructions instructions : recipe.getAnalyzedInstructions()) {
            if (instructions.getSteps() != null && !instructions.getSteps().isEmpty()) {
                adapter.submitList(instructions.getSteps());
            } else {
                mBinding.tvInstructionsError.setVisibility(View.VISIBLE);
            }
        }
    }

    private void observeLikedState() {
        mDetailsViewModel.isRecipeInFavs(mRecipe.getId()).observe(this, recipe -> {
            if (recipe != null) {
                mLikeButton.setLiked(true);
            } else {
                mLikeButton.setLiked(false);
            }
        });
    }

    private void setUpFavsListener() {
        mLikeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                mDetailsViewModel.insertRecipeToFavourites(mRecipe);
                Toast.makeText(getContext(), getString(R.string.add_to_favs), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                mDetailsViewModel.deleteRecipeFromFavourites(mRecipe);
                Toast.makeText(getContext(), getString(R.string.remove_from_favs), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
