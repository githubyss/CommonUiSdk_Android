package com.githubyss.common.ui.app.page.text_field

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.githubyss.common.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.common.res.common.dimen.FontSize20Sp
import com.githubyss.common.res.common.dimen.FontSize30Sp
import com.githubyss.common.res.common.dimen.SpaceNormal
import com.githubyss.common.res.common.dimen.SpaceSideMarginDefault
import com.githubyss.common.res.text_field.textFieldOutlinedShape
import com.githubyss.common.res.text_field.textFieldShape
import com.githubyss.common.res.text_field.textFieldTransparentBackgroundNormal
import com.githubyss.common.res.text_field.textFieldTransparentIndicatorFocused
import com.githubyss.common.ui.page.compose.PagePadding
import com.githubyss.common.ui.text_field.compose.*
import com.githubyss.common.ui.toolbar.compose.TopNavigationBar


/**
 * TextFieldComposeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/06/29 00:12:24
 */
class TextFieldComposeFragment : BaseComposeToolbarFragment() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = TextFieldComposeFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val textFieldComposeVm: TextFieldComposeViewModel by viewModels()


    /** ****************************** Override ****************************** */

    /**  */
    @Composable
    override fun Toolbar() {
        TopNavigationBar(textFieldComposeVm.title) { activity?.onBackPressed() }
    }

    /**  */
    @Composable
    override fun Content() {
        PagePadding(
            paddingTop = Dp.SpaceNormal,
            paddingBottom = Dp.SpaceNormal,
        )
        {
            TextFields()
        }
    }


    /** ****************************** Functions ****************************** */

    /**  */
    @Composable
    private fun TextFields() {
        BasicTextField(
            value = textFieldComposeVm.text, onValueChange = { textFieldComposeVm.updateText(it) },
            modifier = Modifier
                // .defaultMinSize(
                //     minWidth = TextFieldDefaults.MinWidth,
                //     minHeight = TextFieldDefaults.MinHeight
                // )
                .background(color = Color.textFieldTransparentBackgroundNormal, shape = TextFieldDefaults.textFieldShape)
                .drawBehind {
                    val strokeWidth = 1.dp.value * density
                    val y = size.height - strokeWidth / 2
                    drawLine(
                        Color.textFieldTransparentIndicatorFocused,
                        Offset(0f, y),
                        Offset(size.width, y),
                        strokeWidth
                    )
                }
        )

        TextFieldCommonBasic(text = "默认的实现")

        TextFieldCommonBasic(
            text = textFieldComposeVm.text,
            marginBottom = Dp.SpaceSideMarginDefault,
            width = 50.dp,
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldCommonBasic(
            text = textFieldComposeVm.text,
            normalBackgroundColor = Color.Blue,
            normalTextColor = Color.White,
            disabledTextColor = Color.LightGray,
            normalCursorColor = Color.White,
            errorCursorColor = Color.Red,
            focusedIndicatorColor = Color.Yellow,
            unfocusedIndicatorColor = Color.Black,
            errorIndicatorColor = Color.Red,
            disabledIndicatorColor = Color.LightGray,
            fontSize = TextUnit.FontSize20Sp,
            singleLine = true,
            maxLines = Int.MAX_VALUE,
            marginBottom = Dp.SpaceSideMarginDefault,
            width = 50.dp,
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextField(
            value = textFieldComposeVm.text,
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldCommon(text = "默认的实现")

        TextFieldCommon(
            text = textFieldComposeVm.text,
            fontSize = TextUnit.FontSize20Sp,
            marginBottom = Dp.SpaceSideMarginDefault,
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldCommon(
            text = textFieldComposeVm.text,
            fontSize = TextUnit.FontSize20Sp,
            shape = TextFieldDefaults.textFieldShape,
            marginBottom = Dp.SpaceSideMarginDefault,
            label = { Text(text = "提示") },
            placeholder = { Text(text = "占位示例") },
            leadingIcon = { Icon(Icons.Filled.AccountBox, contentDescription = "AccountBox") },
            trailingIcon = {
                Icon(Icons.Filled.Call, contentDescription = "Call", modifier = Modifier.clickable {
                })
            },
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldCommonOutlined(
            text = textFieldComposeVm.text,
            fontSize = TextUnit.FontSize20Sp,
            marginBottom = Dp.SpaceSideMarginDefault,
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldCommonOutlined(
            text = textFieldComposeVm.text,
            fontSize = TextUnit.FontSize20Sp,
            shape = TextFieldDefaults.textFieldOutlinedShape,
            marginBottom = Dp.SpaceSideMarginDefault,
            label = { Text(text = "提示") },
            placeholder = { Text(text = "占位示例") },
            leadingIcon = { Icon(Icons.Filled.AccountBox, contentDescription = "AccountBox") },
            trailingIcon = {
                Icon(Icons.Filled.Call, contentDescription = "Call", modifier = Modifier.clickable {
                })
            },
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldTransparent(
            text = textFieldComposeVm.text,
            fontSize = TextUnit.FontSize20Sp,
            marginBottom = Dp.SpaceSideMarginDefault,
            label = { Text(text = "提示") },
            placeholder = { Text(text = "占位示例") },
            leadingIcon = { Icon(Icons.Filled.Add, contentDescription = "Add") },
            trailingIcon = {
                Icon(Icons.Filled.Favorite, contentDescription = "Favorite", modifier = Modifier.clickable {
                })
            },
            maxLines = 1,
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldBlack(
            text = textFieldComposeVm.text,
            fontSize = TextUnit.FontSize20Sp,
            marginBottom = Dp.SpaceSideMarginDefault,
            label = { Text(text = "提示") },
            placeholder = { Text(text = "占位示例") },
            leadingIcon = { Icon(Icons.Filled.Add, contentDescription = "Add") },
            trailingIcon = {
                Icon(Icons.Filled.Favorite, contentDescription = "Favorite", modifier = Modifier.clickable {
                })
            },
            singleLine = true,
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldBlackOutlined(
            text = textFieldComposeVm.text,
            fontSize = TextUnit.FontSize20Sp,
            marginBottom = Dp.SpaceSideMarginDefault,
            label = { Text(text = "提示") },
            placeholder = { Text(text = "占位示例") },
            leadingIcon = { Icon(Icons.Filled.Add, contentDescription = "Add") },
            trailingIcon = {
                Icon(Icons.Filled.Favorite, contentDescription = "Favorite", modifier = Modifier.clickable {
                })
            },
            singleLine = true,
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldPassword(
            text = textFieldComposeVm.text,
            label = textFieldComposeVm.label,
            placeholder = textFieldComposeVm.placeholder,
            fontSize = TextUnit.FontSize20Sp,
            isError = textFieldComposeVm.isError,
            marginBottom = Dp.SpaceSideMarginDefault,
            onKeyboardActions = {
                textFieldComposeVm.validateDigits(it)
                textFieldComposeVm.updateLabel(textFieldComposeVm.isError)
            },
            onValueChange = {
                textFieldComposeVm.updateText(it)
                textFieldComposeVm.validateDigits(it)
                textFieldComposeVm.updateLabel(textFieldComposeVm.isError)
            },
            onClear = {
                textFieldComposeVm.updateText("")
                textFieldComposeVm.validateDigits("")
                textFieldComposeVm.updateLabel(textFieldComposeVm.isError)
            }
        )

        TextFieldPasswordOutlined(
            text = textFieldComposeVm.text,
            label = textFieldComposeVm.label,
            placeholder = textFieldComposeVm.placeholder,
            fontSize = TextUnit.FontSize20Sp,
            isError = textFieldComposeVm.isError,
            marginBottom = Dp.SpaceSideMarginDefault,
            onKeyboardActions = {
                textFieldComposeVm.validateDigits(it)
                textFieldComposeVm.updateLabel(textFieldComposeVm.isError)
            },
            onValueChange = {
                textFieldComposeVm.updateText(it)
                textFieldComposeVm.validateDigits(it)
                textFieldComposeVm.updateLabel(textFieldComposeVm.isError)
            },
            onClear = {
                textFieldComposeVm.updateText("")
                textFieldComposeVm.validateDigits("")
                textFieldComposeVm.updateLabel(textFieldComposeVm.isError)
            }
        )

        TextFieldPasswordNumber(
            text = textFieldComposeVm.textPasswordNumber,
            label = textFieldComposeVm.labelPasswordNumber,
            placeholder = textFieldComposeVm.placeholder,
            fontSize = TextUnit.FontSize30Sp,
            marginBottom = Dp.SpaceSideMarginDefault,
            onKeyboardActions = {
            },
            onValueChange = {
                textFieldComposeVm.updateTextPasswordNumber(it)
            },
            onClear = {
                textFieldComposeVm.updateTextPasswordNumber("")
            },
            onErrorChange = {
                textFieldComposeVm.updateLabelPasswordNumber(it)
            },
        )

        TextFieldPasswordNumberOutlined(
            text = textFieldComposeVm.textPasswordNumber,
            label = textFieldComposeVm.labelPasswordNumber,
            placeholder = textFieldComposeVm.placeholder,
            fontSize = TextUnit.FontSize30Sp,
            marginBottom = Dp.SpaceSideMarginDefault,
            onKeyboardActions = {
            },
            onValueChange = {
                textFieldComposeVm.updateTextPasswordNumber(it)
            },
            onClear = {
                textFieldComposeVm.updateTextPasswordNumber("")
            },
            onErrorChange = {
                textFieldComposeVm.updateLabelPasswordNumber(it)
            },
        )
    }
}
