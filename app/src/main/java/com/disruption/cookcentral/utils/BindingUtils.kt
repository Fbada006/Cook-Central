package com.disruption.cookcentral.utils

import android.content.Context
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.disruption.cookcentral.R

//TODO: Get rid of this repetition
/**
 * This method checks if the recipe has an image. If it does not, simply load the error image
 */
@BindingAdapter("imageRecipe")
fun setRecipeImage(imageView: ImageView, recipeImage: String?) {
    val context = imageView.context
    if (!TextUtils.isEmpty(recipeImage)) {
        Glide.with(context)
                .load(recipeImage)
                .centerCrop()
                .placeholder(R.drawable.image_loading_animation)
                .error(R.drawable.ic_error)
                .into(imageView)
    } else {
        loadErrorImage(imageView, context)
    }
}

@BindingAdapter("imageIngredient")
fun setIngredientImage(imageView: ImageView, ingredientImage: String) {
    val context = imageView.context
    if (!TextUtils.isEmpty(ingredientImage)) {
        Glide.with(context)
                .load(Constants.INGREDIENT_IMAGE_BASE_URL + ingredientImage)
                .centerCrop()
                .placeholder(R.drawable.image_loading_animation)
                .error(R.drawable.ic_error)
                .into(imageView)
    } else {
        loadErrorImage(imageView, context)
    }
}


@BindingAdapter("imageSearchedRecipe")
fun setSearchedRecipeIngredient(imageView: ImageView, recipeImage: String) {
    val context = imageView.context
    if (!TextUtils.isEmpty(recipeImage)) {
        Glide.with(context)
                .load(Constants.SEARCHED_RECIPE_IMAGE_BASE_URL + recipeImage)
                .centerCrop()
                .placeholder(R.drawable.image_loading_animation)
                .error(R.drawable.ic_error)
                .into(imageView)
    } else {
        loadErrorImage(imageView, context)
    }
}

private fun loadErrorImage(imageView: ImageView, context: Context) {
    Glide.with(context)
            .load(R.drawable.ic_error)
            .centerCrop()
            .into(imageView)
}
