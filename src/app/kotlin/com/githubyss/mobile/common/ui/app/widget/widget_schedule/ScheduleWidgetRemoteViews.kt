package com.githubyss.mobile.common.ui.app.widget.widget_schedule

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.LayoutRes
import com.githubyss.common.base.app_widget.classical.BaseAppWidgetRemoteViews
import com.githubyss.mobile.common.kit.util.getIntent
import com.githubyss.mobile.common.kit.util.getPendingIntent
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.widget.WidgetComposeActivity


/**
 * ScheduleWidgetRemoteViews
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/07 10:09:36
 */
class ScheduleWidgetRemoteViews(context: Context?, @LayoutRes layoutId: Int = R.layout.comui_widget_schedule) : BaseAppWidgetRemoteViews(context, layoutId) {
    init {
        // 设置文本
        setTextViewText(R.id.text_date, ScheduleDataCenter.datetime)
        setTextViewText(R.id.text_weekday, ScheduleDataCenter.weekday)


        // 把 Widget 绑定到 RemoteViewsService
        val listIntent = Intent(context, ScheduleWidgetListViewService::class.java).apply {
            // putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        }
        // 设置列表适配器
        setRemoteAdapter(R.id.list_schedule, listIntent)
        // 设置当显示的 list 为空时，显示的 View
        setEmptyView(R.id.list_schedule, R.id.layout_schedule_none)


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
        val itemPendingIntent = context.getPendingIntent<ScheduleWidget>(itemIntent)
        // 设置列表点击事件
        // listView 使用 setPendingIntentTemplate 方法，当你点击 ListView 中的任何一个 item 时都会发送 itemPendingIntent，而在我们的 RemoteViewsService 的 RemoteViewsFactory 中的 getViewAt() 方法中，为每一个 item 都设置了一个 intent。
        setPendingIntentTemplate(R.id.list_schedule, itemPendingIntent)


        /** Intent 传入 Widget 实例，意图为发送广播 */

        // 日期点击事件
        val dateIntent = Intent(context, ScheduleWidget::class.java).apply {
            action = "datetime"
        }
        val flag = when {
            Build.VERSION.SDK_INT >= 31 -> PendingIntent.FLAG_MUTABLE
            else -> PendingIntent.FLAG_UPDATE_CURRENT
        }
        val datePendingIntent = PendingIntent.getBroadcast(context, 0, dateIntent, flag)
        // 设置日期点击事件
        setOnClickPendingIntent(R.id.text_date, datePendingIntent)


        // 图标点击事件、设置图标点击事件
        val listPendingIntent = context.getPendingIntent<ScheduleWidget>("list")
        setOnClickPendingIntent(R.id.image_schedule_list, listPendingIntent)

        val deletePendingIntent = context.getPendingIntent<ScheduleWidget>("delete")
        setOnClickPendingIntent(R.id.image_schedule_delete, deletePendingIntent)


        /** Intent 传入 Activity 实例，意图为跳转页面 */

        // 布局点击事件
        val containerPendingIntent = context.getPendingIntent<WidgetComposeActivity>()
        // 设置布局点击事件
        setOnClickPendingIntent(R.id.layout_container, containerPendingIntent)
    }
}
