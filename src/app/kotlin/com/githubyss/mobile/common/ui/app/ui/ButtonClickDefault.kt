package com.githubyss.mobile.common.ui.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.ui.button_click.compose.ButtonTextClickBlueWeightHorizontalMarginPadding


@Composable
fun ButtonClickDefault(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit = {},
) {
    ButtonTextClickBlueWeightHorizontalMarginPadding(
        modifier = modifier,
        text = text,
        height = 60.dp,
    ) { onClick() }
}
