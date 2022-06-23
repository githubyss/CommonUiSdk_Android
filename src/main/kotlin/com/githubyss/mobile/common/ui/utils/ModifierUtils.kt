package com.githubyss.mobile.common.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
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