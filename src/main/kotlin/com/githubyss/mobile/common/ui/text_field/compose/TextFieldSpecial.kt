package com.githubyss.mobile.common.ui.text_field.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.githubyss.mobile.common.res.common.dimen.SpaceNone
import com.githubyss.mobile.common.res.common.dimen.SpaceTiny
import com.githubyss.mobile.common.res.text_field.textFieldShape


@Composable
fun TextFieldPassword(
    text: String,
    label: String,
    placeholder: String,
    textDecoration: TextDecoration = TextDecoration.None,
    textAlign: TextAlign = TextAlign.Start,
    textDirection: TextDirection = TextDirection.Ltr,
    textIndent: TextIndent = TextIndent.None,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily.Monospace,
    letterSpacing: TextUnit = TextUnit.SpaceTiny,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textStyle: TextStyle = LocalTextStyle.current,
    enabled: Boolean = true,
    isError: Boolean = false,
    shape: Shape = TextFieldDefaults.textFieldShape,
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
    keyboardType: KeyboardType = KeyboardType.Password,
    imeAction: ImeAction = ImeAction.Done,
    onKeyboardActions: (String) -> Unit = {},
    onValueChange: (String) -> Unit = {},
    onClear: () -> Unit = {},
    canPasswordVisible: Boolean = true,
) {
    val isPasswordVisible = remember { mutableStateOf(false) }

    val readOnly: Boolean = false
    val singleLine: Boolean = true
    val maxLines: Int = 1
    val labelView: @Composable (() -> Unit) = { Text(text = label) }
    val placeholderView: @Composable (() -> Unit) = { Text(text = placeholder) }
    val leadingIcon: @Composable (() -> Unit) = {
        Icon(imageVector = Icons.Filled.Lock, contentDescription = "Lock", modifier = Modifier.clickable {
            isPasswordVisible.value = !isPasswordVisible.value
        })
    }
    val trailingIcon: @Composable (() -> Unit) = {
        Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear", modifier = Modifier.clickable {
            onClear()
        })
    }
    val visualTransformation: VisualTransformation = if (isPasswordVisible.value && canPasswordVisible) {
        VisualTransformation.None
    }
    else {
        PasswordVisualTransformation()
    }
    val keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = keyboardType,
        imeAction = imeAction,
    )

    TextFieldBlack(
        text,
        textDecoration, textAlign, textDirection, textIndent,
        fontSize, fontStyle, fontWeight, fontFamily,
        letterSpacing, lineHeight, textStyle,
        labelView, placeholderView,
        leadingIcon, trailingIcon,
        enabled,
        readOnly,
        singleLine,
        isError,
        maxLines,
        shape,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        visualTransformation,
        keyboardOptions,
        onKeyboardActions, onValueChange,
    )
}

@Composable
fun TextFieldPasswordNumber(
    text: String,
    label: String,
    placeholder: String,
    textDecoration: TextDecoration = TextDecoration.None,
    textAlign: TextAlign = TextAlign.Start,
    textDirection: TextDirection = TextDirection.Ltr,
    textIndent: TextIndent = TextIndent.None,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily.Monospace,
    letterSpacing: TextUnit = TextUnit.SpaceTiny,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textStyle: TextStyle = LocalTextStyle.current,
    enabled: Boolean = true,
    shape: Shape = TextFieldDefaults.textFieldShape,
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
    imeAction: ImeAction = ImeAction.Done,
    onKeyboardActions: (String) -> Unit = {},
    onValueChange: (String) -> Unit = {},
    onClear: () -> Unit = {},
    onErrorChange: (Boolean) -> Unit = {},
    canPasswordVisible: Boolean = true,
) {
    var isError: Boolean by remember { mutableStateOf(false) }

    val keyboardType: KeyboardType = KeyboardType.Number

    val onKeyboardActionsRevised: (String) -> Unit = {
        onKeyboardActions(it)
        isError = !validateDigits(it)
        onErrorChange(isError)
    }

    val onValueChangeRevised: (String) -> Unit = {
        onValueChange(it)
        isError = !validateDigits(it)
        onErrorChange(isError)
    }

    val onClearRevised: () -> Unit = {
        onClear()
        isError = !validateDigits("")
        onErrorChange(isError)
    }

    TextFieldPassword(
        text, label, placeholder,
        textDecoration, textAlign, textDirection, textIndent,
        fontSize, fontStyle, fontWeight, fontFamily,
        letterSpacing, lineHeight, textStyle,
        enabled,
        isError,
        shape,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        keyboardType, imeAction,
        onKeyboardActionsRevised, onValueChangeRevised,
        onClearRevised, canPasswordVisible,
    )
}

private fun validateDigits(text: String): Boolean {
    return text.isEmpty() || text.isDigitsOnly()
}
