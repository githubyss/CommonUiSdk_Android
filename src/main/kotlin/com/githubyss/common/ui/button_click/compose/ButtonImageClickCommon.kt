package com.githubyss.common.ui.button_click.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.common.res.common.dimen.ElevationNone
import com.githubyss.common.res.common.dimen.SpaceNone
import com.githubyss.common.ui.image.ImageCommon
import com.githubyss.common.ui.utils.modifierHeightAssemble
import com.githubyss.common.ui.utils.modifierWidthAssemble


/**
 * 可点击图像按钮
 * 内容固定为一个图像
 * 无外边距，无内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonImageClickCommon(
    modifier: Modifier = Modifier,
    @DrawableRes imageResId: Int,
    imageWidth: Dp = 0.dp,
    imageHeight: Dp = 0.dp,
    imageFillMaxWidth: Boolean = false,
    imageFillMaxHeight: Boolean = false,
    shape: Shape,
    border: BorderStroke,
    colors: ButtonColors,
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
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    val elevation: ButtonElevation = ButtonDefaults.elevation(Dp.ElevationNone, Dp.ElevationNone, Dp.ElevationNone)

    ButtonClickCommon(
        modifier,
        shape, border, colors,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        elevation,
        interactionSource,
        enabled,
        onClick,
    )
    {
        ImageCommon(
            imageResId = imageResId,
            modifier = Modifier
                .modifierWidthAssemble(imageWidth, imageFillMaxWidth)
                .modifierHeightAssemble(imageHeight, imageFillMaxHeight)
                .background(Color.Transparent),
        )
    }
}
