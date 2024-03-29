package com.githubyss.common.ui.app.widget.widget_player

import android.content.Intent
import android.widget.RemoteViewsService


/**
 * PlayerWidgetListRemoteViewsService
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/05 16:20:25
 */
class PlayerWidgetListRemoteViewsService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return PlayerWidgetListRemoteViewsFactory(this.applicationContext, intent)
    }
}
