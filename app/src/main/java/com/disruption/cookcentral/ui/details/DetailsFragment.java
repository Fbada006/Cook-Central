package com.disruption.cookcentral.ui.details;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.disruption.cookcentral.R;
import com.disruption.cookcentral.databinding.FragmentDetailsBinding;
import com.disruption.cookcentral.models.AnalyzedInstructions;
import com.disruption.cookcentral.models.Recipe;
import com.disruption.cookcentral.models.Steps;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    private static final String TAG = "DetailsFragment";

    private FragmentDetailsBinding mBinding;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_details, container, false);
        if (getArguments() != null) {
            Recipe args = DetailsFragmentArgs.fromBundle(getArguments()).getRecipe();
            for (AnalyzedInstructions instructions : args.getAnalyzedInstructions()) {
                for (Steps steps : instructions.getSteps()) {
                    Log.e(TAG, "onCreateView:================ " + steps.getStep());
                }
            }
        }

        return mBinding.getRoot();
    }
}
