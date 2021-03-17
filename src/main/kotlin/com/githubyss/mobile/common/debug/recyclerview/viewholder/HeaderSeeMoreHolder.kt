package com.githubyss.mobile.common.debug.recyclerview.viewholder

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
    var layoutItem = view.findViewById(R.id.layout_recyclerItemHeaderSeeMore) as FlexboxLayout
    var tvTitle = view.findViewById(R.id.textView_recyclerHeader) as TextView
    var tvSeeMore = view.findViewById(R.id.textView_recyclerSeeMore) as TextView
}
