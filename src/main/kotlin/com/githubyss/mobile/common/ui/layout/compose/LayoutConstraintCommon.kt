package com.githubyss.mobile.common.ui.layout.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.githubyss.mobile.common.res.common.dimen.SpaceNone
import com.githubyss.mobile.common.res.layout.layoutBorderNone
import com.githubyss.mobile.common.res.layout.layoutShapeRectangle
import com.githubyss.mobile.common.ui.utils.modifierHeightAssemble
import com.githubyss.mobile.common.ui.utils.modifierWidthAssemble


@Composable
inline fun LayoutConstraintCommon(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.layoutShapeRectangle,
    border: BorderStroke = MaterialTheme.layoutBorderNone,
    background: Color,
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
    crossinline content: @Composable ConstraintLayoutScope.() -> Unit,
) {
    val background = Brush.linearGradient(listOf(background, background))

    LayoutConstraintCommon(
        modifier,
        shape, border, background,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        content,
    )
}

@Composable
inline fun LayoutConstraintCommon(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.layoutShapeRectangle,
    border: BorderStroke = MaterialTheme.layoutBorderNone,
    background: Brush,
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
    crossinline content: @Composable ConstraintLayoutScope.() -> Unit,
) {
    val modifierConstraint = modifier
        // 先设置 padding，再设置长高、背景、滑动，padding 作用等同于 margin
        .padding(top = marginTop, bottom = marginBottom, start = marginStart, end = marginEnd)
        .modifierWidthAssemble(width, isFillMaxWidth)
        .modifierHeightAssemble(height, isFillMaxHeight)
        .background(background, shape)
        // 先设置长高、背景、滑动，再设置 padding，padding 作用等同于 padding
        .padding(top = paddingTop, bottom = paddingBottom, start = paddingStart, end = paddingEnd)

    ConstraintLayout(modifierConstraint) {
        content()
    }
}
