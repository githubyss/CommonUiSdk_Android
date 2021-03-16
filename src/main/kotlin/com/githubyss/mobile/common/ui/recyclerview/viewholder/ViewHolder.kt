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
class ViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.frameLayout_container) as FrameLayout
}
