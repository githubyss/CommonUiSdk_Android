package com.githubyss.mobile.common.ui.app.page.text_field

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.fragment.app.viewModels
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.res.common.dimen.FontSize30Sp
import com.githubyss.mobile.common.res.common.dimen.SpaceNormal
import com.githubyss.mobile.common.res.common.dimen.SpaceSideMarginDefault
import com.githubyss.mobile.common.res.text_field.textFieldShape
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
            text = textFieldComposeVm.text,
            label = textFieldComposeVm.label,
            placeholder = textFieldComposeVm.placeholder,
            fontSize = TextUnit.FontSize30Sp,
            marginBottom = Dp.SpaceSideMarginDefault,
            onKeyboardActions = {
            },
            onValueChange = {
                textFieldComposeVm.updateText(it)
            },
            onClear = {
                textFieldComposeVm.updateText("")
            },
            onErrorChange = {
                textFieldComposeVm.updateLabel(it)
            },
        )

        TextFieldPassword(
            text = textFieldComposeVm.text,
            label = textFieldComposeVm.label,
            placeholder = textFieldComposeVm.placeholder,
            fontSize = TextUnit.FontSize30Sp,
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
            fontSize = TextUnit.FontSize30Sp,
            marginBottom = Dp.SpaceSideMarginDefault,
            label = { Text(text = "请输入信息") },
            placeholder = { Text(text = "这是个例子") },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Lock") },
            trailingIcon = {
                Icon(Icons.Filled.Clear, contentDescription = "Clear", modifier = Modifier.clickable {
                })
            },
            singleLine = true,
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldTransparent(
            text = textFieldComposeVm.text,
            fontSize = TextUnit.FontSize30Sp,
            marginBottom = Dp.SpaceSideMarginDefault,
            label = { Text(text = "请输入信息") },
            placeholder = { Text(text = "这是个例子") },
            leadingIcon = { Icon(Icons.Filled.Add, contentDescription = "Add") },
            trailingIcon = {
                Icon(Icons.Filled.Favorite, contentDescription = "Favorite", modifier = Modifier.clickable {
                })
            },
            maxLines = 1,
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldCommon(
            text = textFieldComposeVm.text,
            textColor = Color.Blue,
            backgroundColor = Color.Red,
            fontSize = TextUnit.FontSize30Sp,
            shape = TextFieldDefaults.textFieldShape,
            marginBottom = Dp.SpaceSideMarginDefault,
            label = { Text(text = "请输入信息") },
            placeholder = { Text(text = "这是个例子") },
            leadingIcon = { Icon(Icons.Filled.AccountBox, contentDescription = "AccountBox") },
            trailingIcon = {
                Icon(Icons.Filled.Call, contentDescription = "Call", modifier = Modifier.clickable {
                })
            },
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldCommon(
            text = textFieldComposeVm.text,
            textColor = Color.Blue,
            backgroundColor = Color.Red,
            fontSize = TextUnit.FontSize30Sp,
            marginBottom = Dp.SpaceSideMarginDefault,
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldCommon(text = "默认的实现")
    }
}
