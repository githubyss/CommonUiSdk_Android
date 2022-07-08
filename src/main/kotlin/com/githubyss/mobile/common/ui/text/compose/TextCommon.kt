package com.githubyss.mobile.common.ui.text.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
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
import com.githubyss.mobile.common.ui.utils.modifierHeightAssemble
import com.githubyss.mobile.common.ui.utils.modifierWidthAssemble


@Composable
fun TextCommon(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.Unspecified,
    textBackground: Color = Color.Unspecified,
    textDecoration: TextDecoration = TextDecoration.None,
    textAlign: TextAlign = TextAlign.Start,
    textDirection: TextDirection = TextDirection.Ltr,
    textIndent: TextIndent = TextIndent.None,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily.Default,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    textStyle: TextStyle = LocalTextStyle.current,
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
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val modifierText: Modifier = modifier
        .padding(top = marginTop, bottom = marginBottom, start = marginStart, end = marginEnd)
        .modifierWidthAssemble(width, isFillMaxWidth)
        .modifierHeightAssemble(height, isFillMaxHeight)
        // 这里在 Modifier 中设置 background，会根据宽高来渲染背景色。
        .background(textBackground)
        .padding(top = paddingTop, bottom = paddingBottom, start = paddingStart, end = paddingEnd)

    // 这里在 TextStyle 中设置 background，只会渲染文字部分。
    val textStyleExtra: TextStyle = TextStyle(
        background = textBackground,
        textDirection = textDirection, textIndent = textIndent,
    )
    val textStyleRevised: TextStyle = textStyle.plus(textStyleExtra)

    Text(
        text,
        modifierText,
        textColor,
        fontSize, fontStyle, fontWeight, fontFamily,
        letterSpacing,
        textDecoration,
        textAlign,
        lineHeight,
        overflow,
        softWrap,
        maxLines,
        onTextLayout,
        textStyleRevised,
    )
}