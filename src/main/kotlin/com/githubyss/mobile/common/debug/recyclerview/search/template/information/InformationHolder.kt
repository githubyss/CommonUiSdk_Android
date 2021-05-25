package com.githubyss.mobile.common.debug.recyclerview.search.template.information

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.textview.KeyWordHighlightTextView
import com.google.android.flexbox.FlexboxLayout


/**
 * InformationHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 17:43:28
 */
class InformationHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerInformationItem) as FlexboxLayout
    var ivImage = view.findViewById(R.id.imageView_recyclerInformationImage) as ImageView
    var tvTitle = view.findViewById(R.id.textView_recyclerInformationTitle) as KeyWordHighlightTextView
    var tvContent = view.findViewById(R.id.textView_recyclerInformationContent) as KeyWordHighlightTextView
    var tvTime = view.findViewById(R.id.textView_recyclerInformationTime) as TextView
    var tvAuthor = view.findViewById(R.id.textView_recyclerInformationAuthor) as TextView
}
