package com.disruption.cookcentral.ui.favs;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.disruption.cookcentral.databinding.CachedRecipeItemBinding;
import com.disruption.cookcentral.models.CachedRecipe;

public class CachedRecipesAdapter extends ListAdapter<CachedRecipe, CachedRecipesAdapter.RecipeViewHolder> {

    private static final DiffUtil.ItemCallback<CachedRecipe> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CachedRecipe>() {
                @Override
                public boolean areItemsTheSame(@NonNull CachedRecipe oldItem, @NonNull CachedRecipe newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull CachedRecipe oldItem, @NonNull CachedRecipe newItem) {
                    return oldItem == newItem;
                }
            };

    public CachedRecipesAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                               int viewType) {
        CachedRecipeItemBinding binding =
                CachedRecipeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, final int position) {
        CachedRecipe pastry = getItem(position);
        holder.bind(pastry);
    }

    public CachedRecipe getCachedRecipeAtPosition(int position) {
        return getItem(position);
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        private CachedRecipeItemBinding mRecipeBinding;

        RecipeViewHolder(CachedRecipeItemBinding binding) {
            super(binding.getRoot());
            mRecipeBinding = binding;
        }

        private void bind(CachedRecipe cachedRecipe) {
            mRecipeBinding.setRecipe(cachedRecipe);
        }
    }
}
