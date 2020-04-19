package com.disruption.cookcentral.ui.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.disruption.cookcentral.databinding.IngredientItemBinding
import com.disruption.cookcentral.models.Ingredients
import com.disruption.cookcentral.ui.details.IngredientAdapter.IngredientViewHolder

class IngredientAdapter : ListAdapter<Ingredients, IngredientViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): IngredientViewHolder {
        val binding = IngredientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredients = getItem(position)
        holder.bind(ingredients)
    }

    inner class IngredientViewHolder(private val mIngredientItemBinding: IngredientItemBinding) : RecyclerView.ViewHolder(mIngredientItemBinding.root) {
        fun bind(ingredients: Ingredients?) {
            mIngredientItemBinding.ingredient = ingredients
        }

    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Ingredients> = object : DiffUtil.ItemCallback<Ingredients>() {
            override fun areItemsTheSame(oldItem: Ingredients, newItem: Ingredients): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Ingredients, newItem: Ingredients): Boolean {
                return oldItem === newItem
            }
        }
    }
}