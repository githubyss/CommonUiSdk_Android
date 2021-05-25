package com.githubyss.mobile.common.debug.recyclerview.search.template.headerhasmore

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * HeaderHasMoreHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:40:00
 */
class HeaderHasMoreHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerHeaderHasMoreItem) as FlexboxLayout
    var tvTitle = view.findViewById(R.id.textView_recyclerHeaderHasMoreTitle) as TextView
    var tvMore = view.findViewById(R.id.textView_recyclerHeaderHasMoreSeeMore) as TextView
}
