package com.githubyss.mobile.common.ui.app.widget.widget_schedule

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.ui.R
import kotlin.collections.ArrayList


/**
 * ScheduleListViewService
 * 一个远程的服务适配器，可以请求 RemoteViews，管理 RemoteViews 的服务。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/26 13:38:17
 */
class ScheduleListViewService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return ListRemoteViewsFactory(this.applicationContext, intent)
    }
}

/**
 * ListRemoteViewsFactory
 * 提供了 RemoteViewsFactory 用于填充远程集合视图。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/26 13:37:06
 */
private class ListRemoteViewsFactory(val context: Context, intent: Intent?) : RemoteViewsService.RemoteViewsFactory {

    /** ****************************** Properties ****************************** */

    /**  */
    val TAG = ListRemoteViewsFactory::class.java.simpleName

    /** 得到原来的 WidgetId */
    var appWidgetId = Integer.valueOf(intent?.data?.schemeSpecificPart) - ScheduleWidget().m

    /**  */
    private val scheduleList = ArrayList<ScheduleData>()


    /** ****************************** Override ****************************** */

    /**  */
    override fun onCreate() {
        scheduleList.addAll(buildScheduleData())
    }

    /** 是用于更新 ListView中 的数据的 */
    override fun onDataSetChanged() {
        scheduleList.clear()
        scheduleList.addAll(buildScheduleData())
    }

    /**  */
    override fun onDestroy() {}

    /**  */
    override fun getCount(): Int {
        return scheduleList.size
    }

    /** 用于填充子 View */
    override fun getViewAt(position: Int): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.comui_widget_schedule_list_item)
        val schedule = scheduleList[position]
        views.setTextViewText(R.id.text_time, schedule.time)
        views.setTextViewText(R.id.text_todo, schedule.todo)
        logD(TAG, schedule.toString())
        return views
    }

    /**
     * 在更新界面的时候如果耗时就会显示正在加藏...的默认字样，但是你可以更改这个界面
     * 如果返回 null 显示默认界面
     * 否则加截自定义的，返回 RemoteViews
     */
    override fun getLoadingView(): RemoteViews? {
        return null
    }

    /**  */
    override fun getViewTypeCount(): Int {
        return 1
    }

    /**  */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**  */
    override fun hasStableIds(): Boolean {
        return false
    }


    /** ****************************** Functions ****************************** */

    /**  */
    private fun buildScheduleData(): ArrayList<ScheduleData> {
        val scheduleList = ArrayList<ScheduleData>()
        scheduleList.add(ScheduleData("10:30", "合肥-青岛三日自驾游"))
        scheduleList.add(ScheduleData("11:30", "小川洋风料理"))
        return scheduleList
    }
}
