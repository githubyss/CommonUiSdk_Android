package com.githubyss.mobile.common.debug.recyclerview.multi.template.header

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * HeaderHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 10:00:25
 */
class HeaderHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerHeaderItem) as FlexboxLayout
    var tvTitle = view.findViewById(R.id.textView_recyclerHeaderSeeMoreTitle) as TextView
}
