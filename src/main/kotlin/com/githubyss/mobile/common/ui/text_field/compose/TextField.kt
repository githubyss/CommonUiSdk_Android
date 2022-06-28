// package com.githubyss.mobile.common.ui.text_field.compose
//
// import androidx.compose.foundation.clickable
// import androidx.compose.foundation.interaction.MutableInteractionSource
// import androidx.compose.foundation.shape.ZeroCornerSize
// import androidx.compose.foundation.text.KeyboardActions
// import androidx.compose.foundation.text.KeyboardOptions
// import androidx.compose.material.*
// import androidx.compose.material.icons.Icons
// import androidx.compose.material.icons.filled.Clear
// import androidx.compose.material.icons.filled.Lock
// import androidx.compose.runtime.Composable
// import androidx.compose.runtime.remember
// import androidx.compose.ui.Modifier
// import androidx.compose.ui.graphics.Shape
// import androidx.compose.ui.text.TextStyle
// import androidx.compose.ui.text.input.VisualTransformation
// import com.githubyss.mobile.common.res.card.cardOrangeContent
//
//
// @Composable
// fun TextFieldPassword(
//     modifier: Modifier = Modifier,
//     text: String,
//     label: String,
//     placeholder: String,
//     leadingIcon: @Composable () -> Unit = { Icon(Icons.Filled.Lock, contentDescription = "Password") },
//     trailingIcon: @Composable () -> Unit = {
//         Icon(Icons.Filled.Clear, contentDescription = "Clear", modifier = Modifier.clickable {
//             onClear()
//         })
//     },
//     enabled: Boolean = true,
//     readOnly: Boolean = false,
//     singleLine: Boolean = false,
//     isError: Boolean = false,
//     maxLines: Int = Int.MAX_VALUE,
//     textStyle: TextStyle = LocalTextStyle.current,
//     visualTransformation: VisualTransformation = VisualTransformation.None,
//     interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//     shape: Shape = MaterialTheme.shapes.small.copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize),
//     colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
//     keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//     onKeyboardActions: (String) -> Unit = {},
//     onClear: () -> Unit = {},
//     onValueChange: (String) -> Unit = {},
// ) {
//     TextField(
//         value = text,
//         label = { Text(text = label) },
//         placeholder = { Text(text = placeholder) },
//         leadingIcon = leadingIcon,
//         trailingIcon = trailingIcon,
//         enabled = enabled,
//         readOnly = readOnly,
//         singleLine = singleLine,
//         isError = isError,
//         maxLines = maxLines,
//         textStyle = textStyle,
//         visualTransformation = visualTransformation,
//         interactionSource = interactionSource,
//         shape = shape,
//         colors = colors,
//         keyboardOptions = keyboardOptions,
//         keyboardActions = KeyboardActions { onKeyboardActions(text) },
//         textStyle = MaterialTheme.cardOrangeContent,
//         onValueChange = onValueChange,
//         modifier = modifier,
//     )
// }