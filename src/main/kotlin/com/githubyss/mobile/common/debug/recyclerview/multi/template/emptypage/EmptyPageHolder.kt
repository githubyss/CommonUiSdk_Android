package com.githubyss.mobile.common.debug.recyclerview.multi.template.emptypage

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * EmptyPageHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 10:00:34
 */
class EmptyPageHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.flexboxItemEmptyPage) as FlexboxLayout
    var tvEmpty = view.findViewById(R.id.textEmptyHint) as TextView
}
