package com.githubyss.mobile.common.debug.recyclerview.fund.fundproduct

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * FundProductHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:17:36
 */
class FundProductHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerItemFundProduct) as FlexboxLayout
    var tvTitle = view.findViewById(R.id.textView_recyclerFundProductTitle) as TextView
    var tvDetail = view.findViewById(R.id.textView_recyclerFundProductDetail) as TextView
    var tvRiseFallRatio = view.findViewById(R.id.textView_recyclerFundProductRiseFallRatio) as TextView
    var tvRiseFallTimeSpan = view.findViewById(R.id.textView_recyclerFundProductRiseFallTimeSpan) as TextView
    var btnIsFollowed = view.findViewById(R.id.button_recyclerFundProductIsFollowed) as TextView
    var tvFollowCount = view.findViewById(R.id.textView_recyclerFundProductFollowCount) as TextView
}
