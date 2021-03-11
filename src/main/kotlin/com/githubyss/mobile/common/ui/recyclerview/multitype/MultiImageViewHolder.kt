package com.githubyss.mobile.common.ui.recyclerview.multitype

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * MultiImageViewHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 09:17:32
 */
class MultiImageViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById<View>(R.id.layout_multiImageItem) as FlexboxLayout
    var ivImage = view.findViewById<View>(R.id.imageView_multiImageDisplay) as ImageView
    var tvImageTitle = view.findViewById<View>(R.id.textView_multiImageTitle) as TextView
}
