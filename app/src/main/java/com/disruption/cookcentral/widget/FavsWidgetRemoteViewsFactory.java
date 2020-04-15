package com.disruption.cookcentral.widget;

import android.content.Context;
import android.text.Html;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.disruption.cookcentral.R;
import com.disruption.cookcentral.data.TinyDb;
import com.disruption.cookcentral.models.CachedRecipe;
import com.disruption.cookcentral.utils.Constants;

import java.util.List;

public class FavsWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<CachedRecipe> mRecipeList;

    public FavsWidgetRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        mRecipeList = new TinyDb(mContext).getListOfFavouriteRecipes(Constants.FAV_KEY);
    }

    @Override
    public void onDataSetChanged() {
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mRecipeList != null ? mRecipeList.size() : 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.item_widget_recipe);
        CachedRecipe recipe = mRecipeList.get(position);

        if (mRecipeList.isEmpty()) {
            remoteViews.setTextViewText(R.id.recipe_title, mContext.getString(R.string.no_favourites_to_show));
            remoteViews.setViewVisibility(R.id.recipe_summary, View.GONE);
        } else {
            remoteViews.setTextViewText(R.id.recipe_title, recipe.getTitle());
            remoteViews.setTextViewText(R.id.recipe_summary, Html.fromHtml(recipe.getSummary()));
        }
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
