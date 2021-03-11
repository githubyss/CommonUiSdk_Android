package com.githubyss.mobile.common.ui.recyclerview.multitype

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * MultiHeaderViewHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 10:00:25
 */
class MultiHeaderViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById<View>(R.id.layout_multiHeaderItem) as FlexboxLayout
    var tvTitle = view.findViewById<View>(R.id.textView_multiHeader) as TextView
}
