package com.disruption.cookcentral.ui.main;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.disruption.cookcentral.databinding.RecipeItemBinding;
import com.disruption.cookcentral.models.Recipe;

public class RecipesAdapter extends ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder> {
    private final RecipeClickListener mRecipeClickListener;

    public RecipesAdapter(RecipeClickListener recipeClickListener) {
        super(DIFF_CALLBACK);
        mRecipeClickListener = recipeClickListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                               int viewType) {
        RecipeItemBinding binding =
                RecipeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, final int position) {
        Recipe pastry = getItem(position);
        holder.bind(pastry, mRecipeClickListener);
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        private RecipeItemBinding mRecipeBinding;

        RecipeViewHolder(RecipeItemBinding binding) {
            super(binding.getRoot());
            mRecipeBinding = binding;
        }

        private void bind(Recipe pastry, RecipeClickListener recipeClickListener) {
            mRecipeBinding.setRecipe(pastry);
            mRecipeBinding.setClickListener(recipeClickListener);
        }
    }

    private static final DiffUtil.ItemCallback<Recipe> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Recipe>() {
                @Override
                public boolean areItemsTheSame(@NonNull Recipe oldItem, @NonNull Recipe newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Recipe oldItem, @NonNull Recipe newItem) {
                    return oldItem == newItem;
                }
            };

    public interface RecipeClickListener {
        void onRecipeClickListener(Recipe Recipe);
    }
}
