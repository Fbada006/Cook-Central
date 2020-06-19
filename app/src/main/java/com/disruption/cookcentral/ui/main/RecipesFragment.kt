package com.disruption.cookcentral.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.disruption.cookcentral.R
import com.disruption.cookcentral.databinding.RecipesFragmentBinding
import com.disruption.cookcentral.models.Recipe
import com.disruption.cookcentral.utils.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class RecipesFragment : Fragment() {
    private lateinit var mBinding: RecipesFragmentBinding
    private var mAdapter: RecipesAdapter? = null
    private val mRecipesViewModel: RecipesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.recipes_fragment, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = RecipesAdapter(RecipeClickListener { recipe: Recipe -> onRecipeClick(recipe) })
        initRv()
        // mRecipesViewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)
        observeViewModelForRecipes()
        observeViewModelForNavigation()
    }

    private fun initRv() {
        val recyclerView = mBinding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = mAdapter
    }

    private fun observeViewModelForRecipes() {
        mRecipesViewModel.mRecipeResource?.observe(viewLifecycleOwner, Observer { recipeResponseResource ->
            when (recipeResponseResource.status) {
                Resource.Status.SUCCESS -> if (recipeResponseResource.data != null && recipeResponseResource.data.recipes.isNotEmpty()) {
                    mAdapter!!.submitList(recipeResponseResource.data.recipes)
                    mBinding.progressBar.visibility = View.GONE
                    mBinding.errorText.visibility = View.GONE
                } else {
                    mBinding.progressBar.visibility = View.GONE
                    mBinding.errorText.visibility = View.VISIBLE
                    mBinding.errorText.text = getString(R.string.no_data_to_display)
                }
                Resource.Status.ERROR -> {
                    mBinding.progressBar.visibility = View.GONE
                    mBinding.errorText.visibility = View.VISIBLE
                    mBinding.errorText.text = getString(R.string.error_has_occurred, recipeResponseResource.message)
                }
                Resource.Status.LOADING -> {
                    mBinding.progressBar.visibility = View.VISIBLE
                    mBinding.errorText.visibility = View.GONE
                }
            }
        })
    }

    private fun observeViewModelForNavigation() {
        mRecipesViewModel.getNavigateToRecipe().observe(viewLifecycleOwner, Observer { recipe: Recipe? ->
            if (recipe != null) {
                //Then the user has clicked on a recipe so navigation is required
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(
                        RecipesFragmentDirections.actionRecipesFragmentToDetailsFragment(recipe)
                )

                //Inform the ViewModel navigation is done to avoid triggering multiple events
                mRecipesViewModel.displayRecipeDetailsComplete()
            }
        })
    }

    private fun onRecipeClick(recipe: Recipe) {
        mRecipesViewModel.displayRecipeDetails(recipe)
    }
}