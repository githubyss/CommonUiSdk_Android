package com.githubyss.mobile.common.ui.app.page.compose_card

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.res.common.dimen.SideNormal
import com.githubyss.mobile.common.ui.button_click.compose.ButtonClickBlue
import com.githubyss.mobile.common.ui.card.compose.CardOrange
import com.githubyss.mobile.common.ui.card.compose.CardTransparent
import com.githubyss.mobile.common.ui.card.compose.CardWhite
import com.githubyss.mobile.common.ui.page.compose.PageSidePadding
import com.githubyss.mobile.common.ui.toolbar.compose.TopNavigationBar


/**
 * ComposeCardFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/06/20 15:47:27
 */
class ComposeCardFragment : BaseComposeToolbarFragment() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = ComposeCardFragment::class.java.simpleName
    }

    private val composeCardVm: ComposeCardViewModel by viewModels()


    /** ****************************** Override ****************************** */

    @Composable
    override fun Toolbar() {
        TopNavigationBar(composeCardVm.title) { activity?.onBackPressed() }
    }

    @Composable
    override fun Content() {
        PageSidePadding(paddingVertical = Dp.SideNormal)
        {
            Cards()
        }
    }


    /** ****************************** Functions ****************************** */

    @Composable
    private fun Cards() {
        CardOrange(
            height = 100.dp,
            isFillMaxWidth = true,
        )
        {
            ButtonClickBlue(text = "XXXXXXXXXXXXXXXXXXXXX")
        }

        CardWhite(
            height = 100.dp,
            isFillMaxWidth = true,
        )
        {
        }

        CardTransparent(
            height = 100.dp,
            isFillMaxWidth = true,
        )
        {
        }
    }
}
