package com.githubyss.mobile.common.ui.button_click.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.button_click.buttonClickBlueBorder
import com.githubyss.mobile.common.res.button_click.buttonClickFontSize
import com.githubyss.mobile.common.res.button_click.buttonClickShapeRound
import com.githubyss.mobile.common.res.common.dimen.SpaceButtonPaddingDefault
import com.githubyss.mobile.common.res.common.dimen.SpaceNano
import com.githubyss.mobile.common.res.common.dimen.SpaceNone


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
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily.Default,
    shape: Shape = ButtonDefaults.buttonClickShapeRound,
    border: BorderStroke = ButtonDefaults.buttonClickBlueBorder,
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
        text, fontSize, fontStyle, fontWeight, fontFamily,
        shape, border,
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
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily.Default,
    shape: Shape = ButtonDefaults.buttonClickShapeRound,
    border: BorderStroke = ButtonDefaults.buttonClickBlueBorder,
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
        text, fontSize, fontStyle, fontWeight, fontFamily,
        shape, border,
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
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily.Default,
    shape: Shape = ButtonDefaults.buttonClickShapeRound,
    border: BorderStroke = ButtonDefaults.buttonClickBlueBorder,
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
    val width: Dp = 0.dp
    val isFillMaxWidth: Boolean = true

    ButtonTextClickBlue(
        modifier,
        text, fontSize, fontStyle, fontWeight, fontFamily,
        shape, border,
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
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily.Default,
    shape: Shape = ButtonDefaults.buttonClickShapeRound,
    border: BorderStroke = ButtonDefaults.buttonClickBlueBorder,
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
        text, fontSize, fontStyle, fontWeight, fontFamily,
        shape, border,
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
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily.Default,
    shape: Shape = ButtonDefaults.buttonClickShapeRound,
    border: BorderStroke = ButtonDefaults.buttonClickBlueBorder,
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
        text, fontSize, fontStyle, fontWeight, fontFamily,
        shape, border,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        height,
        isFillMaxHeight,
        elevation,
        enabled,
        onClick,
    )
}
