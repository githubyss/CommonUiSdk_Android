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
import com.githubyss.mobile.common.ui.page.compose.PageSidePadding
import com.githubyss.mobile.common.ui.text_field.compose.TextFieldBlack
import com.githubyss.mobile.common.ui.text_field.compose.TextFieldCommon
import com.githubyss.mobile.common.ui.text_field.compose.TextFieldPassword
import com.githubyss.mobile.common.ui.text_field.compose.TextFieldTransparent
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
        PageSidePadding(paddingVertical = Dp.SpaceNormal)
        {
            TextFields()
        }
    }


    /** ****************************** Functions ****************************** */

    @Composable
    private fun TextFields() {
        TextFieldCommon(text = "默认的实现")

        TextFieldCommon(
            text = textFieldComposeVm.text,
            textColor = Color.Blue,
            backgroundColor = Color.Red,
            fontSize = TextUnit.FontSize30Sp,
            marginTop = Dp.SpaceSideMarginDefault,
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldCommon(
            text = textFieldComposeVm.text,
            textColor = Color.Blue,
            backgroundColor = Color.Red,
            fontSize = TextUnit.FontSize30Sp,
            shape = TextFieldDefaults.textFieldShape,
            marginTop = Dp.SpaceSideMarginDefault,
            label = { Text(text = "请输入信息") },
            placeholder = { Text(text = "这是个例子") },
            leadingIcon = { Icon(Icons.Filled.AccountBox, contentDescription = "AccountBox") },
            trailingIcon = {
                Icon(Icons.Filled.Call, contentDescription = "Call", modifier = Modifier.clickable {
                })
            },
            onValueChange = { textFieldComposeVm.updateText(it) },
        )

        TextFieldBlack(
            text = textFieldComposeVm.text,
            fontSize = TextUnit.FontSize30Sp,
            marginTop = Dp.SpaceSideMarginDefault,
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
            marginTop = Dp.SpaceSideMarginDefault,
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

        TextFieldPassword(
            text = textFieldComposeVm.text,
            label = textFieldComposeVm.label,
            placeholder = textFieldComposeVm.placeholder,
            fontSize = TextUnit.FontSize30Sp,
            isError = textFieldComposeVm.isError,
            marginTop = Dp.SpaceSideMarginDefault,
            onKeyboardActions = {
                textFieldComposeVm.validateDigitsOnly(textFieldComposeVm.text)
                textFieldComposeVm.updateLabel("密码格式错误，请检查并改正")
            },
            onValueChange = {
                textFieldComposeVm.updateText(it)
                textFieldComposeVm.validateDigitsOnly(textFieldComposeVm.text)
            },
            onClear = { textFieldComposeVm.updateText("") }
        )
    }
}
