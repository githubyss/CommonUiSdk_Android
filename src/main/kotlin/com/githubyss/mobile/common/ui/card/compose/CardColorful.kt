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
import com.githubyss.mobile.common.res.common.dimen.SideNone


@Composable
fun CardOrange(
    modifier: Modifier = Modifier,
    background: Brush = MaterialTheme.cardOrangeBackground,
    shape: Shape = MaterialTheme.cardOrangeShape,
    border: BorderStroke = MaterialTheme.cardOrangeBorder,
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    paddingHorizontal: Dp = Dp.SideNone,
    paddingVertical: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    content: @Composable () -> Unit,
) {
    CardCommon(
        modifier,
        background, shape, border,
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        content,
    )
}

@Composable
fun CardWhite(
    modifier: Modifier = Modifier,
    background: Brush = MaterialTheme.cardWhiteBackground,
    shape: Shape = MaterialTheme.cardWhiteShape,
    border: BorderStroke = MaterialTheme.cardWhiteBorder,
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    paddingHorizontal: Dp = Dp.SideNone,
    paddingVertical: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    content: @Composable () -> Unit,
) {
    CardCommon(
        modifier,
        background, shape, border,
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        content,
    )
}

@Composable
fun CardTransparent(
    modifier: Modifier = Modifier,
    background: Color = MaterialTheme.cardTransparentBackground,
    shape: Shape = MaterialTheme.cardTransparentShape,
    border: BorderStroke = MaterialTheme.cardTransparentBorder,
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    paddingHorizontal: Dp = Dp.SideNone,
    paddingVertical: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    content: @Composable () -> Unit,
) {
    CardCommon(
        modifier,
        background, shape, border,
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        content,
    )
}
