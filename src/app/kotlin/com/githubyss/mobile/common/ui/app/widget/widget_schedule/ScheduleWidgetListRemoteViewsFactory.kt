package com.githubyss.mobile.common.ui.app.widget.widget_schedule

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.ui.R


/**
 * ScheduleWidgetListRemoteViewsFactory
 * 提供了 RemoteViewsFactory 用于填充远程集合视图。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/26 13:37:06
 */
class ScheduleWidgetListRemoteViewsFactory(val context: Context, intent: Intent?) : RemoteViewsService.RemoteViewsFactory {

    /** ****************************** Properties ****************************** */

    /**  */
    val TAG = ScheduleWidgetListRemoteViewsFactory::class.java.simpleName

    /** 得到原来的 WidgetId */
    var appWidgetId = intent?.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)


    /** ****************************** Override ****************************** */

    /**
     * RemoteViewsFactory 调用时执行，这个方法执行时间超过 20 秒会报错。
     * 如果耗时长的任务应该在 onDataSetChanged 或者 getViewAt 中处理
     */
    override fun onCreate() {
        ScheduleDataCenter.scheduleList.addAll(ScheduleDataCenter.scheduleList)
    }

    /**
     * 当调 notifyAppWidgetViewDataChanged 方法时，触发这个方法。
     * 例如：AppWidgetManager.getInstance(context).notifyAppWidgetViewDataChanged(appWidgetIds, viewId)
     */
    override fun onDataSetChanged() {
        ScheduleDataCenter.scheduleList.addAll(ScheduleDataCenter.scheduleList)
    }

    /** 这里写情理资源，释放内存的操作 */
    override fun onDestroy() {
        ScheduleDataCenter.scheduleList.clear()
    }

    /** 返回集台数量 */
    override fun getCount(): Int {
        return ScheduleDataCenter.scheduleList.size
    }

    /** 创建并且填充，在指定索引位置显示 View，这个和 BaseAdapter 的 getView 类似 */
    override fun getViewAt(position: Int): RemoteViews? {
        if (position < 0 || position >= count) return null

        val schedule = ScheduleDataCenter.scheduleList[position]
        logD(TAG, "position: $position, schedule: $schedule")

        // 创建在当前索引位置要显示的 View
        val remoteViews = RemoteViews(context.packageName, R.layout.comui_widget_schedule_list_item)
        // 设置要显示的内容
        remoteViews.setTextViewText(R.id.text_time, schedule.time)
        remoteViews.setTextViewText(R.id.text_todo, schedule.todo)

        //填充 Intent，填充在 AppWidgetProvider 中创建的 PendingIntent
        val intent = Intent()
        // 传入点击行的数据
        intent.putExtra("content", schedule)
        remoteViews.setOnClickFillInIntent(R.id.layout_root, intent)

        return remoteViews
    }

    /**
     * 在更新界面的时候如果耗时就会显示正在加藏...的默认字样，但是你可以更改这个界面
     * 如果返回 null 显示默认界面
     * 否则加截自定义的，返回 RemoteViews
     */
    override fun getLoadingView(): RemoteViews? {
        return null
    }

    /** 不同 View 定义的数量。默认为 1 */
    override fun getViewTypeCount(): Int {
        return 1
    }

    /** 返回当前的索引。 */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /** 如果每个项提供的 ID 是稳定的，即它们不会在运行时改变，就返回 true (没用过。。。) */
    override fun hasStableIds(): Boolean {
        return false
    }
}
