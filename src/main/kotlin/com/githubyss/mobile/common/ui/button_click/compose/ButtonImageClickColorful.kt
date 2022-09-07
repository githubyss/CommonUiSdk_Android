package com.githubyss.mobile.common.ui.button_click.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.button_click.*
import com.githubyss.mobile.common.res.common.dimen.SpaceNone
import com.githubyss.mobile.common.res.common.style.borderNone


// @Composable
// fun ButtonTextClickBlue(@IdRes resId: Int, enabled: Boolean = true, onClick: () -> Unit) {
//     ButtonTextClickBlue(getStringFromRes(resId), enabled, onClick)
// }


/** Button click white. */

/**
 * 可点击图像按钮-白色样式
 * 无外边距，无内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonImageClickWhite(
    modifier: Modifier = Modifier,
    @DrawableRes imageResId: Int,
    imageWidth: Dp = 0.dp,
    imageHeight: Dp = 0.dp,
    imageFillMaxWidth: Boolean = false,
    imageFillMaxHeight: Boolean = false,
    shape: Shape = ButtonDefaults.buttonClickShapeRound,
    border: BorderStroke = MaterialTheme.buttonClickBorderNone,
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
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val colors: ButtonColors = ButtonDefaults.buttonClickWhiteBackground(interactionSource.collectIsPressedAsState().value)

    ButtonImageClickCommon(
        modifier,
        imageResId,
        imageWidth, imageHeight,
        imageFillMaxWidth, imageFillMaxHeight,
        shape, border, colors,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        interactionSource,
        enabled,
        onClick,
    )
}


/** Button click transparent. */

/**
 * 可点击图像按钮-透明样式
 * 无外边距，无内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonImageClickTransparent(
    modifier: Modifier = Modifier,
    @DrawableRes imageResId: Int,
    imageWidth: Dp = 0.dp,
    imageHeight: Dp = 0.dp,
    imageFillMaxWidth: Boolean = false,
    imageFillMaxHeight: Boolean = false,
    shape: Shape = ButtonDefaults.buttonClickShapeRound,
    border: BorderStroke = MaterialTheme.buttonClickBorderNone,
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
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val colors: ButtonColors = ButtonDefaults.buttonClickTransparentBackground(interactionSource.collectIsPressedAsState().value)

    ButtonImageClickCommon(
        modifier,
        imageResId,
        imageWidth, imageHeight,
        imageFillMaxWidth, imageFillMaxHeight,
        shape, border, colors,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        interactionSource,
        enabled,
        onClick,
    )
}
