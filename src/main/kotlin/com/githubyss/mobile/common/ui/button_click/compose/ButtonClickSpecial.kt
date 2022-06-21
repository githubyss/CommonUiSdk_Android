package com.githubyss.mobile.common.ui.button_click.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.common.dimen.SideNano
import com.githubyss.mobile.common.res.common.dimen.SideTiny


@Composable
fun ButtonClickBlueMargin(
    text: String,
    modifier: Modifier = Modifier,
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
    ButtonClickBlue(
        text = text,
        marginHorizontal = marginHorizontal,
        marginVertical = marginVertical,
        paddingHorizontal = paddingHorizontal,
        paddingVertical = paddingVertical,
        width = width,
        height = height,
        isFillMaxWidth = isFillMaxWidth,
        isFillMaxHeight = isFillMaxHeight,
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
    )
}

@Composable
fun ButtonClickBlueWeightHorizontal(
    text: String,
    modifier: Modifier = Modifier,
    marginHorizontal: Dp = Dp.SideNano,
    marginVertical: Dp = Dp.SideNano,
    paddingHorizontal: Dp = Dp.SideTiny,
    paddingVertical: Dp = Dp.SideTiny,
    isFillMaxWidth: Boolean = true,
    height: Dp = 60.dp,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    ButtonClickBlue(
        text = text,
        marginHorizontal = marginHorizontal,
        marginVertical = marginVertical,
        paddingHorizontal = paddingHorizontal,
        paddingVertical = paddingVertical,
        isFillMaxWidth = isFillMaxWidth,
        height = height,
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
    )
}