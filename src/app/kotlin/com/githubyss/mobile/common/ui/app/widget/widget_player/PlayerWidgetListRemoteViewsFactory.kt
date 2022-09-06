package com.githubyss.mobile.common.ui.app.widget.widget_player

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.githubyss.common.base.app_widget.classical.BaseAppWidgetRemoteViewsFactory
import com.githubyss.mobile.common.kit.util.logD
import com.githubyss.mobile.common.ui.R


/**
 * PlayerWidgetListRemoteViewsFactory
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/05 16:20:09
 */
class PlayerWidgetListRemoteViewsFactory(val context: Context, intent: Intent?) : BaseAppWidgetRemoteViewsFactory<PlayerData>() {

    /** ****************************** Properties ****************************** */

    /**  */
    val TAG = PlayerWidgetListRemoteViewsFactory::class.java.simpleName

    /** 得到原来的 WidgetId */
    var appWidgetId = intent?.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)

    override val dataList: ArrayList<PlayerData> by lazy { ArrayList<PlayerData>() }


    /** ****************************** Override ****************************** */

    /**  */
    override fun setupData() {
        dataList.addAll(PlayerDataCenter.playList)
    }

    /**  */
    override fun updateData() {
        dataList.clear()
        dataList.addAll(PlayerDataCenter.playList)
    }

    /**  */
    override fun clearData() {
        PlayerDataCenter.clear()
    }

    /**  */
    override fun refreshView(position: Int): RemoteViews? {
        if (position < 0 || position >= count) return null

        val playerData = dataList[position]
        logD(TAG, "position: $position, playerData: $playerData")

        // 创建在当前索引位置要显示的 View
        val remoteViews = RemoteViews(context.packageName, R.layout.comui_widget_player_list_item)
        // 设置要显示的内容
        remoteViews.setTextViewText(R.id.textTitle, playerData.title)

        // 填充 Intent，填充在 AppWidgetProvider 中创建的 PendingIntent
        val fillInIntent = Intent()
        // 传入点击行的数据
        fillInIntent.putExtra("content", playerData)
        // remoteViews 的 setOnClickFillInIntent() 是将 fillInIntent 合并到现有的 itemPendingIntent 中去。就是两个 intent 合并了。
        remoteViews.setOnClickFillInIntent(R.id.layoutRoot, fillInIntent)

        return remoteViews
    }
}
