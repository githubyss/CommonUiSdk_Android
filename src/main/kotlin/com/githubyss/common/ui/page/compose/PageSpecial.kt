package com.githubyss.common.ui.page.compose

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.githubyss.common.res.common.dimen.SpaceNone
import com.githubyss.common.res.common.dimen.SpaceNormal
import com.githubyss.common.res.page.pageBgLightGray


@Composable
fun PageMargin(
    background: Color = Color.pageBgLightGray,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    marginTop: Dp = Dp.SpaceNone,
    marginBottom: Dp = Dp.SpaceNone,
    marginStart: Dp = Dp.SpaceNormal,
    marginEnd: Dp = Dp.SpaceNormal,
    content: @Composable ColumnScope.() -> Unit,
) {
    val paddingTop: Dp = Dp.SpaceNone
    val paddingBottom: Dp = Dp.SpaceNone
    val paddingStart: Dp = Dp.SpaceNone
    val paddingEnd: Dp = Dp.SpaceNone

    PageCommon(
        background,
        verticalArrangement, horizontalAlignment,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
    )
    {
        content()
    }
}

@Composable
fun PagePadding(
    background: Color = Color.pageBgLightGray,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    paddingTop: Dp = Dp.SpaceNone,
    paddingBottom: Dp = Dp.SpaceNone,
    paddingStart: Dp = Dp.SpaceNormal,
    paddingEnd: Dp = Dp.SpaceNormal,
    content: @Composable ColumnScope.() -> Unit,
) {
    val marginTop: Dp = Dp.SpaceNone
    val marginBottom: Dp = Dp.SpaceNone
    val marginStart: Dp = Dp.SpaceNone
    val marginEnd: Dp = Dp.SpaceNone

    PageCommon(
        background,
        verticalArrangement, horizontalAlignment,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
    )
    {
        content()
    }
}
