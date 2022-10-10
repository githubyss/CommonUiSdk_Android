package com.githubyss.common.ui.app.widget.widget_player

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.githubyss.common.base.app_widget.classical.BaseAppWidgetRemoteViewsFactory
import com.githubyss.common.kit.util.logD
import com.githubyss.common.ui.R


/**
 * PlayerWidgetListRemoteViewsFactory
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/05 16:20:09
 */
class PlayerWidgetListRemoteViewsFactory(val context: Context, intent: Intent?) : BaseAppWidgetRemoteViewsFactory<PlayData>() {

    /** ****************************** Properties ****************************** */

    /**  */
    val TAG = PlayerWidgetListRemoteViewsFactory::class.java.simpleName

    /** 得到原来的 WidgetId */
    var appWidgetId = intent?.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)


    /** ****************************** Override ****************************** */

    /**  */
    override val dataList by lazy { ArrayList<PlayData>() }

    /**  */
    override fun setupData() {
        dataList.addAll(PlayDataCenter.playList)
    }

    /**  */
    override fun updateData() {
        dataList.clear()
        dataList.addAll(PlayDataCenter.playList)
    }

    /**  */
    override fun clearData() {
        PlayDataCenter.clearPlayList()
    }

    /**  */
    override fun refreshView(position: Int): RemoteViews? {
        if (position < 0 || position >= count) return null

        val playerData = dataList[position]
        logD(TAG, "position: $position, playerData: $playerData")

        // 创建在当前索引位置要显示的 View
        val remoteViews = RemoteViews(context.packageName, R.layout.comui_widget_player_list_item).apply {
            // 设置要显示的内容
            setTextViewText(R.id.textTitle, playerData.title)


            // 填充 Intent，填充在 AppWidgetProvider 中创建的 PendingIntent
            val fillInIntent = Intent().apply {
                // 传入点击行的数据
                putExtra("position", position)
                putExtra("content", playerData)
            }
            // remoteViews 的 setOnClickFillInIntent() 是将 fillInIntent 合并到现有的 itemPendingIntent 中去。就是两个 intent 合并了。
            setOnClickFillInIntent(R.id.layoutRoot, fillInIntent)
        }


        return remoteViews
    }
}
