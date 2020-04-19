package com.disruption.cookcentral.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.disruption.cookcentral.R

/**
 * Implementation of App Widget functionality.
 */
class FavsWidget : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        //TODO: Explore widgets some more
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                            appWidgetId: Int) {
            val widgetText: CharSequence = context.getString(R.string.fragment_fav_label)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.recipe_app_widget)
            views.setTextViewText(R.id.appwidget_text, widgetText)
            views.setRemoteAdapter(R.id.widget_recipe_list,
                    Intent(context, FavsWidgetRemoteViewsService::class.java))

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}