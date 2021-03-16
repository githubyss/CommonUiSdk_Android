package com.githubyss.mobile.common.debug.recyclerview.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * EmptyHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 10:00:34
 */
class EmptyHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerEmptyItem) as FlexboxLayout
    var tvEmpty = view.findViewById(R.id.textView_recyclerEmpty) as TextView
}
