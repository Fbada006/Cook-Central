package com.disruption.cookcentral.ui.details;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.disruption.cookcentral.R;
import com.disruption.cookcentral.databinding.FragmentDetailsBinding;
import com.disruption.cookcentral.models.Recipe;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends BottomSheetDialogFragment {

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
            Toast.makeText(requireContext(), "Recipe  " + args.getTitle(), Toast.LENGTH_LONG).show();
        }

        return mBinding.getRoot();
    }
}
