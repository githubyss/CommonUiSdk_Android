package com.githubyss.common.ui.app.page.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.githubyss.common.base.activity_fragment.compose.BaseComposeToolbarActivity
import com.githubyss.common.kit.util.getStringFromRes
import com.githubyss.common.kit.util.refreshAppWidgetList
import com.githubyss.common.kit.util.refreshAppWidgetViewByComponentName
import com.githubyss.common.res.common.dimen.*
import com.githubyss.common.ui.R
import com.githubyss.common.ui.app.ui.ButtonClickDefault
import com.githubyss.common.ui.app.widget.widget_schedule.ScheduleData
import com.githubyss.common.ui.app.widget.widget_schedule.ScheduleDataCenter
import com.githubyss.common.ui.app.widget.widget_schedule.ScheduleWidget
import com.githubyss.common.ui.app.widget.widget_schedule.ScheduleWidgetRemoteViews
import com.githubyss.common.ui.layout.compose.LayoutWeightHorizontal
import com.githubyss.common.ui.page.compose.PagePadding
import com.githubyss.common.ui.text.compose.TextCommon
import com.githubyss.common.ui.toolbar.compose.TopNavigationBar


/**
 * WidgetComposeActivity
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/28 23:29:42
 */
class WidgetComposeActivity : BaseComposeToolbarActivity() {

    /** ****************************** Object ****************************** */

    /**  */
    companion object {
        val TAG: String = WidgetComposeActivity::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */


    /** ****************************** Override ****************************** */

    /**  */
    @Composable
    override fun Toolbar() {
        TopNavigationBar(getStringFromRes(R.string.comui_time_countdown_title)) { onBackPressed() }
    }

    /**  */
    @Preview
    @Composable
    override fun Content() {
        PagePadding(
            paddingTop = Dp.SpaceNormal,
            paddingBottom = Dp.SpaceNormal,
        )
        {
            ChangeWidgetContent()
        }
    }


    /** ****************************** Functions ****************************** */

    /**  */
    @Composable
    private fun ChangeWidgetContent() {
        TextCommon(text = "日程 Widget")
        LayoutWeightHorizontal {
            ButtonClickDefault(text = "切换日期", modifier = Modifier.weight(1F)) {
                ScheduleDataCenter.changeDatetime()
                val remoteViews = ScheduleWidgetRemoteViews(this@WidgetComposeActivity)
                refreshAppWidgetViewByComponentName<ScheduleWidget>(remoteViews)

                // val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
                // intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, this@WidgetComposeActivity.getAppWidgetIds<ScheduleWidget>())
                // this@WidgetComposeActivity.sendBroadcast(intent)
            }
            ButtonClickDefault(text = "更新列表项", modifier = Modifier.weight(1F)) {
                ScheduleDataCenter.buildScheduleList()
                refreshAppWidgetList<ScheduleWidget>(R.id.list_schedule)
            }
            ButtonClickDefault(text = "清空列表", modifier = Modifier.weight(1F)) {
                ScheduleDataCenter.clearScheduleList()
                refreshAppWidgetList<ScheduleWidget>(R.id.list_schedule)
            }
        }

        TextCommon(text = "播放器 Widget", marginTop = Dp.SpaceNormal)
        LayoutWeightHorizontal {
            ButtonClickDefault(text = "更新列表项", modifier = Modifier.weight(1F)) {
                ScheduleDataCenter.scheduleList.add(ScheduleData("12:00", "一个时间"))
                refreshAppWidgetList<ScheduleWidget>(R.id.list_schedule)
            }
            ButtonClickDefault(text = "清空列表", modifier = Modifier.weight(1F)) {
                ScheduleDataCenter.clearScheduleList()
                refreshAppWidgetList<ScheduleWidget>(R.id.list_schedule)
            }
        }
    }
}
