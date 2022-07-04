package com.githubyss.mobile.common.ui.page.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.githubyss.mobile.common.res.common.dimen.SpaceNone
import com.githubyss.mobile.common.res.page.pageBgLightGray


@Composable
fun PageCommon(
    background: Color = Color.pageBgLightGray,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    marginTop: Dp = Dp.SpaceNone,
    marginBottom: Dp = Dp.SpaceNone,
    marginStart: Dp = Dp.SpaceNone,
    marginEnd: Dp = Dp.SpaceNone,
    paddingTop: Dp = Dp.SpaceNone,
    paddingBottom: Dp = Dp.SpaceNone,
    paddingStart: Dp = Dp.SpaceNone,
    paddingEnd: Dp = Dp.SpaceNone,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        Modifier
            // 先设置 padding，再设置长高、背景、滑动，padding 作用等同于 margin
            .padding(top = marginTop, bottom = marginBottom, start = marginStart, end = marginEnd)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(background)
            .verticalScroll(rememberScrollState())
            // 先设置长高、背景、滑动，再设置 padding，padding 作用等同于 padding
            .padding(top = paddingTop, bottom = paddingBottom, start = paddingStart, end = paddingEnd),
        verticalArrangement,
        horizontalAlignment,
    )
    {
        content()
    }
}
