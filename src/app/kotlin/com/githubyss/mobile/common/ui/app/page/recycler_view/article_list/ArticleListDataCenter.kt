package com.githubyss.mobile.common.ui.app.page.recycler_view.article_list

import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterItem


/**
 * ArticleListDataCenter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/03 16:10:14
 */
object ArticleListDataCenter {
    @JvmStatic
    val items = ArrayList<BindingAdapterItem>()

    init {
        initData()
    }

    private fun initData() {
        items.add(ItemDatetime(0, "今天"))
        items.add(ItemArticleTitle("20899", "人民网这样说！", false, true))
        items.add(ItemArticleTitle("20765", "金士顿火了！", true, true))
        items.add(ItemDatetime(1, "昨天"))
        items.add(ItemArticleTitle("20033", "Google 宣布以下事项！", false, false))
        items.add(ItemDatetime(2, "一个月前"))
        items.add(ItemArticleTitle("20011", "苏宁进入退市流程！", false, false))
        items.add(ItemArticleTitle("19766", "互联网寒冬还要持续多久！", false, false))
        items.add(ItemArticleTitle("19679", "三亚热死人了！", false, false))
    }
}
