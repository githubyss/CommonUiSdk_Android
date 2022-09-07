package com.githubyss.mobile.common.ui.app.widget.widget_schedule

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.githubyss.common.base.app_widget.classical.BaseAppWidgetRemoteViewsFactory
import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.ui.R


/**
 * ScheduleWidgetListRemoteViewsFactory
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/26 13:37:06
 */
class ScheduleWidgetListRemoteViewsFactory(val context: Context, intent: Intent?) : BaseAppWidgetRemoteViewsFactory<ScheduleData>() {

    /** ****************************** Properties ****************************** */

    /**  */
    val TAG = ScheduleWidgetListRemoteViewsFactory::class.java.simpleName

    /** 得到原来的 WidgetId */
    var appWidgetId = intent?.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)

    private val scheduleList by lazy { ArrayList<ScheduleData>() }


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCreate() {
        super.onCreate()
        scheduleList.addAll(ScheduleDataCenter.scheduleList)
    }

    /**  */
    override fun onDataSetChanged() {
        super.onDataSetChanged()
        scheduleList.clear()
        scheduleList.addAll(ScheduleDataCenter.scheduleList)
    }

    /**  */
    override fun onDestroy() {
        scheduleList.clear()
        ScheduleDataCenter.clearScheduleList()
        super.onDestroy()
    }

    /**  */
    override fun getCount(): Int {
        super.getCount()
        return scheduleList.size
    }

    /**  */
    override fun getViewAt(position: Int): RemoteViews? {
        super.getViewAt(position)

        if (position < 0 || position >= count) return null

        val schedule = scheduleList[position]
        logD(TAG, "position: $position, schedule: $schedule")

        // 创建在当前索引位置要显示的 View
        val remoteViews = RemoteViews(context.packageName, R.layout.comui_widget_schedule_list_item)
        // 设置要显示的内容
        remoteViews.setTextViewText(R.id.text_time, schedule.time)
        remoteViews.setTextViewText(R.id.text_todo, schedule.todo)

        //填充 Intent，填充在 AppWidgetProvider 中创建的 PendingIntent
        val fillInIntent = Intent()
        // 传入点击行的数据
        fillInIntent.putExtra("content", schedule)
        // remoteViews 的 setOnClickFillInIntent() 是将 fillInIntent 合并到现有的 itemPendingIntent 中去。就是两个 intent 合并了。
        remoteViews.setOnClickFillInIntent(R.id.layout_root, fillInIntent)

        return remoteViews
    }
}
