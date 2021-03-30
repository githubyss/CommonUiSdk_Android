package com.githubyss.mobile.common.debug.recyclerview.search.template.financeaq

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * FinanceAqHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 11:56:09
 */
class FinanceAqHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerFinanceAqItem) as FlexboxLayout
    var tvTitle = view.findViewById(R.id.textView_recyclerFinanceAqTitle) as TextView
    var tvContent = view.findViewById(R.id.textView_recyclerFinanceAqContent) as TextView
}
