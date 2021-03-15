package com.githubyss.mobile.common.ui.recyclerview.viewholder

import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * FragmentHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 19:58:36
 */
class FragmentHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById<View>(R.id.layout_recyclerFragmentItem) as FlexboxLayout
    var layoutFragment = view.findViewById<View>(R.id.layout_recyclerFragment) as FrameLayout
}
