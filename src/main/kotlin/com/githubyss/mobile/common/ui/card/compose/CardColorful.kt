package com.githubyss.mobile.common.ui.card.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.card.*
import com.githubyss.mobile.common.res.common.dimen.ElevationLow
import com.githubyss.mobile.common.res.common.dimen.ElevationNone
import com.githubyss.mobile.common.res.common.dimen.SideNone


@Composable
fun CardOrange(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.cardShape,
    elevation: Dp = Dp.ElevationLow,
    marginStart: Dp = Dp.SideNone,
    marginTop: Dp = Dp.SideNone,
    marginEnd: Dp = Dp.SideNone,
    marginBottom: Dp = Dp.SideNone,
    paddingStart: Dp = Dp.SideNone,
    paddingTop: Dp = Dp.SideNone,
    paddingEnd: Dp = Dp.SideNone,
    paddingBottom: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    content: @Composable () -> Unit,
) {
    CardCommon(
        modifier,
        shape,
        MaterialTheme.cardOrangeBorder,
        MaterialTheme.cardOrangeBackground,
        elevation,
        marginStart, marginTop, marginEnd, marginBottom,
        paddingStart, paddingTop, paddingEnd, paddingBottom,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        content,
    )
}

@Composable
fun CardWhite(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.cardShape,
    elevation: Dp = Dp.ElevationLow,
    marginStart: Dp = Dp.SideNone,
    marginTop: Dp = Dp.SideNone,
    marginEnd: Dp = Dp.SideNone,
    marginBottom: Dp = Dp.SideNone,
    paddingStart: Dp = Dp.SideNone,
    paddingTop: Dp = Dp.SideNone,
    paddingEnd: Dp = Dp.SideNone,
    paddingBottom: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    content: @Composable () -> Unit,
) {
    CardCommon(
        modifier,
        shape,
        MaterialTheme.cardWhiteBorder,
        MaterialTheme.cardWhiteBackground,
        elevation,
        marginStart, marginTop, marginEnd, marginBottom,
        paddingStart, paddingTop, paddingEnd, paddingBottom,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        content,
    )
}

@Composable
fun CardTransparent(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.cardShape,
    elevation: Dp = Dp.ElevationNone,
    marginStart: Dp = Dp.SideNone,
    marginTop: Dp = Dp.SideNone,
    marginEnd: Dp = Dp.SideNone,
    marginBottom: Dp = Dp.SideNone,
    paddingStart: Dp = Dp.SideNone,
    paddingTop: Dp = Dp.SideNone,
    paddingEnd: Dp = Dp.SideNone,
    paddingBottom: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    content: @Composable () -> Unit,
) {
    CardCommon(
        modifier,
        shape,
        MaterialTheme.cardTransparentBorder,
        MaterialTheme.cardTransparentBackground,
        elevation,
        marginStart, marginTop, marginEnd, marginBottom,
        paddingStart, paddingTop, paddingEnd, paddingBottom,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        content,
    )
}
