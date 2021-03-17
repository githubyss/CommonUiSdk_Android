package com.githubyss.mobile.common.debug.recyclerview.viewholder

import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R


/**
 * EmptyNoneHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:46:33
 */
class EmptyNoneHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerItemEmptyNone) as FrameLayout
}
