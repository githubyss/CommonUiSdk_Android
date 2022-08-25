package com.githubyss.mobile.common.ui.app.widget.widget_default

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.githubyss.mobile.common.kit.util.getPendingIntent
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.homepage.HomepageActivity


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

    /** ****************************** Override ****************************** */

    /**
     * 到达指定的时间，或者用户第一次创建 AppWidget 所调用的方法。
     * 每次创建该类型的 AppWidget 都会调用此方法，通常来说我们需要在该方法里为该 AppWidget 指定 RemoteViews 对象。
     */
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        appWidgetIds.forEach { appWidgetId ->
            updateWidgetDefault(context, appWidgetManager, appWidgetId)
        }
    }

    /**
     * 删除一个 AppWidget 所调用的方法。
     * 当该类型的 AppWidget 每次被删除时，调用此方法。
     */
    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    /**
     * 接收广播事件。
     * 广播接受者方法，用来接受广播消息。
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }

    /**
     * 创建第一个 AppWidget 实例所调用的方法。
     * 当第一次创建该类型的 AppWidget 时，调用此方法。
     */
    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    /**
     * 删除最后一个 AppWidget 所调用的方法。
     * 当该类型的窗口小部件 AppWidget 全被删除时，调用此方法。
     */
    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

/**  */
internal fun updateWidgetDefault(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    // Create an Intent to launch ExampleActivity
    val pendingIntent = getPendingIntent(context, Intent(context, HomepageActivity::class.java), appWidgetId, "")

    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.comui_app_widget).apply {
        val widgetText = context.getString(R.string.comui_app_widget_text)
        setTextViewText(R.id.appwidget_text, widgetText)
        setOnClickPendingIntent(R.id.appwidget_text, pendingIntent)
    }

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
