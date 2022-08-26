package com.githubyss.mobile.common.ui.app.widget.widget_schedule

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import com.githubyss.mobile.common.kit.util.getIntentByActivity
import com.githubyss.mobile.common.kit.util.getPendingIntent
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.homepage.HomepageActivity


/**
 * ScheduleWidget
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/24 23:09:49
 */
class ScheduleWidget : AppWidgetProvider() {

    /** ****************************** Properties ****************************** */

    /**  */
    private val scheduleWidgetVm by lazy { ScheduleWidgetVm() }
    var m = (0..1000).random() // 随机数

    /** ****************************** Override ****************************** */

    /**  */
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        scheduleWidgetVm.setupDate()
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
        val intent = Intent(context, ScheduleListViewService::class.java).apply {
            data = Uri.fromParts("content", appWidgetId.toString() + m.toString(), null)
        }
        val views = RemoteViews(context.packageName, R.layout.comui_widget_schedule).apply {
            setTextViewText(R.id.text_date, scheduleWidgetVm.date.value)
            setTextViewText(R.id.text_weekday, scheduleWidgetVm.weekday.value)
            setRemoteAdapter(R.id.list_schedule, intent)
            setOnClickPendingIntent(R.id.layout_container, context.getIntentByActivity<HomepageActivity>().getPendingIntent(context, R.id.layout_container, ""))
        }

        // val componentName = ComponentName(context, ScheduleWidget::class.java)
        // appWidgetManager.updateAppWidget(componentName, views)
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}
