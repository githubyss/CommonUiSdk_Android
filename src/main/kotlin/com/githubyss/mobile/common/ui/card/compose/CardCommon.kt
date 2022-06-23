package com.githubyss.mobile.common.ui.card.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.common.dimen.ElevationLow
import com.githubyss.mobile.common.res.common.dimen.SideNone
import com.githubyss.mobile.common.ui.utils.modifierHeightAssemble
import com.githubyss.mobile.common.ui.utils.modifierWidthAssemble


@Composable
fun CardCommon(
    modifier: Modifier = Modifier,
    shape: Shape,
    border: BorderStroke,
    background: Brush,
    elevation: Dp = Dp.ElevationLow,
    marginStart: Dp = Dp.SideNone,
    marginTop: Dp = Dp.SideNone,
    marginEnd: Dp = Dp.SideNone,
    marginBottom: Dp = Dp.SideNone,
    paddingStart: Dp = Dp.SideNone,
    paddingTop: Dp = Dp.SideNone,
    paddingEnd: Dp = Dp.SideNone,
    paddingBottom: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 100.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    content: @Composable () -> Unit,
) {
    val modifierCard: Modifier = Modifier.padding(start = marginStart, top = marginTop, end = marginEnd, bottom = marginBottom)
    var modifierBox: Modifier = modifier
    modifierBox = modifierBox.modifierWidthAssemble(width, isFillMaxWidth)
    modifierBox = modifierBox.modifierHeightAssemble(height, isFillMaxHeight)

    // modifierRevised = modifierRevised
    //     .border(border, shape)
    //     .background(background, shape)
    //     .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
    //     .clip(shape)
    // Box(modifier = modifierRevised)
    // {
    //     content()
    // }

    modifierBox = modifierBox
        .background(background)
        .padding(start = paddingStart, top = paddingTop, end = paddingEnd, bottom = paddingBottom)

    Card(
        shape = shape,
        border = border,
        elevation = elevation,
        modifier = modifierCard,
    )
    {
        Box(modifier = modifierBox)
        {
            content()
        }
    }
}

@Composable
fun CardCommon(
    modifier: Modifier = Modifier,
    shape: Shape,
    border: BorderStroke,
    background: Color,
    elevation: Dp = Dp.ElevationLow,
    marginStart: Dp = Dp.SideNone,
    marginTop: Dp = Dp.SideNone,
    marginEnd: Dp = Dp.SideNone,
    marginBottom: Dp = Dp.SideNone,
    paddingStart: Dp = Dp.SideNone,
    paddingTop: Dp = Dp.SideNone,
    paddingEnd: Dp = Dp.SideNone,
    paddingBottom: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 100.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    content: @Composable () -> Unit,
) {
    val modifierCard: Modifier = Modifier.padding(start = marginStart, top = marginTop, end = marginEnd, bottom = marginBottom)
    var modifierBox: Modifier = modifier
    modifierBox = modifierBox.modifierWidthAssemble(width, isFillMaxWidth)
    modifierBox = modifierBox.modifierHeightAssemble(height, isFillMaxHeight)

    // modifierBox = modifierBox
    //     .border(border, shape)
    //     .background(background, shape)
    //     .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
    //     .clip(shape)
    // Box(modifier = modifierBox)
    // {
    //     content()
    // }

    modifierBox = modifierBox
        .background(background)
        .padding(start = paddingStart, top = paddingTop, end = paddingEnd, bottom = paddingBottom)

    Card(
        shape = shape,
        border = border,
        elevation = elevation,
        modifier = modifierCard,
    )
    {
        Box(modifier = modifierBox)
        {
            content()
        }
    }
}
