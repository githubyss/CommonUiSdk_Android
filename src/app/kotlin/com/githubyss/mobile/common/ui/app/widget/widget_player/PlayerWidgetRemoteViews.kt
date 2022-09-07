package com.githubyss.mobile.common.ui.app.widget.widget_player

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.LayoutRes
import com.githubyss.common.base.app_widget.classical.BaseAppWidgetRemoteViews
import com.githubyss.mobile.common.kit.util.getIntent
import com.githubyss.mobile.common.kit.util.getPendingIntent
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.app.page.widget.WidgetComposeActivity


/**
 * PlayerWidgetRemoteViews
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/09/07 10:55:49
 */
class PlayerWidgetRemoteViews(context: Context?, @LayoutRes layoutId: Int = R.layout.comui_widget_player) : BaseAppWidgetRemoteViews(context, layoutId) {
    companion object {
        const val ACTION_TITLE = "com.githubyss.common.ui.widget.action.TITLE"
        const val ACTION_LIST = "com.githubyss.common.ui.widget.action.LIST"
        const val ACTION_PLAY = "com.githubyss.common.ui.widget.action.PLAY"
        const val ACTION_PREVIOUS = "com.githubyss.common.ui.widget.action.PREVIOUS"
        const val ACTION_NEXT = "com.githubyss.common.ui.widget.action.NEXT"
        const val ACTION_PLAY_LIST_ITEM = "com.githubyss.common.ui.widget.action.PLAY_LIST_ITEM"
    }

    init {
        // 设置文本
        setTextViewText(R.id.textTitle, PlayDataCenter.title)


        // 把 Widget 绑定到 RemoteViewsService
        setRemoteAdapter(R.id.listPlay, Intent(context, PlayerWidgetListRemoteViewsService::class.java))
        // 设置当显示的 list 为空时，显示的 View
        setEmptyView(R.id.listPlay, R.id.layoutPlayerNone)


        // 列表点击事件
        setPendingIntentTemplate(R.id.listPlay, context.getPendingIntent<PlayerWidget>(ACTION_PLAY_LIST_ITEM))


        // 界面点击事件
        setOnClickPendingIntent(R.id.textTitle, context.getPendingIntent<PlayerWidget>(ACTION_TITLE))
        setOnClickPendingIntent(R.id.imageList, context.getPendingIntent<PlayerWidget>(ACTION_LIST))
        setOnClickPendingIntent(R.id.imagePlayerPrevious, context.getPendingIntent<PlayerWidget>(ACTION_PREVIOUS))
        setOnClickPendingIntent(R.id.imagePlayerPlay, context.getPendingIntent<PlayerWidget>(ACTION_PLAY))
        setOnClickPendingIntent(R.id.imagePlayerNext, context.getPendingIntent<PlayerWidget>(ACTION_NEXT))


        // 布局点击事件
        setOnClickPendingIntent(R.id.layoutContainer, context.getPendingIntent<WidgetComposeActivity>())
    }
}
