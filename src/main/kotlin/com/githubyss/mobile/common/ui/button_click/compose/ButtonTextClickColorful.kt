package com.githubyss.mobile.common.ui.button_click.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.button_click.*
import com.githubyss.mobile.common.res.common.dimen.SpaceButtonPaddingDefault
import com.githubyss.mobile.common.res.common.dimen.SpaceNano
import com.githubyss.mobile.common.res.common.dimen.SpaceNone


// @Composable
// fun ButtonTextClickBlue(@IdRes resId: Int, enabled: Boolean = true, onClick: () -> Unit) {
//     ButtonTextClickBlue(getStringFromRes(resId), enabled, onClick)
// }


/** Button click blue. */

/**
 * 可点击文本按钮-蓝色样式
 * 无外边距，无内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonTextClickBlue(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = TextUnit.buttonClickFontSize,
    shape: Shape = ButtonDefaults.buttonClickShape,
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
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    ButtonTextClickCommon(
        modifier,
        text, fontSize,
        shape,
        ButtonDefaults.buttonClickBlueBorder,
        ButtonDefaults.buttonClickBlueBackground(interactionSource.collectIsPressedAsState().value),
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        elevation,
        interactionSource,
        enabled,
        onClick,
    )
}

/**
 * 可点击文本按钮-蓝色样式
 * 无外边距，默认内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonTextClickBluePadding(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = TextUnit.buttonClickFontSize,
    shape: Shape = ButtonDefaults.buttonClickShape,
    marginTop: Dp = Dp.SpaceNone,
    marginBottom: Dp = Dp.SpaceNone,
    marginStart: Dp = Dp.SpaceNone,
    marginEnd: Dp = Dp.SpaceNone,
    paddingTop: Dp = Dp.SpaceButtonPaddingDefault,
    paddingBottom: Dp = Dp.SpaceButtonPaddingDefault,
    paddingStart: Dp = Dp.SpaceButtonPaddingDefault,
    paddingEnd: Dp = Dp.SpaceButtonPaddingDefault,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    elevation: ButtonElevation = ButtonDefaults.elevation(),
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    ButtonTextClickBlue(
        modifier,
        text, fontSize,
        shape,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        elevation,
        enabled,
        onClick,
    )
}

/**
 * 可点击文本按钮-蓝色样式
 * 默认外边距，默认内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonTextClickBlueMarginPadding(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = TextUnit.buttonClickFontSize,
    shape: Shape = ButtonDefaults.buttonClickShape,
    marginTop: Dp = Dp.SpaceNano,
    marginBottom: Dp = Dp.SpaceNano,
    marginStart: Dp = Dp.SpaceNano,
    marginEnd: Dp = Dp.SpaceNano,
    paddingTop: Dp = Dp.SpaceButtonPaddingDefault,
    paddingBottom: Dp = Dp.SpaceButtonPaddingDefault,
    paddingStart: Dp = Dp.SpaceButtonPaddingDefault,
    paddingEnd: Dp = Dp.SpaceButtonPaddingDefault,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    elevation: ButtonElevation = ButtonDefaults.elevation(),
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    ButtonTextClickBlue(
        modifier,
        text, fontSize,
        shape,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        elevation,
        enabled,
        onClick,
    )
}

/**
 * 可点击文本按钮-蓝色样式-横向等分
 * 无外边距，无内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonTextClickBlueWeightHorizontal(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = TextUnit.buttonClickFontSize,
    shape: Shape = ButtonDefaults.buttonClickShape,
    marginTop: Dp = Dp.SpaceNone,
    marginBottom: Dp = Dp.SpaceNone,
    marginStart: Dp = Dp.SpaceNone,
    marginEnd: Dp = Dp.SpaceNone,
    paddingTop: Dp = Dp.SpaceNone,
    paddingBottom: Dp = Dp.SpaceNone,
    paddingStart: Dp = Dp.SpaceNone,
    paddingEnd: Dp = Dp.SpaceNone,
    height: Dp = 0.dp,
    isFillMaxHeight: Boolean = false,
    elevation: ButtonElevation = ButtonDefaults.elevation(),
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    ButtonTextClickBlue(
        modifier,
        text, fontSize,
        shape,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        0.dp, height,
        true, isFillMaxHeight,
        elevation,
        enabled,
        onClick,
    )
}

/**
 * 可点击文本按钮-蓝色样式-横向等分
 * 无外边距，默认内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonTextClickBlueWeightHorizontalPadding(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = TextUnit.buttonClickFontSize,
    shape: Shape = ButtonDefaults.buttonClickShape,
    marginTop: Dp = Dp.SpaceNone,
    marginBottom: Dp = Dp.SpaceNone,
    marginStart: Dp = Dp.SpaceNone,
    marginEnd: Dp = Dp.SpaceNone,
    paddingTop: Dp = Dp.SpaceButtonPaddingDefault,
    paddingBottom: Dp = Dp.SpaceButtonPaddingDefault,
    paddingStart: Dp = Dp.SpaceButtonPaddingDefault,
    paddingEnd: Dp = Dp.SpaceButtonPaddingDefault,
    height: Dp = 0.dp,
    isFillMaxHeight: Boolean = false,
    elevation: ButtonElevation = ButtonDefaults.elevation(),
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    ButtonTextClickBlueWeightHorizontal(
        modifier,
        text, fontSize,
        shape,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        height,
        isFillMaxHeight,
        elevation,
        enabled,
        onClick,
    )
}

/**
 * 可点击文本按钮-蓝色样式-横向等分
 * 默认外边距，默认内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonTextClickBlueWeightHorizontalMarginPadding(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = TextUnit.buttonClickFontSize,
    shape: Shape = ButtonDefaults.buttonClickShape,
    marginTop: Dp = Dp.SpaceNano,
    marginBottom: Dp = Dp.SpaceNano,
    marginStart: Dp = Dp.SpaceNano,
    marginEnd: Dp = Dp.SpaceNano,
    paddingTop: Dp = Dp.SpaceButtonPaddingDefault,
    paddingBottom: Dp = Dp.SpaceButtonPaddingDefault,
    paddingStart: Dp = Dp.SpaceButtonPaddingDefault,
    paddingEnd: Dp = Dp.SpaceButtonPaddingDefault,
    height: Dp = 0.dp,
    isFillMaxHeight: Boolean = false,
    elevation: ButtonElevation = ButtonDefaults.elevation(),
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    ButtonTextClickBlueWeightHorizontal(
        modifier,
        text, fontSize,
        shape,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        height,
        isFillMaxHeight,
        elevation,
        enabled,
        onClick,
    )
}


/** Button click white. */

/**
 * 可点击文本按钮-白色样式
 * 无外边距，无内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonTextClickWhite(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = TextUnit.buttonClickFontSize,
    shape: Shape = ButtonDefaults.buttonClickShape,
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
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    ButtonTextClickCommon(
        modifier,
        text, fontSize,
        shape,
        ButtonDefaults.buttonClickWhiteBorder,
        ButtonDefaults.buttonClickWhiteBackground(interactionSource.collectIsPressedAsState().value),
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        elevation,
        interactionSource,
        enabled,
        onClick,
    )
}


/** Button click transparent. */

/**
 * 可点击文本按钮-透明样式
 * 无外边距，无内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonTextClickTransparent(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = TextUnit.buttonClickFontSize,
    shape: Shape = ButtonDefaults.buttonClickShape,
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
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    ButtonTextClickCommon(
        modifier,
        text, fontSize,
        shape,
        ButtonDefaults.buttonClickTransparentBorder,
        ButtonDefaults.buttonClickTransparentBackground(interactionSource.collectIsPressedAsState().value),
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        elevation,
        interactionSource,
        enabled,
        onClick,
    )
}
