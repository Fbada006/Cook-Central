package com.disruption.cookcentral.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

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
    public RemoteViews getViewAt(int i) {
        return null;
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
