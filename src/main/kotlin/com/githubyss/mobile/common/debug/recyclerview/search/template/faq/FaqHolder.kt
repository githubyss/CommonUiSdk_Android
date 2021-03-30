package com.githubyss.mobile.common.debug.recyclerview.search.template.faq

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * FaqHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 17:27:11
 */
class FaqHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerFaqItem) as FlexboxLayout
    var tvContent = view.findViewById(R.id.textView_recyclerFaqContent) as TextView
}
