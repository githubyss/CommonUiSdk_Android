package com.githubyss.mobile.common.ui.button_click.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.button_click.*
import com.githubyss.mobile.common.res.common.dimen.SpaceNone


/** Button click blue. */

/**
 * 可点击通用按钮-蓝色样式
 * 无外边距，无内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonClickBlue(
    modifier: Modifier = Modifier,
    shape: Shape = ButtonDefaults.buttonClickShape,
    marginTop: Dp = Dp.SpaceNone,
    marginBottom: Dp = Dp.SpaceNone,
    marginStart: Dp = Dp.SpaceNone,
    marginEnd: Dp = Dp.SpaceNone,
    paddingTop: Dp = Dp.SpaceNone,
    paddingBottom: Dp = Dp.SpaceNone,
    paddingStart: Dp = Dp.SpaceNone,
    paddingEnd: Dp = Dp.SpaceNone,
    width: Dp = 0.dp,
    height: Dp = 60.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    elevation: ButtonElevation = ButtonDefaults.elevation(),
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    ButtonClickCommon(
        modifier,
        shape,
        ButtonDefaults.buttonClickBlueBorder,
        ButtonDefaults.buttonClickBlueBackground(interactionSource.collectIsPressedAsState().value),
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
        content()
    }
}


/** Button click white. */

/**
 * 可点击通用按钮-白色样式
 * 无外边距，无内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonClickWhite(
    modifier: Modifier = Modifier,
    shape: Shape = ButtonDefaults.buttonClickShape,
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
    elevation: ButtonElevation = ButtonDefaults.elevation(),
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    ButtonClickCommon(
        modifier,
        shape,
        ButtonDefaults.buttonClickWhiteBorder,
        ButtonDefaults.buttonClickBlueBackground(interactionSource.collectIsPressedAsState().value),
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
        content()
    }
}


/** Button click transparent. */

/**
 * 可点击通用按钮-透明样式
 * 无外边距，无内边距
 *
 * @param
 * @return
 */
@Composable
fun ButtonClickTransparent(
    modifier: Modifier = Modifier,
    shape: Shape = ButtonDefaults.buttonClickShape,
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
    elevation: ButtonElevation = ButtonDefaults.elevation(),
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    ButtonClickCommon(
        modifier,
        shape,
        ButtonDefaults.buttonClickTransparentBorder,
        ButtonDefaults.buttonClickTransparentBackground(interactionSource.collectIsPressedAsState().value),
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
        content()
    }
}
