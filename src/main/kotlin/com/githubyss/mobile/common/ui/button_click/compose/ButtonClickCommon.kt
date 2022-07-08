package com.githubyss.mobile.common.ui.button_click.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.common.dimen.SpaceNone
import com.githubyss.mobile.common.ui.utils.modifierHeightAssemble
import com.githubyss.mobile.common.ui.utils.modifierWidthAssemble


/**
 * 可点击通用按钮
 * 可以自定义传入按钮上显示的内容
 * 无外边距，无内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonClickCommon(
    modifier: Modifier = Modifier,
    shape: Shape,
    border: BorderStroke,
    colors: ButtonColors,
    marginTop: Dp = Dp.SpaceNone,
    marginBottom: Dp = Dp.SpaceNone,
    marginStart: Dp = Dp.SpaceNone,
    marginEnd: Dp = Dp.SpaceNone,
    paddingTop: Dp = Dp.SpaceNone,
    paddingBottom: Dp = Dp.SpaceNone,
    paddingStart: Dp = Dp.SpaceNone,
    paddingEnd: Dp = Dp.SpaceNone,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    elevation: ButtonElevation = ButtonDefaults.elevation(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    // 先设置 padding，再设置长高，padding 作用等同于 margin
    val modifierButton: Modifier = modifier
        .padding(top = marginTop, bottom = marginBottom, start = marginStart, end = marginEnd)
        .modifierWidthAssemble(width, isFillMaxWidth)
        .modifierHeightAssemble(height, isFillMaxHeight)

    val paddingContent: PaddingValues = PaddingValues(top = paddingTop, bottom = paddingBottom, start = paddingStart, end = paddingEnd)

    Button(
        onClick,
        modifierButton,
        enabled,
        interactionSource,
        elevation,
        shape, border, colors,
        paddingContent,
    )
    {
        content()
    }
}
