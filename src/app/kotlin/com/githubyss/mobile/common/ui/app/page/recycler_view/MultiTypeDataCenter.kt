package com.githubyss.mobile.common.ui.app.page.recycler_view

import com.githubyss.mobile.common.ui.R
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterItem


object MultiTypeDataCenter {
    @JvmStatic
    val items = ArrayList<BindingAdapterItem>()

    init {
        initData()
    }

    private fun initData() {
        items.add(ItemText("叶子"))
        items.add(ItemImage(R.drawable.comui_head_yezi))
        items.add(ItemText("虫虫"))
        items.add(ItemImage(R.drawable.comui_head_chongchong))
        items.add(ItemText("晓媛"))
        items.add(ItemImage(R.drawable.comui_head_xiaoyuan))
        items.add(ItemText("一菲"))
        items.add(ItemImage(R.drawable.comui_head_yifei))
        items.add(ItemText("子晴"))
        items.add(ItemImage(R.drawable.comui_head_ziqing))
    }
}
