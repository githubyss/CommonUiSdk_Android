package com.githubyss.mobile.common.debug.recyclerview.search.template.fundmanager

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.textview.KeyWordHighlightTextView
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
    var tvTitle = view.findViewById(R.id.textView_recyclerFundManagerTitle) as KeyWordHighlightTextView
    var tvBestReturn = view.findViewById(R.id.textView_recyclerFundManagerBestReturn) as TextView
    var tvRiseFallRatio = view.findViewById(R.id.textView_recyclerFundManagerRiseFallRatio) as TextView
    var tvDescription = view.findViewById(R.id.textView_recyclerFundManagerDescription) as KeyWordHighlightTextView
}
