package com.disruption.cookcentral.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.disruption.cookcentral.R;

import static com.disruption.cookcentral.utils.Constants.INGREDIENT_IMAGE_BASE_URL;

public class BindingUtils {

    /**
     * This method checks if the recipe has an image. If it does not, simply load the error image
     */
    @BindingAdapter("imageRecipe")
    public static void setRecipeImage(ImageView imageView, String recipeImage) {
        Context context = imageView.getContext();

        if (!TextUtils.isEmpty(recipeImage)) {
            Glide.with(context)
                    .load(recipeImage)
                    .centerCrop()
                    .placeholder(R.drawable.image_loading_animation)
                    .error(R.drawable.ic_error)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_error)
                    .centerCrop()
                    .into(imageView);
        }
    }

    @BindingAdapter("imageIngredient")
    public static void setIngredientImage(ImageView imageView, String ingredientImage) {
        Context context = imageView.getContext();

        if (!TextUtils.isEmpty(ingredientImage)) {
            Glide.with(context)
                    .load(INGREDIENT_IMAGE_BASE_URL + ingredientImage)
                    .centerCrop()
                    .placeholder(R.drawable.image_loading_animation)
                    .error(R.drawable.ic_error)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_error)
                    .centerCrop()
                    .into(imageView);
        }
    }
}
