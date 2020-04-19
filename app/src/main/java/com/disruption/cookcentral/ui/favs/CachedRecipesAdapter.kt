package com.disruption.cookcentral.ui.favs

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.disruption.cookcentral.databinding.CachedRecipeItemBinding
import com.disruption.cookcentral.models.CachedRecipe

class CachedRecipesAdapter : ListAdapter<CachedRecipe, CachedRecipesAdapter.RecipeViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecipeViewHolder {
        val binding = CachedRecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val pastry = getItem(position)
        holder.bind(pastry)
    }

    fun getCachedRecipeAtPosition(position: Int): CachedRecipe? {
        return getItem(position)
    }

    inner class RecipeViewHolder(private val mRecipeBinding: CachedRecipeItemBinding) : RecyclerView.ViewHolder(mRecipeBinding.root) {
        fun bind(cachedRecipe: CachedRecipe?) {
            mRecipeBinding.recipe = cachedRecipe
        }

    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<CachedRecipe> = object : DiffUtil.ItemCallback<CachedRecipe>() {
            override fun areItemsTheSame(oldItem: CachedRecipe, newItem: CachedRecipe): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: CachedRecipe, newItem: CachedRecipe): Boolean {
                return oldItem === newItem
            }
        }
    }
}