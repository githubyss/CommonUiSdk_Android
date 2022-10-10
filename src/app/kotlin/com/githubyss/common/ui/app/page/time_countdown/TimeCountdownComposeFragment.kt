package com.githubyss.common.ui.app.page.time_countdown

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.viewModels
import com.githubyss.common.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.common.res.common.dimen.*
import com.githubyss.common.ui.page.compose.PagePadding
import com.githubyss.common.ui.time_view.classical.TimeCountdownView
import com.githubyss.common.ui.toolbar.compose.TopNavigationBar


/**
 * TimeCountdownComposeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/22 15:33:56
 */
class TimeCountdownComposeFragment : BaseComposeToolbarFragment() {

    /** ****************************** Object ****************************** */

    /**  */
    companion object {
        val TAG: String = TimeCountdownComposeFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val timeCountdownVm by viewModels<TimeCountdownViewModel>()


    /** ****************************** Override ****************************** */

    /**  */
    @Composable
    override fun Toolbar() {
        TopNavigationBar(timeCountdownVm.title) { activity?.onBackPressed() }
    }

    /**  */
    @Composable
    override fun Content() {
        PagePadding(
            paddingTop = Dp.SpaceNormal,
            paddingBottom = Dp.SpaceNormal,
        )
        {
            TimeCountdown()
        }
    }


    /** ****************************** Functions ****************************** */

    /**  */
    @Composable
    private fun TimeCountdown() {
        AndroidView(factory = { context ->
            TimeCountdownView(context).apply { remainingMillisecond = timeCountdownVm.remainingMillisecond.value ?: return@apply }
        })
    }
}
