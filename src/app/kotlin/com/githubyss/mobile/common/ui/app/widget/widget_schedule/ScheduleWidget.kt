package com.githubyss.mobile.common.ui.app.widget.widget_schedule

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import androidx.annotation.IdRes
import com.githubyss.common.base.app_widget.classical.BaseAppWidget
import com.githubyss.mobile.common.kit.util.*
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.homepage.HomepageActivity


/**
 * ScheduleWidget
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/24 23:09:49
 */
class ScheduleWidget : BaseAppWidget() {

    /** ****************************** Properties ****************************** */

    /**  */
    private val scheduleWidgetVm by lazy { ScheduleWidgetVm() }


    /** ****************************** Override ****************************** */

    /**  */
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        scheduleWidgetVm.setupDate()

        /** 遍历所有的 appWidgetIds 更新组件 */
        appWidgetIds.forEach { appWidgetId ->
            updateWidget(context, appWidgetManager, appWidgetId)
        }
    }

    /**  */
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        refreshWidget(context, intent)
    }


    /** ****************************** Functions ****************************** */

    /**  */
    private fun updateWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        logD(TAG, "appWidgetId: $appWidgetId")

        // 获取 Widget 的组件名
        val widgetComponentName = ComponentName(context, ScheduleWidget::class.java)

        // 把 Widget 绑定到 RemoteViewsService
        val listIntent = Intent(context, ScheduleWidgetListViewService::class.java).apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        }

        // 列表点击事件
        // val itemIntent = Intent(context, ScheduleWidget::class.java).apply {
        //     // 设置 Action，方便在 onReceive 中区别点击事件
        //     action = "item"
        //     data = Uri.parse(this.toUri(Intent.URI_INTENT_SCHEME))
        // }
        // val itemPendingIntent = PendingIntent.getBroadcast(context, 0, itemIntent, PendingIntent.FLAG_MUTABLE)

        // 列表点击事件
        val itemIntent = context.getIntent<ScheduleWidget>()?.apply {
            // 设置 Action，方便在 onReceive 中区别点击事件
            action = "item"
            // 这是为了让 intent 能够带上 extras 数据一起传递，否则在 intent 的比较的过程中会被忽略掉。
            data = Uri.parse(this.toUri(Intent.URI_INTENT_SCHEME))
        }
        val itemPendingIntent = context.getPendingIntent<ScheduleWidget>(itemIntent, 0, "")

        // 界面点击事件
        // val iconIntent = Intent(context, ScheduleWidget::class.java).apply {
        //     action = "icon"
        // }
        // val iconPendingIntent = PendingIntent.getBroadcast(context, 0, iconIntent, PendingIntent.FLAG_MUTABLE)

        // 图标点击事件
        val iconPendingIntent = context.getPendingIntent<ScheduleWidget>("icon", 0, "")

        // 布局点击事件
        val containerPendingIntent = context.getIntent<HomepageActivity>().getPendingIntent<HomepageActivity>(context, 0, "")

        // 创建一个 RemoteViews
        val remoteViews = RemoteViews(context.packageName, R.layout.comui_widget_schedule).apply {
            // 设置文本
            setTextViewText(R.id.text_date, scheduleWidgetVm.date)
            setTextViewText(R.id.text_weekday, scheduleWidgetVm.weekday.value)

            // 设置列表适配器
            setRemoteAdapter(R.id.list_schedule, listIntent)
            // 设置当显示的 list 为空时，显示的 View
            setEmptyView(R.id.list_schedule, R.id.layout_schedule_none)

            // 设置列表点击事件
            // listView 使用 setPendingIntentTemplate 方法，当你点击 ListView 中的任何一个 item 时都会发送 itemPendingIntent，而在我们的 RemoteViewsService 的 RemoteViewsFactory 中的 getViewAt() 方法中，为每一个 item 都设置了一个 intent。
            setPendingIntentTemplate(R.id.list_schedule, itemPendingIntent)

            // 设置图标点击事件
            setOnClickPendingIntent(R.id.image_schedule, iconPendingIntent)

            // 设置布局点击事件
            setOnClickPendingIntent(R.id.layout_container, containerPendingIntent)
        }

        // 更新 Widget
        // appWidgetManager.updateAppWidget(widgetComponentName, remoteViews)
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
    }

    /**  */
    private fun refreshWidget(context: Context?, intent: Intent?) {
        context ?: return
        intent ?: return

        when (intent.action) {
            "date" -> {

            }
            "item" -> {
                logD(TAG, "点击了项目")
            }
            "icon" -> {
                ScheduleDataCenter.scheduleList.add(ScheduleData("12:00", "一个时间"))
                context.refreshWidget<ScheduleWidget>(R.id.list_schedule)
            }
        }
    }
}
