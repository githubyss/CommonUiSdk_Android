package com.githubyss.mobile.common.ui.recyclerview.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * FooterHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 10:30:07
 */
class FooterHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerFooterItem) as FlexboxLayout
    var tvTitle = view.findViewById(R.id.textView_recyclerFooter) as TextView
}
