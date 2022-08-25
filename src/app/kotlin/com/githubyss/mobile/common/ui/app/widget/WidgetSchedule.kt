package com.githubyss.mobile.common.ui.app.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.githubyss.mobile.common.ui.R


/**
 * WidgetSchedule
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/24 23:09:49
 */
class WidgetSchedule : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateWidgetSchedule(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
    }

    override fun onDisabled(context: Context) {
    }
}

/**  */
internal fun updateWidgetSchedule(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    val widgetText = context.getString(R.string.comui_app_widget_text)
    val views = RemoteViews(context.packageName, R.layout.comui_widget_schedule)
    views.setTextViewText(R.id.text_title, widgetText)

    appWidgetManager.updateAppWidget(appWidgetId, views)
}