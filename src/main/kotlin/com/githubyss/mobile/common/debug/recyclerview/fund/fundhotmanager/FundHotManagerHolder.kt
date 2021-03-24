package com.githubyss.mobile.common.debug.recyclerview.fund.fundhotmanager

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * FundHotManagerHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 11:32:48
 */
class FundHotManagerHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerFundHotManagerItem) as FlexboxLayout
    var tvTitle = view.findViewById(R.id.textView_recyclerFundHotManagerTitle) as TextView
    var tvHeaderImage = view.findViewById(R.id.textView_recyclerFundHotManagerHeaderImage) as ImageView
    var tvBestReturn = view.findViewById(R.id.textView_recyclerFundHotManagerBestReturn) as TextView
    var tvRiseFallRatio = view.findViewById(R.id.textView_recyclerFundHotManagerRiseFallRatio) as TextView
    var tvDescription = view.findViewById(R.id.textView_recyclerFundHotManagerDescription) as TextView
}
