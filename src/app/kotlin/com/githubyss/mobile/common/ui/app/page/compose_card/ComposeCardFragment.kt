package com.githubyss.mobile.common.ui.app.page.compose_card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.viewModels
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.res.common.color.White12Pct
import com.githubyss.mobile.common.res.common.dimen.*
import com.githubyss.mobile.common.ui.button_click.compose.ButtonTextClickBlue
import com.githubyss.mobile.common.ui.card.compose.CardCommon
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
        CardCommon(
            shape = RoundedCornerShape(Dp.CornerRadiusTiny),
            border = BorderStroke(Dp.BorderWidthThin, Color.Blue),
            background = Color.Transparent,
            elevation = Dp.ElevationNone,
            marginTop = Dp.SideNormal,
            paddingStart = Dp.SideTiny,
            paddingTop = Dp.SideTiny,
            paddingEnd = Dp.SideTiny,
            paddingBottom = Dp.SideTiny,
            height = 100.dp,
            isFillMaxWidth = true,
        )
        {
            CardContent()
        }

        CardOrange(
            marginTop = Dp.SideNormal,
            marginBottom = Dp.SideNormal,
            paddingStart = Dp.SideTiny,
            paddingTop = Dp.SideTiny,
            paddingEnd = Dp.SideTiny,
            paddingBottom = Dp.SideTiny,
            height = 100.dp,
            isFillMaxWidth = true,
        )
        {
            CardContent()
        }

        CardWhite(
            shape = RoundedCornerShape(Dp.CornerRadiusBig),
            marginTop = Dp.SideNormal,
            marginBottom = Dp.SideNormal,
            isFillMaxWidth = true,
        )
        {
            CardContent()
        }

        CardTransparent(
            shape = RoundedCornerShape(Dp.CornerRadiusHuge),
            marginTop = Dp.SideNormal,
            marginBottom = Dp.SideNormal,
            isFillMaxWidth = true,
        )
        {
            CardContent()
        }
    }

    @Composable
    private fun CardContent() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White12Pct),
        )
        {
            val (button) = createRefs()
            ButtonTextClickBlue(
                text = "自适应宽，默认高，无外间距，无内间距",
                modifier = Modifier
                    .constrainAs(button) {
                        centerTo(parent)
                    }
            )
        }
    }
}
