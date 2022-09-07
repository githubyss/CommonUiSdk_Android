package com.githubyss.mobile.common.ui.app.widget.widget_schedule

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import com.githubyss.common.base.app_widget.classical.BaseAppWidget
import com.githubyss.mobile.common.kit.util.*
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.widget.WidgetComposeActivity


/**
 * ScheduleWidget
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/24 23:09:49
 */
class ScheduleWidget : BaseAppWidget() {

    /** ****************************** Properties ****************************** */


    /** ****************************** Override ****************************** */

    /**  */
    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        ScheduleDataCenter.setupDate()
    }

    /**  */
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        // 创建一个 RemoteViews
        val remoteViews = ScheduleWidgetRemoteViews(context)

        /** 根据 WidgetIds 刷新 WidgetView */
        // context.refreshAppWidgetView(appWidgetIds, remoteViews)
        // context.refreshAppWidgetViewByIds<ScheduleWidget>(remoteViews)

        /** 根据 WidgetComponentName 刷新 WidgetView */
        // context.refreshAppWidgetView(ComponentName(context, ScheduleWidget::class.java), remoteViews)
        context.refreshAppWidgetViewByComponentName<ScheduleWidget>(remoteViews)

        /** 遍历 WidgetIds 刷新 WidgetView */
        // appWidgetIds.forEach { appWidgetId ->
        //     logD(TAG, "appWidgetId: $appWidgetId")
        //
        //     // 根据 WidgetId 刷新 WidgetView
        //     context.refreshAppWidgetView(appWidgetId, remoteViews)
        // }
    }

    /**  */
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        context ?: return
        intent ?: return

        val remoteViews = ScheduleWidgetRemoteViews(context)

        when (intent.action) {
            "item" -> {
                logD(TAG, "点击了项目")
            }
            "datetime" -> {
                ScheduleDataCenter.changeDatetime()
                context.refreshAppWidgetViewByComponentName<ScheduleWidget>(remoteViews)
            }
            "list" -> {
                ScheduleDataCenter.buildScheduleList()
                context.refreshAppWidgetList<ScheduleWidget>(R.id.list_schedule)
            }
            "delete" -> {
                ScheduleDataCenter.clearScheduleList()
                context.refreshAppWidgetList<ScheduleWidget>(R.id.list_schedule)
            }
        }
    }


    /** ****************************** Functions ****************************** */

    /**  */
}
