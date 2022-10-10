package com.githubyss.common.ui.layout.compose

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.common.res.common.dimen.SpaceNone
import com.githubyss.common.ui.utils.modifierHeightAssemble
import com.githubyss.common.ui.utils.modifierWidthAssemble


@Composable
fun LayoutVerticalCommon(
    modifier: Modifier = Modifier,
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
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit,
) {
    val modifierRow = modifier
        // 先设置 padding，再设置长高、背景、滑动，padding 作用等同于 margin
        .padding(top = marginTop, bottom = marginBottom, start = marginStart, end = marginEnd)
        .modifierWidthAssemble(width, isFillMaxWidth)
        .modifierHeightAssemble(height, isFillMaxHeight)
        // 先设置长高、背景、滑动，再设置 padding，padding 作用等同于 padding
        .padding(top = paddingTop, bottom = paddingBottom, start = paddingStart, end = paddingEnd)

    Column(
        modifierRow,
        verticalArrangement,
        horizontalAlignment,
    ) {
        content()
    }
}
