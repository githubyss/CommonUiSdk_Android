package com.githubyss.common.ui.text_field.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
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
import com.githubyss.common.res.common.dimen.SpaceNone
import com.githubyss.common.ui.utils.drawTextFieldIndicatorLineBottom
import com.githubyss.common.ui.utils.modifierHeightAssemble
import com.githubyss.common.ui.utils.modifierWidthAssemble


/**
 * 使用独立的颜色配置颜色模式
 * 默认的实现，颜色都是未指定，也就是透明色。
 * 所以不能直接用默认值，需要指定一部分颜色才可以。
 *
 * @param
 * @return
 */
@Composable
fun TextFieldCommonBasic(
    modifier: Modifier = Modifier,
    text: String,
    normalBackgroundColor: Color = Color.Unspecified,
    normalTextColor: Color = Color.Unspecified,
    disabledTextColor: Color = Color.Unspecified,
    unfocusedLabelColor: Color = Color.Unspecified,
    focusedLabelColor: Color = Color.Unspecified,
    errorLabelColor: Color = Color.Unspecified,
    disabledLabelColor: Color = Color.Unspecified,
    normalPlaceholderColor: Color = Color.Unspecified,
    disabledPlaceholderColor: Color = Color.Unspecified,
    normalCursorColor: Color = Color.Unspecified,
    errorCursorColor: Color = Color.Unspecified,
    focusedIndicatorColor: Color = Color.Unspecified,
    unfocusedIndicatorColor: Color = Color.Unspecified,
    errorIndicatorColor: Color = Color.Unspecified,
    disabledIndicatorColor: Color = Color.Unspecified,
    normalLeadingIconColor: Color = Color.Unspecified,
    disabledLeadingIconColor: Color = Color.Unspecified,
    errorLeadingIconColor: Color = Color.Unspecified,
    normalTrailingIconColor: Color = Color.Unspecified,
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
    isError: Boolean = false,
    readOnly: Boolean = false,
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
    decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit = @Composable { innerTextField -> innerTextField() },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardActions: (String) -> Unit = {},
    onValueChange: (String) -> Unit = {},
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = normalBackgroundColor,
        textColor = normalTextColor, disabledTextColor = disabledTextColor,
        unfocusedLabelColor = unfocusedLabelColor, focusedLabelColor = focusedLabelColor, errorLabelColor = errorLabelColor, disabledLabelColor = disabledLabelColor,
        placeholderColor = normalPlaceholderColor, disabledPlaceholderColor = disabledPlaceholderColor,
        cursorColor = normalCursorColor, errorCursorColor = errorCursorColor,
        focusedIndicatorColor = focusedIndicatorColor, unfocusedIndicatorColor = unfocusedIndicatorColor, errorIndicatorColor = errorIndicatorColor, disabledIndicatorColor = disabledIndicatorColor,
        leadingIconColor = normalLeadingIconColor, disabledLeadingIconColor = disabledLeadingIconColor, errorLeadingIconColor = errorLeadingIconColor,
        trailingIconColor = normalTrailingIconColor, disabledTrailingIconColor = disabledTrailingIconColor, errorTrailingIconColor = errorTrailingIconColor,
    )

    TextFieldCommonBasic(
        modifier,
        text,
        textDecoration, textAlign, textDirection, textIndent,
        fontSize, fontStyle, fontWeight, fontFamily,
        letterSpacing, lineHeight, textStyle,
        label, placeholder,
        leadingIcon, trailingIcon,
        enabled, isError, readOnly,
        singleLine, maxLines,
        shape, colors,
        marginTop, marginBottom, marginStart, marginEnd,
        paddingTop, paddingBottom, paddingStart, paddingEnd,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
        interactionSource,
        visualTransformation,
        decorationBox,
        keyboardOptions,
        onKeyboardActions, onValueChange, onTextLayout,
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
fun TextFieldCommonBasic(
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
    isError: Boolean = false,
    readOnly: Boolean = false,
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
    decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit = @Composable { innerTextField -> innerTextField() },
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardActions: (String) -> Unit = {},
    onValueChange: (String) -> Unit = {},
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    val textColor: Color = colors.textColor(enabled = enabled).value
    val backgroundColor: Color = colors.backgroundColor(enabled = enabled).value
    val cursorBrush: SolidColor = SolidColor(colors.cursorColor(isError = isError).value)
    val indicatorColor: Color = colors.indicatorColor(enabled = enabled, isError = isError, interactionSource = interactionSource).value

    val modifierTextField: Modifier = modifier
        .defaultMinSize(
            minWidth = 50.dp,
            minHeight = TextFieldDefaults.MinHeight
        )
        .padding(top = marginTop, bottom = marginBottom, start = marginStart, end = marginEnd)
        .modifierWidthAssemble(width, isFillMaxWidth)
        .modifierHeightAssemble(height, isFillMaxHeight)
        // 这里在 Modifier 中设置 background，会根据宽高来渲染背景色。
        .background(color = backgroundColor, shape = shape)
        .padding(top = paddingTop, bottom = paddingBottom, start = paddingStart, end = paddingEnd)
        .drawTextFieldIndicatorLineBottom(indicatorColor)

    // 这里在 TextStyle 中设置 background，只会渲染文字部分。
    val textStyleExtra: TextStyle = TextStyle(
        color = textColor,
        // background = backgroundColor,
        textDecoration = textDecoration, textAlign = textAlign, textDirection = textDirection, textIndent = textIndent,
        fontSize = fontSize, fontStyle = fontStyle, fontWeight = fontWeight, fontFamily = fontFamily,
        letterSpacing = letterSpacing, lineHeight = lineHeight,
    )
    val textStyleRevised: TextStyle = textStyle.plus(textStyleExtra)

    BasicTextField(
        text,
        onValueChange,
        modifierTextField,
        enabled, readOnly,
        textStyleRevised,
        keyboardOptions, KeyboardActions { onKeyboardActions(text) },
        singleLine, maxLines,
        visualTransformation,
        onTextLayout,
        interactionSource,
        cursorBrush,
        decorationBox,
    )
}
