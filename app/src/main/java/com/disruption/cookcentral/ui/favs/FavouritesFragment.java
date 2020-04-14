package com.disruption.cookcentral.ui.favs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourites, container, false);

        showFavs();

        return mBinding.getRoot();
    }

    private void showFavs() {
        CachedRecipesAdapter adapter = new CachedRecipesAdapter();
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 1));
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setAdapter(adapter);

        FavouritesViewModelFactory factory = new FavouritesViewModelFactory(requireActivity().getApplication());

        FavouritesViewModel favsViewModel = new ViewModelProvider(this, factory).get(FavouritesViewModel.class);

        favsViewModel.mFavData.observe(this, recipes -> {
            if (recipes != null && !recipes.isEmpty()) {
                adapter.submitList(recipes);
                mBinding.emptyFavs.setVisibility(View.INVISIBLE);
            } else {
                mBinding.emptyFavs.setVisibility(View.VISIBLE);
            }
        });
    }
}
