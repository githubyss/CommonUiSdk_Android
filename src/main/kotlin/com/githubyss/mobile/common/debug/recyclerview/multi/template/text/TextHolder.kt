package com.githubyss.mobile.common.debug.recyclerview.multi.template.text

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * TextHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 10:01:27
 */
class TextHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerTextItem) as FlexboxLayout
    var tvText = view.findViewById(R.id.textView_recyclerText) as TextView
    var ivSelect = view.findViewById(R.id.imageView_recyclerTextSelect) as ImageView
}
