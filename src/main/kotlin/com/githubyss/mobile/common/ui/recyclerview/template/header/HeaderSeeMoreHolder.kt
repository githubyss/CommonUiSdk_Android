package com.githubyss.mobile.common.ui.recyclerview.template.header

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * HeaderSeeMoreHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:40:00
 */
class HeaderSeeMoreHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerHeaderSeeMoreItem) as FlexboxLayout
    var tvTitle = view.findViewById(R.id.textView_recyclerHeaderSeeMoreTitle) as TextView
    var tvSeeMore = view.findViewById(R.id.textView_recyclerHeaderSeeMoreSeeMore) as TextView
}
