package com.disruption.cookcentral.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.disruption.cookcentral.databinding.RecipeItemBinding
import com.disruption.cookcentral.models.Recipe

class RecipesAdapter(private val mRecipeClickListener: RecipeClickListener) :
        ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecipeViewHolder {
        val binding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe, mRecipeClickListener)
    }

    inner class RecipeViewHolder(private val mRecipeBinding: RecipeItemBinding) : RecyclerView.ViewHolder(mRecipeBinding.root) {
        fun bind(pastry: Recipe?, recipeClickListener: RecipeClickListener) {
            mRecipeBinding.recipe = pastry
            mRecipeBinding.clickListener = recipeClickListener
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Recipe> = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
                return oldItem === newItem
            }
        }
    }
}

class RecipeClickListener(val clickListener: (recipe: Recipe) -> Unit) {
    fun onRecipeClickListener(recipe: Recipe) = clickListener(recipe)
}