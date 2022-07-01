package com.githubyss.mobile.common.ui.card.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.common.brush.Transparent
import com.githubyss.mobile.common.res.common.dimen.ElevationLow
import com.githubyss.mobile.common.res.common.dimen.SpaceNone
import com.githubyss.mobile.common.ui.utils.modifierHeightAssemble
import com.githubyss.mobile.common.ui.utils.modifierWidthAssemble


/**
 * 通用的 Card
 *
 * @param contentBackground 内容背景色，会遮盖卡片背景色，渲染渐变色的时候使用，默认为透明。
 * @param cardBackgroundColor 卡片背景色，会被内容背景色遮盖，只能渲染单一颜色，默认为透明。
 * @param cardContentColor 内容颜色，比如 Icon、Text 等，如果内容中的 UI 明确指定了自己的颜色，则会覆盖此内容颜色。
 * @return
 */
@Composable
fun CardCommon(
    modifier: Modifier = Modifier,
    shape: Shape,
    border: BorderStroke,
    contentBackground: Brush = Brush.Transparent,
    cardBackgroundColor: Color = Color.Transparent,
    cardContentColor: Color = contentColorFor(cardBackgroundColor),
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
    elevation: Dp = Dp.ElevationLow,
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

    // 这里在 Box 的 Modifier 中设置 background，会遮盖卡片背景色，渲染渐变色的时候使用。究其原因，是因为 Card 参数列表里没有 Brush，而实现渐变色需要使用 Brush
    modifierBox = modifierBox
        .background(contentBackground)
        .padding(start = paddingStart, top = paddingTop, end = paddingEnd, bottom = paddingBottom)

    // 这里在 Card 参数中设置 backgroundColor，可以实现单一颜色的渲染，无法实现渐变色的渲染。
    // 当同时在 Box 的 Modifier 中设置 background 时，Card 的 backgroundColor 会被 Box background 遮盖。换句话说，就是此时设置 backgroundColor 无效。
    Card(
        modifierCard,
        shape,
        cardBackgroundColor,
        cardContentColor,
        border,
        elevation,
    )
    {
        Box(modifier = modifierBox)
        {
            content()
        }
    }
}
