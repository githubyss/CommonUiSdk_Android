package com.githubyss.mobile.common.ui.app.page.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.githubyss.mobile.common.kit.base.activity_fragment.compose.BaseComposeToolbarFragment
import com.githubyss.mobile.common.res.common.dimen.*
import com.githubyss.mobile.common.ui.page.compose.PagePadding
import com.githubyss.mobile.common.ui.text.compose.TextCommon
import com.githubyss.mobile.common.ui.toolbar.compose.TopNavigationBar


/**
 * TextComposeFragment
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/06/28 16:11:00
 */
class TextComposeFragment : BaseComposeToolbarFragment() {

    /** ****************************** Companion ****************************** */

    /**  */
    companion object {
        val TAG: String = TextComposeFragment::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    /**  */
    private val textComposeVm: TextComposeViewModel by viewModels()


    /** ****************************** Override ****************************** */

    /**  */
    @Composable
    override fun Toolbar() {
        TopNavigationBar(textComposeVm.title) { activity?.onBackPressed() }
    }

    /**  */
    @Composable
    override fun Content() {
        PagePadding(
            paddingTop = Dp.SpaceNormal,
            paddingBottom = Dp.SpaceNormal,
        )
        {
            Texts()
        }
    }


    /** ****************************** Functions ****************************** */

    /**  */
    @Composable
    private fun Texts() {
        TextCommon(
            text = "《Tinder诈骗王》",
            textColor = Color.Yellow,
            fontSize = TextUnit.FontSize20Sp,
            textBackground = Color.DarkGray,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
            letterSpacing = TextUnit.SpaceNormal,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
            maxLines = 5,
            marginTop = Dp.SpaceSideMarginDefault,
            paddingTop = Dp.SpaceTextPaddingDefault,
            paddingBottom = Dp.SpaceTextPaddingDefault,
            paddingStart = Dp.SpaceTextPaddingDefault,
            paddingEnd = Dp.SpaceTextPaddingDefault,
            height = 100.dp,
            isFillMaxWidth = true,
        )

        TextCommon(
            text = "《Tinder诈骗王》海报这位名叫Simon Leviev的以色列骗子之所以“称王”，确实诈骗手段高超，不仅时间管理非常强，超级有耐心，诈骗剧本扎实，还知道怎么规避法律风险，堪称完美骗局。所以本片的结局令人心塞。姑娘们依旧债台高筑，面临法院起诉，骗子出狱后却还能过着奢靡生活，美女香车，游戏人间。",
            textColor = Color.Yellow,
            fontSize = TextUnit.FontSize20Sp,
            textBackground = Color.DarkGray,
            marginTop = Dp.SpaceSideMarginDefault,
            paddingTop = Dp.SpaceTextPaddingDefault,
            paddingBottom = Dp.SpaceTextPaddingDefault,
            paddingStart = Dp.SpaceTextPaddingDefault,
            paddingEnd = Dp.SpaceTextPaddingDefault,
            isFillMaxWidth = true,
        )

        TextCommon(
            text = "自定义颜色、字号、背景色",
            textColor = Color.Red,
            fontSize = TextUnit.FontSize30Sp,
            textBackground = Color.Yellow,
        )

        TextCommon(text = "默认的实现")
    }
}
