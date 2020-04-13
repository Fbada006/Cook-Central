package com.disruption.cookcentral.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.disruption.cookcentral.R;

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
                    .placeholder(R.drawable.recipe_loading_animation)
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
