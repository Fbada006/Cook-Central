package com.disruption.cookcentral.ui.details

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.disruption.cookcentral.R
import com.disruption.cookcentral.databinding.FragmentDetailsBinding
import com.disruption.cookcentral.models.CachedRecipe
import com.disruption.cookcentral.models.Ingredients
import com.disruption.cookcentral.models.Recipe
import com.like.LikeButton
import com.like.OnLikeListener
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {
    private lateinit var mBinding: FragmentDetailsBinding
    private var mLikeButton: LikeButton? = null
    private var mRecipe: Recipe? = null
    private val mDetailsViewModel: DetailsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        mLikeButton = mBinding.starButton

        if (arguments != null) {
            mRecipe = DetailsFragmentArgs.fromBundle(requireArguments()).recipe
            setUpViews(mRecipe!!)
            setUpIngredientsRecyclerViews(mRecipe!!)
            setUpStepsRecyclerViews(mRecipe!!)
            //val factory = DetailsViewModelFactory(requireActivity().application)
            //mDetailsViewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
            setUpFavsListener()
            observeLikedState()
        }

        return mBinding.root
    }

    private fun setUpViews(recipe: Recipe) {
        mBinding.tvRecipeTime.text = requireContext().getString(R.string.recipe_time, recipe.readyInMinutes, recipe.servings)
        mBinding.tvRecipeName.text = recipe.title
        mBinding.tvRecipeInstructions.text = Html.fromHtml(recipe.summary)

        Glide.with(requireContext())
                .load(recipe.image)
                .centerCrop()
                .placeholder(R.drawable.image_loading_animation)
                .error(R.drawable.ic_error)
                .into(mBinding.ivRecipeImage)
    }

    private fun setUpIngredientsRecyclerViews(recipe: Recipe) {
        val adapter = IngredientAdapter()
        mBinding.rvIngredientsList.adapter = adapter
        mBinding.rvIngredientsList.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        val ingredientsSet: MutableSet<Ingredients> = HashSet()

        for (instructions in recipe.analyzedInstructions!!) {
            for (steps in instructions.steps!!) {
                if (steps.ingredients != null && steps.ingredients!!.isNotEmpty()) {
                    ingredientsSet.addAll(steps.ingredients!!)
                }
            }
        }

        if (ingredientsSet.isNotEmpty()) {
            val ingredients: List<Ingredients> = ArrayList(ingredientsSet)
            adapter.submitList(ingredients)
        } else {
            mBinding.tvIngredientsError.visibility = View.VISIBLE
        }
    }

    private fun setUpStepsRecyclerViews(recipe: Recipe) {
        val adapter = StepAdapter()
        mBinding.rvInstructionsList.adapter = adapter
        mBinding.rvInstructionsList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        for (instructions in recipe.analyzedInstructions!!) {
            if (instructions.steps != null && instructions.steps!!.isNotEmpty()) {
                adapter.submitList(instructions.steps)
            } else {
                mBinding.tvInstructionsError.visibility = View.VISIBLE
            }
        }
    }

    private fun observeLikedState() {
        mDetailsViewModel.isRecipeInFavs(mRecipe!!.id).observe(viewLifecycleOwner, Observer { recipe: CachedRecipe? ->
            mLikeButton!!.isLiked = recipe != null
        })
    }

    private fun setUpFavsListener() {
        var cachedRecipe: CachedRecipe? = null
        if (mRecipe != null) {
            cachedRecipe = CachedRecipe()
            cachedRecipe.id = mRecipe!!.id
            cachedRecipe.image = mRecipe!!.image
            cachedRecipe.readyInMinutes = mRecipe!!.readyInMinutes
            cachedRecipe.summary = mRecipe!!.summary
            cachedRecipe.title = mRecipe!!.title
            cachedRecipe.servings = mRecipe!!.servings
        }

        val finalCachedRecipe = cachedRecipe

        mLikeButton!!.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {
                mDetailsViewModel.insertRecipeToFavourites(finalCachedRecipe!!)
                Toast.makeText(context, getString(R.string.add_to_favs), Toast.LENGTH_SHORT).show()
            }

            override fun unLiked(likeButton: LikeButton) {
                mDetailsViewModel.deleteRecipeFromFavourites(finalCachedRecipe!!)
                Toast.makeText(context, getString(R.string.remove_from_favs), Toast.LENGTH_SHORT).show()
            }
        })
    }
}