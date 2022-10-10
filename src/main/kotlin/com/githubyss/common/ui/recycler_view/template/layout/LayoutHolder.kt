package com.githubyss.common.ui.recycler_view.template.layout

import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.common.ui.R


/**
 * LayoutHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/16 15:15:25
 */
class LayoutHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.frame_container) as FrameLayout
}
