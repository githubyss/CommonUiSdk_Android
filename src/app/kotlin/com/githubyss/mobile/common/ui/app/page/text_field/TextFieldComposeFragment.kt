package com.githubyss.mobile.common.ui.app.page.text_field

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
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.res.common.dimen.FontSize20Sp
import com.githubyss.mobile.common.res.common.dimen.FontSize30Sp
import com.githubyss.mobile.common.res.common.dimen.SpaceNormal
import com.githubyss.mobile.common.res.common.dimen.SpaceSideMarginDefault
import com.githubyss.mobile.common.res.text_field.outlinedTextFieldShape
import com.githubyss.mobile.common.res.text_field.textFieldShape
import com.githubyss.mobile.common.res.text_field.textFieldTransparentBackgroundNormal
import com.githubyss.mobile.common.res.text_field.textFieldTransparentIndicatorFocused
import com.githubyss.mobile.common.ui.page.compose.PagePadding
import com.githubyss.mobile.common.ui.text_field.compose.*
import com.githubyss.mobile.common.ui.toolbar.compose.TopNavigationBar


/**
 * TextFieldComposeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/06/29 00:12:24
 */
class TextFieldComposeFragment : BaseComposeToolbarFragment() {

    /** ****************************** Properties ****************************** */

    companion object {
        val TAG: String = TextFieldComposeFragment::class.java.simpleName
    }

    private val textFieldComposeVm: TextFieldComposeViewModel by viewModels()


    /** ****************************** Override ****************************** */

    @Composable
    override fun Toolbar() {
        TopNavigationBar(textFieldComposeVm.title) { activity?.onBackPressed() }
    }

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

    @Composable
    private fun TextFields() {
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

        OutlinedTextFieldBlack(
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

        OutlinedTextFieldCommon(
            text = textFieldComposeVm.text,
            fontSize = TextUnit.FontSize20Sp,
            shape = TextFieldDefaults.outlinedTextFieldShape,
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

        TextFieldCommon(
            text = textFieldComposeVm.text,
            fontSize = TextUnit.FontSize20Sp,
            marginBottom = Dp.SpaceSideMarginDefault,
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        OutlinedTextFieldCommon(
            text = textFieldComposeVm.text,
            fontSize = TextUnit.FontSize20Sp,
            marginBottom = Dp.SpaceSideMarginDefault,
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldCommon(text = "默认的实现")

        TextField(value = textFieldComposeVm.text, onValueChange = { textFieldComposeVm.updateText(it) })

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
    }
}
