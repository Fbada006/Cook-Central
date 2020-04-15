package com.disruption.cookcentral.widget;

import android.content.Context;
import android.text.Html;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.disruption.cookcentral.R;
import com.disruption.cookcentral.data.RecipeDatabase;
import com.disruption.cookcentral.models.CachedRecipe;

import java.util.List;

public class FavsWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<CachedRecipe> mRecipeList;
    private RecipeDatabase mRecipeDatabase;

    public FavsWidgetRemoteViewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        mRecipeDatabase = RecipeDatabase.getInstance(mContext);
    }

    @Override
    public void onDataSetChanged() {
        mRecipeList = mRecipeDatabase.recipeDao().loadAllFavsForWidget();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mRecipeList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.item_widget_recipe);
        CachedRecipe recipe = mRecipeList.get(position);
        remoteViews.setTextViewText(R.id.recipe_title, recipe.getTitle());
        remoteViews.setTextViewText(R.id.recipe_summary, Html.fromHtml(recipe.getSummary()));

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
