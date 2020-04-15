package com.disruption.cookcentral.ui.search;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.disruption.cookcentral.databinding.SearchedRecipeItemBinding;
import com.disruption.cookcentral.models.search.SearchedRecipe;

public class SearchedRecipesAdapter extends ListAdapter<SearchedRecipe, SearchedRecipesAdapter.RecipeViewHolder> {

    private static final DiffUtil.ItemCallback<SearchedRecipe> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<SearchedRecipe>() {
                @Override
                public boolean areItemsTheSame(@NonNull SearchedRecipe oldItem, @NonNull SearchedRecipe newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull SearchedRecipe oldItem, @NonNull SearchedRecipe newItem) {
                    return oldItem == newItem;
                }
            };

    public SearchedRecipesAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                               int viewType) {
        SearchedRecipeItemBinding binding =
                SearchedRecipeItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, final int position) {
        SearchedRecipe recipe = getItem(position);
        holder.bind(recipe);
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        private SearchedRecipeItemBinding mRecipeBinding;

        RecipeViewHolder(SearchedRecipeItemBinding binding) {
            super(binding.getRoot());
            mRecipeBinding = binding;
        }

        private void bind(SearchedRecipe recipe) {
            mRecipeBinding.setRecipe(recipe);
        }
    }
}
