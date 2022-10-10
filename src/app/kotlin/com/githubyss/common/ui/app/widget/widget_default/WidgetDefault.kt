package com.githubyss.common.ui.app.widget.widget_default

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.githubyss.common.base.app_widget.classical.BaseAppWidget
import com.githubyss.common.kit.util.getPendingIntent
import com.githubyss.common.ui.R
import com.githubyss.common.ui.app.page.homepage.HomepageActivity


/**
 * WidgetDefault
 *
 * Implementation of App Widget functionality.
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/24 15:50:33
 */
class WidgetDefault : BaseAppWidget() {

    /** ****************************** Override ****************************** */

    /**  */
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        // There may be multiple widgets active, so update all of them
        appWidgetIds.forEach { appWidgetId ->
            updateWidgetDefault(context, appWidgetManager, appWidgetId)
        }
    }
}

/**  */
internal fun updateWidgetDefault(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    // Create an Intent to launch ExampleActivity
    val pendingIntent = getPendingIntent<HomepageActivity>(context, Intent(context, HomepageActivity::class.java), appWidgetId, "")

    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.comui_app_widget).apply {
        val widgetText = context.getString(R.string.comui_app_widget_text)
        setTextViewText(R.id.appwidget_text, widgetText)
        setOnClickPendingIntent(R.id.appwidget_text, pendingIntent)
    }

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
