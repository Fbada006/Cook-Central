package com.disruption.cookcentral.ui.favs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.disruption.cookcentral.R;
import com.disruption.cookcentral.databinding.FragmentFavouritesBinding;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment {
    private FragmentFavouritesBinding mBinding;

    public FavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_favourites, container, false);

        return mBinding.getRoot();
    }

}
