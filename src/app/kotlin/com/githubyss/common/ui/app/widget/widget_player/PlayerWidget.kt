package com.githubyss.common.ui.app.widget.widget_player

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import com.githubyss.common.base.app_widget.classical.BaseAppWidget
import com.githubyss.common.kit.util.*
import com.githubyss.common.ui.R


/**
 * PlayerWidget
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/05 16:19:13
 */
class PlayerWidget : BaseAppWidget() {

    /** ****************************** Properties ****************************** */


    /** ****************************** Override ****************************** */

    /**  */
    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        PlayDataCenter.setupDate()
    }

    /**  */
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        val remoteViews = PlayerWidgetRemoteViews(context)
        context.refreshAppWidgetView(appWidgetIds, remoteViews)
    }

    /**  */
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        context ?: return
        intent ?: return

        val remoteViews = PlayerWidgetRemoteViews(context)

        when (intent.action) {
            PlayerWidgetRemoteViews.ACTION_PLAY_LIST_ITEM -> {
                logD(TAG, "点击了项目")
            }
            PlayerWidgetRemoteViews.ACTION_TITLE -> {
                PlayDataCenter.changeTitle("XXX")
                context.refreshAppWidgetViewByComponentName<PlayerWidget>(remoteViews)
            }
            PlayerWidgetRemoteViews.ACTION_LIST -> {
                PlayDataCenter.buildPlayList()
                context.refreshAppWidgetList<PlayerWidget>(R.id.listPlay)
            }
            PlayerWidgetRemoteViews.ACTION_PREVIOUS -> {
            }
            PlayerWidgetRemoteViews.ACTION_PLAY -> {
                PlayDataCenter.clearPlayList()
                context.refreshAppWidgetList<PlayerWidget>(R.id.listPlay)
            }
            PlayerWidgetRemoteViews.ACTION_NEXT -> {
            }
        }
    }
}
