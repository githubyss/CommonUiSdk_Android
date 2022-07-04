package com.githubyss.mobile.common.ui.card.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.card.*
import com.githubyss.mobile.common.res.common.brush.Transparent
import com.githubyss.mobile.common.res.common.dimen.ElevationLow
import com.githubyss.mobile.common.res.common.dimen.ElevationNone
import com.githubyss.mobile.common.res.common.dimen.SpaceNone


@Composable
fun CardOrange(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.cardShape,
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
    elevation: Dp = Dp.ElevationLow,
    content: @Composable () -> Unit,
) {
    val border: BorderStroke = MaterialTheme.cardOrangeBorder
    val contentBackground: Brush = MaterialTheme.cardOrangeBackground
    val cardBackgroundColor: Color = Color.Transparent
    val cardContentColor: Color = Color.cardOrangeContent

    CardCommon(
        modifier,
        shape,
        border,
        contentBackground,
        cardBackgroundColor,
        cardContentColor,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        elevation,
        content,
    )
}

@Composable
fun CardWhite(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.cardShape,
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
    elevation: Dp = Dp.ElevationLow,
    content: @Composable () -> Unit,
) {
    val border: BorderStroke = MaterialTheme.cardWhiteBorder
    val contentBackground: Brush = MaterialTheme.cardWhiteBackground
    val cardBackgroundColor: Color = Color.Transparent
    val cardContentColor: Color = Color.cardWhiteContent

    CardCommon(
        modifier,
        shape,
        border,
        contentBackground,
        cardBackgroundColor,
        cardContentColor,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        elevation,
        content,
    )
}

@Composable
fun CardTransparent(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.cardShape,
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
    elevation: Dp = Dp.ElevationNone,
    content: @Composable () -> Unit,
) {
    val border: BorderStroke = MaterialTheme.cardTransparentBorder
    val contentBackground: Brush = Brush.Transparent
    val cardBackgroundColor: Color = MaterialTheme.cardTransparentBackground
    val cardContentColor: Color = Color.cardTransparentContent

    CardCommon(
        modifier,
        shape,
        border,
        contentBackground,
        cardBackgroundColor,
        cardContentColor,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        elevation,
        content,
    )
}
