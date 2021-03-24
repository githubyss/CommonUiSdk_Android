package com.githubyss.mobile.common.debug.recyclerview.fund.fundhot

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * FundHotHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/22 17:11:12
 */
class FundHotHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerFundHotItem) as FlexboxLayout
    var tvTitle = view.findViewById(R.id.textView_recyclerFundHotTitle) as TextView
    var tvRiseFallRatio = view.findViewById(R.id.textView_recyclerFundHotRiseFallRatio) as TextView
    var tvHint = view.findViewById(R.id.textView_recyclerFundHotHint) as TextView
    var tvRiseFallTimeSpan = view.findViewById(R.id.textView_recyclerFundHotRiseFallTimeSpan) as TextView
}
