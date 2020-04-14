package com.disruption.cookcentral.ui.details;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.disruption.cookcentral.databinding.IngredientItemBinding;
import com.disruption.cookcentral.models.Ingredients;

public class IngredientAdapter extends ListAdapter<Ingredients, IngredientAdapter.IngredientViewHolder> {

    private static final DiffUtil.ItemCallback<Ingredients> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Ingredients>() {
                @Override
                public boolean areItemsTheSame(@NonNull Ingredients oldItem, @NonNull Ingredients newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Ingredients oldItem, @NonNull Ingredients newItem) {
                    return oldItem == newItem;
                }
            };

    public IngredientAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                   int viewType) {
        IngredientItemBinding binding =
                IngredientItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new IngredientViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, final int position) {
        Ingredients ingredients = getItem(position);
        holder.bind(ingredients);
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        private IngredientItemBinding mIngredientItemBinding;

        IngredientViewHolder(IngredientItemBinding binding) {
            super(binding.getRoot());
            mIngredientItemBinding = binding;
        }

        private void bind(Ingredients ingredients) {
            mIngredientItemBinding.setIngredient(ingredients);
        }
    }
}
