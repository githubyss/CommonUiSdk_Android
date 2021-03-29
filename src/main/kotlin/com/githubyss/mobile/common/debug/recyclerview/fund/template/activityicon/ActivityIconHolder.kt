package com.githubyss.mobile.common.debug.recyclerview.fund.template.activityicon

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * ActivityIconHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/26 10:29:43
 */
class ActivityIconHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerActivityIconItem) as FlexboxLayout
    var ivIconImage = view.findViewById(R.id.imageView_recyclerActivityIconImage) as ImageView
    var tvLabel = view.findViewById(R.id.textView_recyclerActivityIconLabel) as TextView
}
