package com.githubyss.common.ui.header_content.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.githubyss.common.res.common.dimen.SpaceNone
import com.githubyss.common.ui.text.compose.TextCommon
import com.githubyss.common.ui.utils.modifierHeightAssemble
import com.githubyss.common.ui.utils.modifierWidthAssemble


@Composable
fun HeaderContentCommon(
    modifier: Modifier = Modifier,
    headerText: String = "",
    headerTextColor: Color = Color.Unspecified,
    headerTextBackground: Color = Color.Unspecified,
    headerTextDecoration: TextDecoration = TextDecoration.None,
    headerTextAlign: TextAlign = TextAlign.Start,
    headerTextDirection: TextDirection = TextDirection.Ltr,
    headerTextIndent: TextIndent = TextIndent.None,
    headerFontSize: TextUnit = TextUnit.Unspecified,
    headerFontStyle: FontStyle = FontStyle.Normal,
    headerFontWeight: FontWeight = FontWeight.Normal,
    headerFontFamily: FontFamily = FontFamily.Default,
    headerLetterSpacing: TextUnit = TextUnit.Unspecified,
    headerLineHeight: TextUnit = TextUnit.Unspecified,
    headerOverflow: TextOverflow = TextOverflow.Clip,
    headerMarginTop: Dp = Dp.SpaceNone,
    headerMarginBottom: Dp = Dp.SpaceNone,
    headerMarginStart: Dp = Dp.SpaceNone,
    headerMarginEnd: Dp = Dp.SpaceNone,
    headerIcon: @Composable (() -> Unit) = {},
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
    onIconClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val modifierColumn: Modifier = modifier
        .padding(top = marginTop, bottom = marginBottom, start = marginStart, end = marginEnd)
        .modifierWidthAssemble(width, isFillMaxWidth)
        .modifierHeightAssemble(height, isFillMaxHeight)
        .padding(top = paddingTop, bottom = paddingBottom, start = paddingStart, end = paddingEnd)

    Column(
        modifierColumn,
        Arrangement.Center,
        Alignment.CenterHorizontally,
    )
    {
        val modifierHeader: Modifier = modifier
            .padding(top = headerMarginTop, bottom = headerMarginBottom, start = headerMarginStart, end = headerMarginEnd)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Transparent)

        ConstraintLayout(modifierHeader)
        {
            val (header_text, header_icon) = createRefs()

            val headerSoftWrap: Boolean = false
            val headerMaxLines: Int = 1
            TextCommon(
                text = headerText,
                textColor = headerTextColor,
                textBackground = headerTextBackground,
                textDecoration = headerTextDecoration,
                textAlign = headerTextAlign,
                textDirection = headerTextDirection,
                textIndent = headerTextIndent,
                fontSize = headerFontSize,
                fontStyle = headerFontStyle,
                fontWeight = headerFontWeight,
                fontFamily = headerFontFamily,
                letterSpacing = headerLetterSpacing,
                lineHeight = headerLineHeight,
                overflow = headerOverflow,
                softWrap = headerSoftWrap,
                maxLines = headerMaxLines,
                isFillMaxWidth = true,
                modifier = Modifier
                    .constrainAs(header_text) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(header_icon.start)
                        this.width = Dimension.fillToConstraints
                    },
            )

            val modifierIcon: Modifier = modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .background(Color.Transparent)
                .clickable {
                    onIconClick()
                }
            Box(
                modifier = modifierIcon
                    .constrainAs(header_icon) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(header_text.end)
                        end.linkTo(parent.end)
                    },
            ) {
                headerIcon()
            }
        }

        content()
    }
}
