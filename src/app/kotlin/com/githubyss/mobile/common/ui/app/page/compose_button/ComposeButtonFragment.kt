package com.githubyss.mobile.common.ui.app.page.compose_button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.res.common.color.White00Pct
import com.githubyss.mobile.common.res.common.dimen.*
import com.githubyss.mobile.common.ui.button_click.compose.*
import com.githubyss.mobile.common.ui.layout.compose.LayoutWeightHorizontal
import com.githubyss.mobile.common.ui.page.compose.PageSidePadding
import com.githubyss.mobile.common.ui.toolbar.compose.TopNavigationBar


/**
 * ComposeButtonFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/04/06 10:14:17
 */
class ComposeButtonFragment : BaseComposeToolbarFragment() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = ComposeButtonFragment::class.java.simpleName
    }

    private val composeButtonVm: ComposeButtonViewModel by viewModels()


    /** ****************************** Override ****************************** */

    @Composable
    override fun Toolbar() {
        TopNavigationBar(composeButtonVm.title) { activity?.onBackPressed() }
    }

    @Composable
    override fun Content() {
        PageSidePadding(paddingVertical = Dp.SideNormal)
        {
            Buttons()
        }
    }


    /** ****************************** Functions ****************************** */

    @Composable
    private fun Buttons() {
        val interactionSource = remember { MutableInteractionSource() }
        ButtonClickCommon(
            interactionSource = interactionSource,
            shape = RoundedCornerShape(Dp.CornerRadiusBig),
            border = BorderStroke(Dp.BorderWidthThin, Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Yellow, contentColor = Color.DarkGray,
                disabledBackgroundColor = Color.Gray, disabledContentColor = Color.White00Pct),
            paddingHorizontal = Dp.SideTiny,
            paddingVertical = Dp.SideTiny,
            isFillMaxWidth = true,
            height = 0.dp,
        )
        {
            Column {
                Row {
                    Icon(Icons.Filled.AccountBox, null)
                    Text(
                        text = "文案",
                        fontSize = TextUnit.FontSizeNormal,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .background(Color.Transparent),
                    )
                }
                Checkbox(
                    checked = true,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .background(Color.Transparent),
                    onCheckedChange = {}
                )
            }
        }

        ButtonTextClickBlue(
            text = "自适应宽，自适应高，默认外间距，无内间距",
            marginHorizontal = Dp.SideNano,
            marginVertical = Dp.SideNano,
        )

        ButtonTextClickBluePadding(text = "自适应宽，自适应高，无外间距，默认内间距")

        ButtonTextClickBlueMarginPadding(text = "自适应宽，自适应高，默认外间距，默认内间距")

        ButtonTextClickBlue(
            text = "满宽，自适应高，默认外间距，无内间距",
            marginHorizontal = Dp.SideNano,
            marginVertical = Dp.SideNano,
            isFillMaxWidth = true,
        )

        ButtonTextClickBluePadding(
            text = "满宽，自适应高，无外间距，默认内间距",
            isFillMaxWidth = true,
        )

        ButtonTextClickBlueWeightHorizontal(text = "等分宽，自适应高，无外间距，无内间距")

        ButtonTextClickBlueWeightHorizontalMarginPadding(text = "等分宽，自适应高，默认外间距，默认内间距")

        LayoutWeightHorizontal {
            ButtonTextClickBlueWeightHorizontal(
                text = "1/3宽，自适应高，无外间距，无内间距",
                modifier = Modifier.weight(1F),
            )
            ButtonTextClickBlueWeightHorizontal(
                text = "2/3宽，自适应高，无外间距，无内间距",
                modifier = Modifier.weight(2F),
            )
        }
    }
}
