package com.githubyss.common.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Stable
fun Modifier.modifierWidthAssemble(width: Dp, isFillMaxWidth: Boolean): Modifier = when {
    isFillMaxWidth -> this.fillMaxWidth()
    width > 0.dp -> this.width(width)
    else -> this.wrapContentWidth()
}

@Stable
fun Modifier.modifierHeightAssemble(height: Dp, isFillMaxHeight: Boolean): Modifier = when {
    isFillMaxHeight -> this.fillMaxHeight()
    height > 0.dp -> this.height(height)
    else -> this.wrapContentHeight()
}

@Stable
fun Modifier.drawTextFieldIndicatorLineBottom(color: Color, lineWidth: Dp = 1.dp): Modifier = this.drawBehind {
    val strokeWidth = lineWidth.value * density
    val y = size.height - strokeWidth / 2
    drawLine(
        color,
        Offset(0f, y),
        Offset(size.width, y),
        strokeWidth
    )
}

