package com.disruption.cookcentral.ui.details;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.disruption.cookcentral.R;
import com.disruption.cookcentral.databinding.FragmentDetailsBinding;
import com.disruption.cookcentral.models.AnalyzedInstructions;
import com.disruption.cookcentral.models.Ingredients;
import com.disruption.cookcentral.models.Recipe;
import com.disruption.cookcentral.models.Steps;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    private static final String TAG = "DetailsFragment";

    private FragmentDetailsBinding mBinding;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_details, container, false);

        if (getArguments() != null) {
            Recipe recipe = DetailsFragmentArgs.fromBundle(getArguments()).getRecipe();
            setUpViews(recipe);
            setUpIngredientsRecyclerViews(recipe);
            setUpStepsRecyclerViews(recipe);
        }

        showAndHandleBackButton();

        return mBinding.getRoot();
    }

    private void setUpViews(Recipe recipe) {
        mBinding.tvRecipeTime.setText(requireContext().getString(R.string.recipe_time, recipe.getReadyInMinutes()));
        mBinding.tvRecipeName.setText(recipe.getTitle());
        mBinding.tvRecipeInstructions.setText(Html.fromHtml(recipe.getInstructions()));
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

    private void showAndHandleBackButton() {
//        Toolbar toolbar = mBinding.detailToolbar;
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
//        toolbar.setNavigationOnClickListener(view -> Objects.requireNonNull(getActivity()).onBackPressed());
    }
}
