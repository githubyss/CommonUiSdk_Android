package com.githubyss.mobile.common.ui.app.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.githubyss.mobile.common.ui.R


/**
 * WidgetDefault
 *
 * Implementation of App Widget functionality.
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/24 15:50:33
 */
class WidgetDefault : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateWidgetDefault(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

/**  */
internal fun updateWidgetDefault(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    val widgetText = context.getString(R.string.comui_app_widget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.comui_app_widget)
    views.setTextViewText(R.id.appwidget_text, widgetText)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
