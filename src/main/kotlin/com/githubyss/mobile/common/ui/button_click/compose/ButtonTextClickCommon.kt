package com.githubyss.mobile.common.ui.button_click.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ButtonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.common.dimen.SideNone


/**
 * 可点击文本按钮
 * 内容固定为一个文本框
 * 无外边距，无内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonTextClickCommon(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: ButtonColors,
    shape: Shape,
    border: BorderStroke,
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
) {
    ButtonClickCommon(
        modifier,
        interactionSource,
        colors, shape, border,
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        enabled,
        onClick,
    )
    {
        Text(
            text = text,
            fontSize = fontSize,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .background(Color.Transparent),
        )
    }
}
