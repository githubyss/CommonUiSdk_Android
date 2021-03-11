package com.githubyss.mobile.common.ui.recyclerview.multitype

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * MultiFooterViewHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 10:30:07
 */
class MultiFooterViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById<View>(R.id.layout_multiFooterItem) as FlexboxLayout
    var tvTitle = view.findViewById<View>(R.id.textView_multiFooter) as TextView
}
