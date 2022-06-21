package com.githubyss.mobile.common.ui.button_click.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.button_click.buttonClickBlueBackground
import com.githubyss.mobile.common.res.button_click.buttonClickBlueBorder
import com.githubyss.mobile.common.res.button_click.buttonClickBlueFontSize
import com.githubyss.mobile.common.res.button_click.buttonClickBlueShape
import com.githubyss.mobile.common.res.common.dimen.SideNone
import com.githubyss.mobile.common.ui.utils.modifierHeightAssemble
import com.githubyss.mobile.common.ui.utils.modifierWidthAssemble


// @Composable
// fun ButtonClickBlue(@IdRes resId: Int, enabled: Boolean = true, onClick: () -> Unit) {
//     ButtonClickBlue(getStringFromRes(resId), enabled, onClick)
// }

@Composable
fun ButtonClickBlue(
    text: String,
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
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressState = interactionSource.collectIsPressedAsState().value

    // 先设置 padding，再设置长高，padding 作用等同于 margin
    var modifierRevised = modifier.padding(marginHorizontal, marginVertical)
    modifierRevised = modifierRevised.modifierWidthAssemble(width, isFillMaxWidth)
    modifierRevised = modifierRevised.modifierHeightAssemble(height, isFillMaxHeight)

    Button(onClick = onClick,
           enabled = enabled,
           interactionSource = interactionSource,
           shape = ButtonDefaults.buttonClickBlueShape,
           border = ButtonDefaults.buttonClickBlueBorder,
           colors = ButtonDefaults.buttonClickBlueBackground(pressState),
           contentPadding = PaddingValues(paddingHorizontal, paddingVertical),
           modifier = modifierRevised)
    {
        Text(
            text = text,
            fontSize = ButtonDefaults.buttonClickBlueFontSize,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .background(Color.Transparent),
        )
    }
}
