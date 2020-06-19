package com.disruption.cookcentral.ui.favs

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.disruption.cookcentral.R
import com.disruption.cookcentral.data.TinyDb
import com.disruption.cookcentral.databinding.FragmentFavouritesBinding
import com.disruption.cookcentral.models.CachedRecipe
import com.disruption.cookcentral.utils.Constants
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
class FavouritesFragment : Fragment() {
    private lateinit var mBinding: FragmentFavouritesBinding
    private var mAdapter: CachedRecipesAdapter? = null
    private var mFavsViewModel: FavouritesViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourites, container, false)
        showFavs()
        return mBinding.root
    }

    private fun showFavs() {
        mAdapter = CachedRecipesAdapter()
        mBinding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.adapter = mAdapter

        itemTouchHelper().attachToRecyclerView(mBinding.recyclerView)
        val factory = FavouritesViewModelFactory(requireActivity().application)
        mFavsViewModel = ViewModelProvider(this, factory).get(FavouritesViewModel::class.java)

        mFavsViewModel!!.mFavData.observe(viewLifecycleOwner, Observer { recipes: List<CachedRecipe?>? ->
            if (recipes != null && recipes.isNotEmpty()) {
                mAdapter!!.submitList(recipes)
                TinyDb(requireContext()).saveListOfFavouriteRecipes(Constants.FAV_KEY, recipes)
                mBinding.emptyFavs.visibility = View.INVISIBLE
            } else {
                mBinding.emptyFavs.visibility = View.VISIBLE
            }
        })
    }

    private fun itemTouchHelper(): ItemTouchHelper {
        return ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val recipe = mAdapter!!.getCachedRecipeAtPosition(viewHolder.bindingAdapterPosition)!!

                mFavsViewModel!!.deleteRecipeFromFavourites(recipe)

                val container: CoordinatorLayout = requireActivity().findViewById(R.id.container)
                val snack = Snackbar.make(container, requireContext().getString(R.string.recipe_deleted),
                        Snackbar.LENGTH_LONG)
                        .setAction(requireContext().getString(R.string.undo_fav_delete)
                        ) { mFavsViewModel!!.insertRecipeToFavourites(recipe) }

                val params = snack.view.layoutParams as CoordinatorLayout.LayoutParams
                params.anchorId = R.id.bottom_nav_view
                params.gravity = Gravity.TOP
                params.anchorGravity = Gravity.TOP
                snack.view.layoutParams = params

                snack.show()
            }
        })
    }
}