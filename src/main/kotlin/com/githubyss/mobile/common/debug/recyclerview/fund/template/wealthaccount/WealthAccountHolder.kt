package com.githubyss.mobile.common.debug.recyclerview.fund.template.wealthaccount

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.githubyss.mobile.common.ui.R
import com.google.android.flexbox.FlexboxLayout


/**
 * WealthAccountHolder
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 18:05:16
 */
class WealthAccountHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    var layoutItem = view.findViewById(R.id.layout_recyclerWealthAccountItem) as FlexboxLayout
    var ivImage = view.findViewById(R.id.imageView_recyclerWealthAccountImage) as ImageView
    var tvTitle = view.findViewById(R.id.textView_recyclerWealthAccountTitle) as TextView
    var tvContent = view.findViewById(R.id.textView_recyclerWealthAccountContent) as TextView
    var tglBtnIsFollowed = view.findViewById(R.id.button_recyclerWealthAccountIsFollowed) as ToggleButton
}
