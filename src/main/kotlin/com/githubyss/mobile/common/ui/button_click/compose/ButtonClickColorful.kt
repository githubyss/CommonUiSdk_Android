package com.githubyss.mobile.common.ui.button_click.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.button_click.*
import com.githubyss.mobile.common.res.common.dimen.SideNone


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
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    paddingHorizontal: Dp = Dp.SideNone,
    paddingVertical: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 60.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    ButtonClickCommon(
        modifier,
        interactionSource,
        ButtonDefaults.buttonClickShape,
        ButtonDefaults.buttonClickBlueBorder,
        ButtonDefaults.buttonClickBlueBackground(interactionSource.collectIsPressedAsState().value),
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
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
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    paddingHorizontal: Dp = Dp.SideNone,
    paddingVertical: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 60.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    ButtonClickCommon(
        modifier,
        interactionSource,
        ButtonDefaults.buttonClickShape,
        ButtonDefaults.buttonClickWhiteBorder,
        ButtonDefaults.buttonClickBlueBackground(interactionSource.collectIsPressedAsState().value),
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
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
    marginHorizontal: Dp = Dp.SideNone,
    marginVertical: Dp = Dp.SideNone,
    paddingHorizontal: Dp = Dp.SideNone,
    paddingVertical: Dp = Dp.SideNone,
    width: Dp = 0.dp,
    height: Dp = 60.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    ButtonClickCommon(
        modifier,
        interactionSource,
        ButtonDefaults.buttonClickShape,
        ButtonDefaults.buttonClickTransparentBorder,
        ButtonDefaults.buttonClickTransparentBackground(interactionSource.collectIsPressedAsState().value),
        marginHorizontal, marginVertical,
        paddingHorizontal, paddingVertical,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        enabled,
        onClick,
    )
    {
        content()
    }
}
