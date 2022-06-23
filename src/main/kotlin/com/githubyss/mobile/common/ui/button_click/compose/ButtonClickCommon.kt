package com.githubyss.mobile.common.ui.button_click.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.common.dimen.SideNone
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
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape,
    border: BorderStroke,
    colors: ButtonColors,
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    paddingHorizontal: Dp = Dp.SideNone,
    paddingVertical: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 60.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    // 先设置 padding，再设置长高，padding 作用等同于 margin
    var modifierRevised = modifier.padding(marginHorizontal, marginVertical)
    modifierRevised = modifierRevised.modifierWidthAssemble(width, isFillMaxWidth)
    modifierRevised = modifierRevised.modifierHeightAssemble(height, isFillMaxHeight)

    Button(
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = PaddingValues(paddingHorizontal, paddingVertical),
        modifier = modifierRevised,
    )
    {
        content()
    }
}
