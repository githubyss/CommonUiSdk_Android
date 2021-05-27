package com.githubyss.mobile.common.ui.recyclerview.template.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R


/**
 * ListHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/29 18:34:56
 */
class ListHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var listItem = view.findViewById(R.id.recyclerContainer) as RecyclerView
}
