package com.githubyss.mobile.common.ui.utils

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Stable
fun Modifier.modifierWidthAssemble(width: Dp, isFillMaxWidth: Boolean): Modifier = when {
    width > 0.dp -> this.width(width)
    isFillMaxWidth -> this.fillMaxWidth()
    else -> this.wrapContentWidth()
}

@Stable
fun Modifier.modifierHeightAssemble(height: Dp, isFillMaxHeight: Boolean): Modifier = when {
    height > 0.dp -> this.height(height)
    isFillMaxHeight -> this.fillMaxHeight()
    else -> this.wrapContentHeight()
}