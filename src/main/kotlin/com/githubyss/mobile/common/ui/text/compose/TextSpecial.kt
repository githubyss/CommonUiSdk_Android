package com.githubyss.mobile.common.ui.text.compose

import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.common.dimen.SpaceNone


@Composable
fun TextTag1Line(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Unspecified,
    textBackground: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily.Default,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration = TextDecoration.None,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    textStyle: TextStyle = LocalTextStyle.current,
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
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    TextCommon(
        modifier,
        text,
        textColor,
        textBackground,
        textDecoration,
        TextAlign.Center,
        TextDirection.Ltr,
        TextIndent.None,
        fontSize, fontStyle, fontWeight, fontFamily,
        letterSpacing,
        lineHeight,
        overflow,
        false,
        1,
        textStyle,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        0.dp, height,
        false, isFillMaxHeight,
        onTextLayout,
    )
}