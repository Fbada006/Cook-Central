package com.disruption.cookcentral.ui.details;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.disruption.cookcentral.databinding.StepsItemBinding;
import com.disruption.cookcentral.models.Steps;

public class StepAdapter extends ListAdapter<Steps, StepAdapter.StepsViewHolder> {

    private static final DiffUtil.ItemCallback<Steps> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Steps>() {
                @Override
                public boolean areItemsTheSame(@NonNull Steps oldItem, @NonNull Steps newItem) {
                    return oldItem.getNumber() == newItem.getNumber();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull Steps oldItem, @NonNull Steps newItem) {
                    return oldItem == newItem;
                }
            };

    public StepAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                              int viewType) {
        StepsItemBinding binding =
                StepsItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StepsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, final int position) {
        Steps pastry = getItem(position);
        holder.bind(pastry);
    }

    class StepsViewHolder extends RecyclerView.ViewHolder {

        private StepsItemBinding mStepsBinding;

        StepsViewHolder(StepsItemBinding binding) {
            super(binding.getRoot());
            mStepsBinding = binding;
        }

        private void bind(Steps steps) {
            mStepsBinding.setStep(steps);
        }
    }
}
