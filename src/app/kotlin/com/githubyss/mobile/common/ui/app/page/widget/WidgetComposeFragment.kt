package com.githubyss.mobile.common.ui.app.page.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.fragment.app.viewModels
import com.githubyss.common.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.kit.util.currentDatetimeString
import com.githubyss.mobile.common.res.common.dimen.*
import com.githubyss.mobile.common.ui.app.ui.ButtonClickDefault
import com.githubyss.mobile.common.ui.app.widget.widget_schedule.ScheduleWidgetVm
import com.githubyss.mobile.common.ui.page.compose.PagePadding
import com.githubyss.mobile.common.ui.toolbar.compose.TopNavigationBar


/**
 * WidgetComposeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/28 23:29:42
 */
class WidgetComposeFragment : BaseComposeToolbarFragment() {

    /** ****************************** Object ****************************** */

    /**  */
    companion object {
        val TAG: String = WidgetComposeFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val widgetVm by viewModels<WidgetVm>()
    private val scheduleWidgetVm by viewModels<ScheduleWidgetVm>()


    /** ****************************** Override ****************************** */

    /**  */
    @Composable
    override fun Toolbar() {
        TopNavigationBar(widgetVm.title) { activity?.onBackPressed() }
    }

    /**  */
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
        ButtonClickDefault(text = "切换日期") {
            scheduleWidgetVm.changeDate()
        }
    }
}
