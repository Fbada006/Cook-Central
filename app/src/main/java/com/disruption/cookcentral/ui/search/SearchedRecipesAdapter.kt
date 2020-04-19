package com.disruption.cookcentral.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.disruption.cookcentral.databinding.SearchedRecipeItemBinding
import com.disruption.cookcentral.models.search.SearchedRecipe

class SearchedRecipesAdapter : ListAdapter<SearchedRecipe, SearchedRecipesAdapter.RecipeViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecipeViewHolder {
        val binding = SearchedRecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    inner class RecipeViewHolder(private val mRecipeBinding: SearchedRecipeItemBinding) : RecyclerView.ViewHolder(mRecipeBinding.root) {
        fun bind(recipe: SearchedRecipe?) {
            mRecipeBinding.recipe = recipe
        }

    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<SearchedRecipe> = object : DiffUtil.ItemCallback<SearchedRecipe>() {
            override fun areItemsTheSame(oldItem: SearchedRecipe, newItem: SearchedRecipe): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: SearchedRecipe, newItem: SearchedRecipe): Boolean {
                return oldItem === newItem
            }
        }
    }
}