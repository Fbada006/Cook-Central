package com.disruption.cookcentral.ui.details

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.disruption.cookcentral.databinding.StepsItemBinding
import com.disruption.cookcentral.models.Steps
import com.disruption.cookcentral.ui.details.StepAdapter.StepsViewHolder

class StepAdapter : ListAdapter<Steps, StepsViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): StepsViewHolder {
        val binding = StepsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StepsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        val pastry = getItem(position)
        holder.bind(pastry)
    }

    inner class StepsViewHolder(private val mStepsBinding: StepsItemBinding)
        : RecyclerView.ViewHolder(mStepsBinding.root) {
        fun bind(steps: Steps?) {
            mStepsBinding.step = steps
        }

    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Steps> = object : DiffUtil.ItemCallback<Steps>() {
            override fun areItemsTheSame(oldItem: Steps, newItem: Steps): Boolean {
                return oldItem.number == newItem.number
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Steps, newItem: Steps): Boolean {
                return oldItem === newItem
            }
        }
    }
}