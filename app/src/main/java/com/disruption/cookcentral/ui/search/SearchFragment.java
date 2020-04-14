package com.disruption.cookcentral.ui.search;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.disruption.cookcentral.R;
import com.disruption.cookcentral.databinding.FragmentSearchBinding;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private FragmentSearchBinding mBinding;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);

        return mBinding.getRoot();
    }

}
