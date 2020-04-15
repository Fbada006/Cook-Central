package com.disruption.cookcentral.ui.favs;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.disruption.cookcentral.R;
import com.disruption.cookcentral.data.TinyDb;
import com.disruption.cookcentral.databinding.FragmentFavouritesBinding;
import com.disruption.cookcentral.models.CachedRecipe;
import com.disruption.cookcentral.utils.Constants;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment {
    private FragmentFavouritesBinding mBinding;
    private CachedRecipesAdapter mAdapter;
    private FavouritesViewModel mFavsViewModel;

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
        mAdapter = new CachedRecipesAdapter();
        mBinding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 1));
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setAdapter(mAdapter);
        itemTouchHelper().attachToRecyclerView(mBinding.recyclerView);

        FavouritesViewModelFactory factory = new FavouritesViewModelFactory(requireActivity().getApplication());

        mFavsViewModel = new ViewModelProvider(this, factory).get(FavouritesViewModel.class);

        mFavsViewModel.mFavData.observe(this, recipes -> {
            if (recipes != null && !recipes.isEmpty()) {
                mAdapter.submitList(recipes);
                new TinyDb(requireContext()).saveListOfFavouriteRecipes(Constants.FAV_KEY, recipes);
                mBinding.emptyFavs.setVisibility(View.INVISIBLE);
            } else {
                mBinding.emptyFavs.setVisibility(View.VISIBLE);
            }
        });
    }

    private ItemTouchHelper itemTouchHelper() {
        return new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                CachedRecipe recipe = mAdapter.getCachedRecipeAtPosition(viewHolder.getBindingAdapterPosition());
                mFavsViewModel.deleteRecipeFromFavourites(recipe);

                CoordinatorLayout container = requireActivity().findViewById(R.id.container);
                final Snackbar snack = Snackbar.make(container, requireContext().getString(R.string.recipe_deleted),
                        Snackbar.LENGTH_LONG)
                        .setAction(requireContext().getString(R.string.undo_fav_delete),
                                view -> mFavsViewModel.insertRecipeToFavourites(recipe));

                CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                        snack.getView().getLayoutParams();
                params.setAnchorId(R.id.bottom_nav_view);
                params.gravity = Gravity.TOP;
                params.anchorGravity = Gravity.TOP;
                snack.getView().setLayoutParams(params);

                snack.show();
            }
        });
    }
}
