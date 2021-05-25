package com.githubyss.mobile.common.debug.recyclerview.search.template.appicon

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.textview.KeyWordHighlightTextView
import com.google.android.flexbox.FlexboxLayout


/**
 * AppIconHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/26 10:17:56
 */
class AppIconHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerAppIconItem) as FlexboxLayout
    var ivIconImage = view.findViewById(R.id.imageView_recyclerAppIconImage) as ImageView
    var tvLabel = view.findViewById(R.id.textView_recyclerAppIconLabel) as KeyWordHighlightTextView
}
