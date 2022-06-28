package com.githubyss.mobile.common.ui.button_click.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
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
import com.githubyss.mobile.common.res.common.dimen.SpaceNone


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
) {
    ButtonClickCommon(
        modifier,
        shape, border, colors,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        elevation,
        interactionSource,
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
