package com.githubyss.mobile.common.ui.app.page.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.viewModels
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.res.common.dimen.*
import com.githubyss.mobile.common.ui.button_click.compose.ButtonTextClickBlue
import com.githubyss.mobile.common.ui.card.compose.CardCommon
import com.githubyss.mobile.common.ui.card.compose.CardOrange
import com.githubyss.mobile.common.ui.card.compose.CardTransparent
import com.githubyss.mobile.common.ui.card.compose.CardWhite
import com.githubyss.mobile.common.ui.page.compose.PagePadding
import com.githubyss.mobile.common.ui.toolbar.compose.TopNavigationBar


/**
 * CardComposeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/06/20 15:47:27
 */
class CardComposeFragment : BaseComposeToolbarFragment() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = CardComposeFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    private val cardComposeVm: CardComposeViewModel by viewModels()


    /** ****************************** Override ****************************** */

    /**  */
    @Composable
    override fun Toolbar() {
        TopNavigationBar(cardComposeVm.title) { activity?.onBackPressed() }
    }

    /**  */
    @Composable
    override fun Content() {
        PagePadding(
            paddingTop = Dp.SpaceNormal,
            paddingBottom = Dp.SpaceNormal,
        )
        {
            Cards()
        }
    }


    /** ****************************** Functions ****************************** */

    /**  */
    @Composable
    private fun Cards() {
        CardCommon(
            shape = RoundedCornerShape(Dp.CornerRadiusTiny),
            border = BorderStroke(Dp.BorderWidthThin, Color.Transparent),
            cardBackgroundColor = Color.Red,
            cardContentColor = Color.Yellow,
            marginTop = Dp.SpaceSideMarginDefault,
            paddingTop = Dp.SpaceCardPaddingDefault,
            paddingBottom = Dp.SpaceCardPaddingDefault,
            paddingStart = Dp.SpaceCardPaddingDefault,
            paddingEnd = Dp.SpaceCardPaddingDefault,
            height = 100.dp,
            isFillMaxWidth = true,
            elevation = Dp.ElevationNone,
        )
        {
            CardContent()
        }

        CardOrange(
            marginTop = Dp.SpaceSideMarginDefault,
            marginBottom = Dp.SpaceSideMarginDefault,
            paddingTop = Dp.SpaceCardPaddingDefault,
            paddingBottom = Dp.SpaceCardPaddingDefault,
            paddingStart = Dp.SpaceCardPaddingDefault,
            paddingEnd = Dp.SpaceCardPaddingDefault,
            height = 100.dp,
            isFillMaxWidth = true,
        )
        {
            CardContent()
        }

        CardWhite(
            shape = RoundedCornerShape(Dp.CornerRadiusBig),
            marginTop = Dp.SpaceSideMarginDefault,
            marginBottom = Dp.SpaceSideMarginDefault,
            isFillMaxWidth = true,
        )
        {
            CardContent()
        }

        CardTransparent(
            shape = RoundedCornerShape(Dp.CornerRadiusHuge),
            marginTop = Dp.SpaceSideMarginDefault,
            marginBottom = Dp.SpaceSideMarginDefault,
            isFillMaxWidth = true,
        )
        {
            CardContent()
        }
    }

    /**  */
    @Composable
    private fun CardContent() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Transparent),
        )
        {
            val (text, button) = createRefs()
            Text(text = "这个是卡片内容",
                 modifier = Modifier
                     .constrainAs(text) {
                         top.linkTo(parent.top)
                         bottom.linkTo(button.top)
                         start.linkTo(parent.start)
                         end.linkTo(parent.end)
                     }
            )
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
