package com.githubyss.common.ui.app.widget.widget_schedule

import android.content.Intent
import android.widget.RemoteViewsService


/**
 * ScheduleWidgetListRemoteViewsService
 * 一个远程的服务适配器，可以请求 RemoteViews，管理 RemoteViews 的服务。
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/26 13:38:17
 */
class ScheduleWidgetListRemoteViewsService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return ScheduleWidgetListRemoteViewsFactory(this.applicationContext, intent)
    }
}
