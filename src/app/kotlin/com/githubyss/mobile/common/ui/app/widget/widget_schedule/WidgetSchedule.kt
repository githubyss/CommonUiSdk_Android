package com.githubyss.mobile.common.ui.app.widget.widget_schedule

import android.app.Activity
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEARCH
import android.os.Build
import android.widget.RemoteViews
import com.githubyss.mobile.common.kit.util.getIntentByActivity
import com.githubyss.mobile.common.kit.util.getPendingIntent
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.homepage.HomepageActivity


/**
 * WidgetSchedule
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/24 23:09:49
 */
class WidgetSchedule : AppWidgetProvider() {

    /** ****************************** Properties ****************************** */

    /**  */
    val widgetScheduleVm by lazy { WidgetScheduleVm() }


    /** ****************************** Override ****************************** */

    /**  */
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        widgetScheduleVm.setupDate()
        appWidgetIds.forEach { appWidgetId ->
            updateWidgetSchedule(context, appWidgetManager, appWidgetId)
        }
    }

    /**  */
    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    /**  */
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }

    /**  */
    override fun onEnabled(context: Context) {
    }

    /**  */
    override fun onDisabled(context: Context) {
    }

    /**  */
    private fun updateWidgetSchedule(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val views = RemoteViews(context.packageName, R.layout.comui_widget_schedule).apply {
            setTextViewText(R.id.text_date, widgetScheduleVm.date.value)
            setTextViewText(R.id.text_weekday, widgetScheduleVm.weekday.value)
            setOnClickPendingIntent(R.id.layout_container, context.getIntentByActivity<HomepageActivity>().getPendingIntent(context, R.id.layout_container, ""))
        }

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}
