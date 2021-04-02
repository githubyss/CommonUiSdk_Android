package com.githubyss.mobile.common.debug.recyclerview.search.template.fundmanager

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * FundManagerHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 11:32:48
 */
class FundManagerHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerFundManagerItem) as FlexboxLayout
    var layoutFundManager = view.findViewById(R.id.layout_fundManager) as FlexboxLayout
    var ivImage = view.findViewById(R.id.imageView_recyclerFundManagerImage) as ImageView
    var tvTitle = view.findViewById(R.id.textView_recyclerFundManagerTitle) as TextView
    var tvBestReturn = view.findViewById(R.id.textView_recyclerFundManagerBestReturn) as TextView
    var tvRiseFallRatio = view.findViewById(R.id.textView_recyclerFundManagerRiseFallRatio) as TextView
    var tvDescription = view.findViewById(R.id.textView_recyclerFundManagerDescription) as TextView
    
    var layoutFundProduct = view.findViewById(R.id.layout_fundProduct) as FlexboxLayout
    var tvProductTitle = view.findViewById(R.id.textView_recyclerFundProductTitle) as TextView
    var tvProductRiseFallRatio = view.findViewById(R.id.textView_recyclerFundProductRiseFallRatio) as TextView
    var tvProductCode = view.findViewById(R.id.textView_recyclerFundProductCode) as TextView
    var tvProductRisk = view.findViewById(R.id.textView_recyclerFundProductRisk) as TextView
    var tvProductClassify = view.findViewById(R.id.textView_recyclerFundProductClassify) as TextView
    var tvProductRiseFallTimeSpan = view.findViewById(R.id.textView_recyclerFundProductRiseFallTimeSpan) as TextView
    var tvProductFollowCount = view.findViewById(R.id.textView_recyclerFundProductFollowCount) as TextView
    var tglBtnProductIsFollowed = view.findViewById(R.id.button_recyclerFundProductIsFollowed) as ToggleButton
}
