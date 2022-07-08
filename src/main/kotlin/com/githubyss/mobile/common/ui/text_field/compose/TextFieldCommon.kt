package com.githubyss.mobile.common.ui.text_field.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.githubyss.mobile.common.res.common.dimen.SpaceNone
import com.githubyss.mobile.common.ui.utils.modifierHeightAssemble
import com.githubyss.mobile.common.ui.utils.modifierWidthAssemble


/**
 * 使用独立的颜色配置颜色模式
 * 默认的实现，颜色都是未指定，也就是透明色。
 * 所以不能直接用默认值，需要指定一部分颜色才可以。
 *
 * @param
 * @return
 */
@Composable
fun TextFieldCommon(
    modifier: Modifier = Modifier,
    text: String,
    backgroundColor: Color = Color.Unspecified,
    textColor: Color = Color.Unspecified,
    disabledTextColor: Color = Color.Unspecified,
    unfocusedLabelColor: Color = Color.Unspecified,
    focusedLabelColor: Color = Color.Unspecified,
    errorLabelColor: Color = Color.Unspecified,
    disabledLabelColor: Color = Color.Unspecified,
    placeholderColor: Color = Color.Unspecified,
    disabledPlaceholderColor: Color = Color.Unspecified,
    cursorColor: Color = Color.Unspecified,
    errorCursorColor: Color = Color.Unspecified,
    focusedIndicatorColor: Color = Color.Unspecified,
    unfocusedIndicatorColor: Color = Color.Unspecified,
    errorIndicatorColor: Color = Color.Unspecified,
    disabledIndicatorColor: Color = Color.Unspecified,
    leadingIconColor: Color = Color.Unspecified,
    disabledLeadingIconColor: Color = Color.Unspecified,
    errorLeadingIconColor: Color = Color.Unspecified,
    trailingIconColor: Color = Color.Unspecified,
    disabledTrailingIconColor: Color = Color.Unspecified,
    errorTrailingIconColor: Color = Color.Unspecified,
    textDecoration: TextDecoration = TextDecoration.None,
    textAlign: TextAlign = TextAlign.Start,
    textDirection: TextDirection = TextDirection.Ltr,
    textIndent: TextIndent = TextIndent.None,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily.Default,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    shape: Shape = MaterialTheme.shapes.small.copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize),
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
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardActions: (String) -> Unit = {},
    onValueChange: (String) -> Unit = {},
) {
    val colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = backgroundColor,
        textColor = textColor, disabledTextColor = disabledTextColor,
        unfocusedLabelColor = unfocusedLabelColor, focusedLabelColor = focusedLabelColor, errorLabelColor = errorLabelColor, disabledLabelColor = disabledLabelColor,
        placeholderColor = placeholderColor, disabledPlaceholderColor = disabledPlaceholderColor,
        cursorColor = cursorColor, errorCursorColor = errorCursorColor,
        focusedIndicatorColor = focusedIndicatorColor, unfocusedIndicatorColor = unfocusedIndicatorColor, errorIndicatorColor = errorIndicatorColor, disabledIndicatorColor = disabledIndicatorColor,
        leadingIconColor = leadingIconColor, disabledLeadingIconColor = disabledLeadingIconColor, errorLeadingIconColor = errorLeadingIconColor,
        trailingIconColor = trailingIconColor, disabledTrailingIconColor = disabledTrailingIconColor, errorTrailingIconColor = errorTrailingIconColor,
    )

    TextFieldCommon(
        modifier,
        text,
        textDecoration, textAlign, textDirection, textIndent,
        fontSize, fontStyle, fontWeight, fontFamily,
        letterSpacing, lineHeight, textStyle,
        label, placeholder,
        leadingIcon, trailingIcon,
        enabled, readOnly, isError,
        singleLine, maxLines,
        shape, colors,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        interactionSource,
        visualTransformation,
        keyboardOptions,
        onKeyboardActions, onValueChange,
    )
}

/**
 * 使用 TextFieldColors 配置颜色模式
 * 默认的实现中，颜色模式使用 TextFieldDefaults.textFieldColors()。
 * 可以直接用，如果需要定制，则需要提供新的 TextFieldColors。
 *
 * @param
 * @return
 */
@Composable
fun TextFieldCommon(
    modifier: Modifier = Modifier,
    text: String,
    textDecoration: TextDecoration = TextDecoration.None,
    textAlign: TextAlign = TextAlign.Start,
    textDirection: TextDirection = TextDirection.Ltr,
    textIndent: TextIndent = TextIndent.None,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.Normal,
    fontFamily: FontFamily = FontFamily.Default,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    shape: Shape = MaterialTheme.shapes.small.copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize),
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
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
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardActions: (String) -> Unit = {},
    onValueChange: (String) -> Unit = {},
) {
    val modifierTextField: Modifier = modifier
        .padding(top = marginTop, bottom = marginBottom, start = marginStart, end = marginEnd)
        .modifierWidthAssemble(width, isFillMaxWidth)
        .modifierHeightAssemble(height, isFillMaxHeight)
        // 这里在 Modifier 中设置 background，会根据宽高来渲染背景色。
        // .background(backgroundColor)
        .padding(top = paddingTop, bottom = paddingBottom, start = paddingStart, end = paddingEnd)

    // textColor、textBackground 在 colors 中设置，不用单独配置
    // 这里在 TextStyle 中设置 background，只会渲染文字部分。
    val textStyleExtra: TextStyle = TextStyle(
        // color = textColor,
        // background = backgroundColor,
        textDecoration = textDecoration, textAlign = textAlign, textDirection = textDirection, textIndent = textIndent,
        fontSize = fontSize, fontStyle = fontStyle, fontWeight = fontWeight, fontFamily = fontFamily,
        letterSpacing = letterSpacing, lineHeight = lineHeight,
    )
    val textStyleRevised: TextStyle = textStyle.plus(textStyleExtra)

    TextField(
        text,
        onValueChange,
        modifierTextField,
        enabled, readOnly,
        textStyleRevised,
        label, placeholder,
        leadingIcon, trailingIcon,
        isError,
        visualTransformation,
        keyboardOptions, KeyboardActions { onKeyboardActions(text) },
        singleLine, maxLines,
        interactionSource,
        shape, colors,
    )
}
