package com.githubyss.mobile.common.ui.recyclerview.multitype

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * MultiEmptyViewHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 10:00:34
 */
class MultiEmptyViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById<View>(R.id.layout_multiEmptyItem) as FlexboxLayout
    var tvEmpty = view.findViewById<View>(R.id.textView_multiEmpty) as TextView
}
