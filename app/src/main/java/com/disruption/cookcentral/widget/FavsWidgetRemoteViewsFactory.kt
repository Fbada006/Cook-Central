package com.disruption.cookcentral.widget

import android.content.Context
import android.text.Html
import android.view.View
import android.widget.RemoteViews
import android.widget.RemoteViewsService.RemoteViewsFactory
import com.disruption.cookcentral.R
import com.disruption.cookcentral.data.TinyDb
import com.disruption.cookcentral.models.CachedRecipe
import com.disruption.cookcentral.utils.Constants

class FavsWidgetRemoteViewsFactory(private val mContext: Context) : RemoteViewsFactory {
    private var mRecipeList: List<CachedRecipe>? = null

    override fun onCreate() {
        mRecipeList = TinyDb(mContext).getListOfFavouriteRecipes(Constants.FAV_KEY)
    }

    override fun onDataSetChanged() {}
    override fun onDestroy() {}
    override fun getCount(): Int {
        return if (mRecipeList != null) mRecipeList!!.size else 0
    }

    override fun getViewAt(position: Int): RemoteViews {
        val remoteViews = RemoteViews(mContext.packageName, R.layout.item_widget_recipe)
        val recipe = mRecipeList!![position]

        if (mRecipeList!!.isEmpty()) {
            remoteViews.setTextViewText(R.id.recipe_title, mContext.getString(R.string.no_favourites_to_show))
            remoteViews.setViewVisibility(R.id.recipe_summary, View.GONE)
        } else {
            remoteViews.setTextViewText(R.id.recipe_title, recipe.title)
            remoteViews.setTextViewText(R.id.recipe_summary, Html.fromHtml(recipe.summary))
        }
        return remoteViews
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

}