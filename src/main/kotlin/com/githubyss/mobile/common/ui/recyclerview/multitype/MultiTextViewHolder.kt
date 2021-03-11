package com.githubyss.mobile.common.ui.recyclerview.multitype

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * MultiTextViewHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 10:01:27
 */
class MultiTextViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById<View>(R.id.layout_multiTextItem) as FlexboxLayout
    var tvTitle = view.findViewById<View>(R.id.textView_multiTextTitle) as TextView
    var ivSelect = view.findViewById<View>(R.id.imageView_multiTextSelect) as ImageView
}
