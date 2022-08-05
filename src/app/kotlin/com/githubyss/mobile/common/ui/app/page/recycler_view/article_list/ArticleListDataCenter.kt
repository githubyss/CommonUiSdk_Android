package com.githubyss.mobile.common.ui.app.page.recycler_view.article_list

import com.githubyss.mobile.common.kit.enumeration.CheckState
import com.githubyss.mobile.common.ui.app.page.recycler_view.article_list.ArticleListDataCenter.changeAllCheckState
import com.githubyss.mobile.common.ui.recycler_view.base.binding.BindingAdapterDoubleLayerItem
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
    val items by lazy {
        val items = ArrayList<BindingAdapterItem>()
        items += ItemDatetime(0, "今天")
        items += ItemTitle("20899", "人民网这样说！", false, true)
        items += ItemTitle("20765", "金士顿火了！", true, true)
        items += ItemDatetime(1, "昨天")
        items += ItemTitle("20033", "Google 宣布以下事项！", false, false)
        items += ItemDatetime(2, "一个月前")
        items += ItemTitle("20011", "苏宁进入退市流程！", false, false)
        items += ItemTitle("19766", "互联网寒冬还要持续多久！", false, false)
        items += ItemTitle("19679", "三亚热死人了！", false, false)
        items
    }

    @JvmStatic
    val itemsEditing by lazy {
        val items = ArrayList<BindingAdapterItem>()
        items += ItemDatetimeEditing("今天", CheckState.CHECK_YES)
        items += ItemTitleEditing("20899", "人民网这样说！", "今天", CheckState.CHECK_YES)
        items += ItemTitleEditing("20765", "金士顿火了！", "今天", CheckState.CHECK_YES)
        items += ItemDatetimeEditing("昨天", CheckState.CHECK_NO)
        items += ItemTitleEditing("20033", "Google 宣布以下事项！", "昨天", CheckState.CHECK_NO)
        items += ItemDatetimeEditing("一个月前", CheckState.CHECK_PARTLY)
        items += ItemTitleEditing("20011", "苏宁进入退市流程！", "一个月前", CheckState.CHECK_YES)
        items += ItemTitleEditing("19766", "互联网寒冬还要持续多久！", "一个月前", CheckState.CHECK_YES)
        items += ItemTitleEditing("19679", "三亚热死人了！", "一个月前", CheckState.CHECK_NO)
        items
    }

    @JvmStatic
    val itemsCombine by lazy {
        val items = ArrayList<ItemCombine>()
        var articleList: ArrayList<ItemTitle>

        articleList = ArrayList()
        items += ItemCombine(0, "今天", articleList)
        articleList += ItemTitle("20899", "人民网这样说！", false, true)
        articleList += ItemTitle("20765", "金士顿火了！", true, true)

        articleList = ArrayList()
        items += ItemCombine(1, "昨天", articleList)
        articleList += ItemTitle("20033", "Google 宣布以下事项！", false, false)

        articleList = ArrayList()
        items += ItemCombine(2, "一个月前", articleList)
        articleList += ItemTitle("20011", "苏宁进入退市流程！", false, false)
        articleList += ItemTitle("19766", "互联网寒冬还要持续多久！", false, false)
        articleList += ItemTitle("19679", "三亚热死人了！", false, false)

        items
    }

    @JvmStatic
    val itemsCombineEditing by lazy {
        val items = ArrayList<ItemCombineEditing>()
        var articleList: ArrayList<ItemTitleEditing>

        articleList = ArrayList()
        items += ItemCombineEditing("今天", CheckState.CHECK_YES, articleList)
        articleList += ItemTitleEditing("20899", "人民网这样说！", "今天", CheckState.CHECK_YES)
        articleList += ItemTitleEditing("20765", "金士顿火了！", "今天", CheckState.CHECK_YES)

        articleList = ArrayList()
        items += ItemCombineEditing("昨天", CheckState.CHECK_YES, articleList)
        articleList += ItemTitleEditing("20033", "Google 宣布以下事项！", "昨天", CheckState.CHECK_NO)

        articleList = ArrayList()
        items += ItemCombineEditing("一个月前", CheckState.CHECK_PARTLY, articleList)
        articleList += ItemTitleEditing("20011", "苏宁进入退市流程！", "一个月前", CheckState.CHECK_YES)
        articleList += ItemTitleEditing("19766", "互联网寒冬还要持续多久！", "一个月前", CheckState.CHECK_YES)
        articleList += ItemTitleEditing("19679", "三亚热死人了！", "一个月前", CheckState.CHECK_NO)

        items
    }

    fun selectItemCombine(data: ItemCombineEditing) {
        val checkState = when (data.checkState.get()) {
            CheckState.CHECK_YES -> {
                CheckState.CHECK_NO
            }
            CheckState.CHECK_NO -> {
                CheckState.CHECK_YES
            }
            CheckState.CHECK_PARTLY -> {
                CheckState.CHECK_YES
            }
            else -> return
        }
        data.checkState.set(checkState)
        data.innerItems = data.innerItems.changeAllCheckState(checkState)
    }

    fun selectItemTitle(data: ItemTitleEditing) {
        val checkState = when (data.checkState.get()) {
            CheckState.CHECK_YES -> CheckState.CHECK_NO
            CheckState.CHECK_NO -> CheckState.CHECK_YES
            else -> return
        }
        data.checkState.set(checkState)

        for (itemCombineEditing in itemsCombineEditing) {
            if (itemCombineEditing.datetime == data.datetime) {
                when {
                    itemCombineEditing.innerItems.all { it.checkState.get() == CheckState.CHECK_YES } -> itemCombineEditing.checkState.set(CheckState.CHECK_YES)
                    itemCombineEditing.innerItems.any { it.checkState.get() == CheckState.CHECK_YES } -> itemCombineEditing.checkState.set(CheckState.CHECK_PARTLY)
                    else -> itemCombineEditing.checkState.set(CheckState.CHECK_NO)
                }
            }
        }
    }

    fun selectAllItems(selectAll: Boolean) {
        val checkState = if (selectAll) CheckState.CHECK_YES else CheckState.CHECK_NO
        for (itemCombineEditing in itemsCombineEditing) {
            itemCombineEditing.checkState.set(checkState)
            itemCombineEditing.innerItems = itemCombineEditing.innerItems.changeAllCheckState(checkState)
        }
    }

    fun List<ItemTitleEditing>.changeAllCheckState(checkState: String) = this.map {
        it.checkState.set(checkState)
        it
    }
}
