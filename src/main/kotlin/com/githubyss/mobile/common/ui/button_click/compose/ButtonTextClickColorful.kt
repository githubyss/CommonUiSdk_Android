package com.githubyss.mobile.common.ui.button_click.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.button_click.*
import com.githubyss.mobile.common.res.common.dimen.SideNano
import com.githubyss.mobile.common.res.common.dimen.SideNone
import com.githubyss.mobile.common.res.common.dimen.SideTiny


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
    val interactionSource = remember { MutableInteractionSource() }
    ButtonTextClickCommon(
        modifier,
        text, fontSize,
        interactionSource,
        ButtonDefaults.buttonClickShape,
        ButtonDefaults.buttonClickBlueBorder,
        ButtonDefaults.buttonClickBlueBackground(interactionSource.collectIsPressedAsState().value),
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
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
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    paddingHorizontal: Dp = Dp.SideTiny,
    paddingVertical: Dp = Dp.SideTiny,
    width: Dp = 0.dp,
    height: Dp = 60.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    ButtonTextClickBlue(
        modifier,
        text, fontSize,
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
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
    marginHorizontal: Dp = Dp.SideNano,
    marginVertical: Dp = Dp.SideNano,
    paddingHorizontal: Dp = Dp.SideTiny,
    paddingVertical: Dp = Dp.SideTiny,
    width: Dp = 0.dp,
    height: Dp = 60.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    ButtonTextClickBlue(
        modifier,
        text, fontSize,
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
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
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    paddingHorizontal: Dp = Dp.SideNone,
    paddingVertical: Dp = Dp.SideNone,
    height: Dp = 60.dp,
    isFillMaxHeight: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    ButtonTextClickBlue(
        modifier,
        text, fontSize,
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width = 0.dp, height = height,
        isFillMaxWidth = true, isFillMaxHeight = isFillMaxHeight,
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
    marginHorizontal: Dp = Dp.SideNano,
    marginVertical: Dp = Dp.SideNano,
    paddingHorizontal: Dp = Dp.SideTiny,
    paddingVertical: Dp = Dp.SideTiny,
    height: Dp = 60.dp,
    isFillMaxHeight: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    ButtonTextClickBlue(
        modifier,
        text, fontSize,
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width = 0.dp, height = height,
        isFillMaxWidth = true, isFillMaxHeight = isFillMaxHeight,
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
    val interactionSource = remember { MutableInteractionSource() }
    ButtonTextClickCommon(
        modifier,
        text, fontSize,
        interactionSource,
        ButtonDefaults.buttonClickShape,
        ButtonDefaults.buttonClickWhiteBorder,
        ButtonDefaults.buttonClickWhiteBackground(interactionSource.collectIsPressedAsState().value),
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
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
    val interactionSource = remember { MutableInteractionSource() }
    ButtonTextClickCommon(
        modifier,
        text, fontSize,
        interactionSource,
        ButtonDefaults.buttonClickShape,
        ButtonDefaults.buttonClickTransparentBorder,
        ButtonDefaults.buttonClickTransparentBackground(interactionSource.collectIsPressedAsState().value),
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        enabled,
        onClick,
    )
}
