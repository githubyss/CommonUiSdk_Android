package com.githubyss.mobile.common.debug.recyclerview.search.template.fundtopic

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.textview.KeyWordHighlightTextView
import com.google.android.flexbox.FlexboxLayout


/**
 * FundTopicHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/22 17:11:12
 */
class FundTopicHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerFundTopicItem) as FlexboxLayout
    var tvTitle = view.findViewById(R.id.textView_recyclerFundTopicTitle) as KeyWordHighlightTextView
    var tvRiseFallRatio = view.findViewById(R.id.textView_recyclerFundTopicRiseFallRatio) as TextView
    var tvHint = view.findViewById(R.id.textView_recyclerFundTopicHint) as TextView
    var tvRiseFallTimeSpan = view.findViewById(R.id.textView_recyclerFundTopicRiseFallTimeSpan) as TextView
}
