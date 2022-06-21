package com.githubyss.mobile.common.ui.layout.compose

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun LayoutWeightHorizontal(content: @Composable RowScope.() -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        content = content,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    )
}