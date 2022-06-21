package com.githubyss.mobile.common.ui.card.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.card.*
import com.githubyss.mobile.common.res.common.dimen.SideNone
import com.githubyss.mobile.common.ui.utils.modifierHeightAssemble
import com.githubyss.mobile.common.ui.utils.modifierWidthAssemble


@Composable
fun CardCommon(
    modifier: Modifier = Modifier,
    background: Brush,
    shape: Shape,
    border: BorderStroke,
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    paddingHorizontal: Dp = Dp.SideNone,
    paddingVertical: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    content: @Composable () -> Unit,
) {
    var modifierRevised: Modifier = modifier
    modifierRevised = modifierRevised.padding(horizontal = marginHorizontal, vertical = marginVertical)
    modifierRevised = modifierRevised.modifierWidthAssemble(width, isFillMaxWidth)
    modifierRevised = modifierRevised.modifierHeightAssemble(height, isFillMaxHeight)
    modifierRevised = modifierRevised
        .background(background, shape)
        .border(border, shape)
        .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
        .clip(shape)

    Box(modifier = modifierRevised)
    {
        content()
    }

    // modifierRevised = modifierRevised
    //     .background(background)
    //     .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
    //
    // Card(shape = shape,
    //      border = border)
    // {
    //     Box(modifier = modifierRevised)
    //     {
    //         content()
    //     }
    // }
}

@Composable
fun CardCommon(
    modifier: Modifier = Modifier,
    background: Color,
    shape: Shape,
    border: BorderStroke,
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    paddingHorizontal: Dp = Dp.SideNone,
    paddingVertical: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    content: @Composable () -> Unit,
) {
    var modifierRevised: Modifier = modifier
    modifierRevised = modifierRevised.padding(horizontal = marginHorizontal, vertical = marginVertical)
    modifierRevised = modifierRevised.modifierWidthAssemble(width, isFillMaxWidth)
    modifierRevised = modifierRevised.modifierHeightAssemble(height, isFillMaxHeight)
    modifierRevised = modifierRevised
        .background(background, shape)
        .border(border, shape)
        .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
        .clip(shape)

    Box(modifier = modifierRevised)
    {
        content()
    }
}

@Composable
fun CardOrange(
    modifier: Modifier = Modifier,
    background: Brush = MaterialTheme.cardOrangeBackground,
    shape: Shape = MaterialTheme.cardOrangeShape,
    border: BorderStroke = MaterialTheme.cardOrangeBorder,
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    paddingHorizontal: Dp = Dp.SideNone,
    paddingVertical: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    content: @Composable () -> Unit,
) {
    CardCommon(
        modifier,
        background, shape, border,
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        content,
    )
}

@Composable
fun CardWhite(
    modifier: Modifier = Modifier,
    background: Brush = MaterialTheme.cardWhiteBackground,
    shape: Shape = MaterialTheme.cardWhiteShape,
    border: BorderStroke = MaterialTheme.cardWhiteBorder,
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    paddingHorizontal: Dp = Dp.SideNone,
    paddingVertical: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    content: @Composable () -> Unit,
) {
    CardCommon(
        modifier,
        background, shape, border,
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        content,
    )
}

@Composable
fun CardTransparent(
    modifier: Modifier = Modifier,
    background: Color = MaterialTheme.cardTransparentBackground,
    shape: Shape = MaterialTheme.cardTransparentShape,
    border: BorderStroke = MaterialTheme.cardTransparentBorder,
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    paddingHorizontal: Dp = Dp.SideNone,
    paddingVertical: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    content: @Composable () -> Unit,
) {
    CardCommon(
        modifier,
        background, shape, border,
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        content,
    )
}
