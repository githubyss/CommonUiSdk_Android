package com.githubyss.mobile.common.ui.card.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.githubyss.mobile.common.res.card.cardOrangeTextPrimary
import com.githubyss.mobile.common.res.card.cardOrangeTextSecondary
import com.githubyss.mobile.common.res.card.cardShape
import com.githubyss.mobile.common.res.common.dimen.ElevationLow
import com.githubyss.mobile.common.res.common.dimen.SideNone
import com.githubyss.mobile.common.res.common.dimen.SideTiny


@Composable
fun Card2Text1Switch(
    modifier: Modifier = Modifier,
    textPrimary: String = "",
    textSecondary: String = "",
    shape: Shape = MaterialTheme.cardShape,
    elevation: Dp = Dp.ElevationLow,
    marginStart: Dp = Dp.SideNone,
    marginTop: Dp = Dp.SideNone,
    marginEnd: Dp = Dp.SideNone,
    marginBottom: Dp = Dp.SideNone,
    paddingStart: Dp = Dp.SideTiny,
    paddingTop: Dp = Dp.SideTiny,
    paddingEnd: Dp = Dp.SideTiny,
    paddingBottom: Dp = Dp.SideTiny,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
    isFillMaxWidth: Boolean = false,
    isFillMaxHeight: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    CardOrange(
        modifier,
        shape,
        elevation,
        marginStart, marginTop, marginEnd, marginBottom,
        paddingStart, paddingTop, paddingEnd, paddingBottom,
        width, height,
        isFillMaxWidth, isFillMaxHeight,
    )
    {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Transparent),
        )
        {
            val (text_primary, text_secondary, switch) = createRefs()
            val textEndBarrier = createEndBarrier(text_primary, text_secondary, margin = Dp.SideTiny)
            Text(
                text = textPrimary,
                textAlign = TextAlign.Start,
                style = MaterialTheme.cardOrangeTextPrimary,
                modifier = Modifier
                    .constrainAs(text_primary) {
                        top.linkTo(parent.top)
                        bottom.linkTo(text_secondary.top)
                        start.linkTo(parent.start)
                        end.linkTo(textEndBarrier)
                        this.width = Dimension.fillToConstraints
                    }
                    .background(Color.Transparent),
            )
            Text(
                text = textSecondary,
                textAlign = TextAlign.Start,
                style = MaterialTheme.cardOrangeTextSecondary,
                modifier = Modifier
                    .constrainAs(text_secondary) {
                        top.linkTo(text_primary.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(textEndBarrier)
                        this.width = Dimension.fillToConstraints
                    }
                    .background(Color.Transparent),
            )
            Switch(
                checked = false,
                onCheckedChange = onCheckedChange,
                modifier = Modifier
                    .constrainAs(switch) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(textEndBarrier)
                        end.linkTo(parent.end)
                    }
            )
        }
    }
}
