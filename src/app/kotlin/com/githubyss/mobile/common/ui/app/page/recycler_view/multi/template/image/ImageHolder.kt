package com.githubyss.mobile.common.ui.app.page.recycler_view.multi.template.image

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * ImageHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 09:17:32
 */
class ImageHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerImageItem) as FlexboxLayout
    var ivImage = view.findViewById(R.id.imageView_recyclerImage) as ImageView
    var tvImageDescription = view.findViewById(R.id.textView_recyclerImageDescription) as TextView
}
