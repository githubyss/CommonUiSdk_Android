package com.githubyss.mobile.common.ui.app.page.recycler_view.article_list

import com.githubyss.common.base.recycler_view.binding.BindingAdapterItem
import com.githubyss.mobile.common.kit.enumeration.CheckState
import com.githubyss.mobile.common.kit.util.*


/**
 * ArticleListDataCenter
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2022/08/03 16:10:14
 */
object ArticleListDataCenter {
    @JvmStatic
    val items by lazy { ArrayList<BindingAdapterItem>() }

    @JvmStatic
    val itemsEditing by lazy { ArrayList<BindingAdapterItem>() }

    @JvmStatic
    val itemsCombine by lazy { ArrayList<ItemCombine>() }

    @JvmStatic
    val itemsCombineEditing by lazy { ArrayList<ItemCombineEditing>() }

    @JvmStatic
    val itemsCombineAll by lazy { ArrayList<ItemCombineAll>() }

    fun updateData() {
        val jsonObject = getJSONObjectFromAssets("json/netres/article/mock_request_articles.json")
        val articlesJson = jsonObject.jsonObject("data").jsonArray("articles") ?: return

        items.clear()
        itemsEditing.clear()
        itemsCombine.clear()
        itemsCombineEditing.clear()

        var isFirst = true
        var dateGet = ""

        var titleList = ArrayList<ItemTitle>()
        var titleEditingList = ArrayList<ItemTitleEditing>()
        var titleAllList = ArrayList<ItemTitleAll>()
        for (article in articlesJson) {
            val id = article.string("id") ?: ""
            val title = article.string("title") ?: ""
            val createDate = article.string("createDate") ?: ""

            val datetimeString = when {
                createDate.equalTodayForUtc -> "今天"
                createDate.equalYesterdayForUtc -> "昨天"
                createDate.beforeMonthForUtc -> "一个月前"
                else -> createDate.datetimeStringForUtc("M月d日")
            }

            when (dateGet) {
                datetimeString -> {
                    items += ItemTitle(id, title, false, false)
                    itemsEditing += ItemTitleEditing(id, title, datetimeString, CheckState.CHECK_NO)
                }
                else -> {
                    items += ItemDatetime(datetimeString, isFirst)
                    itemsEditing += ItemDatetimeEditing(datetimeString, CheckState.CHECK_NO)
                }
            }

            when (dateGet) {
                datetimeString -> {}
                else -> {
                    titleList = ArrayList()
                    itemsCombine += ItemCombine(datetimeString, isFirst, titleList)

                    titleEditingList = ArrayList()
                    itemsCombineEditing += ItemCombineEditing(datetimeString, CheckState.CHECK_NO, titleEditingList)
                    dateGet = datetimeString

                    titleAllList = ArrayList()
                    itemsCombineAll += ItemCombineAll(datetimeString, isFirst, titleAllList, false, CheckState.CHECK_NO)
                }
            }

            titleList += ItemTitle(id, title, false, false)
            titleEditingList += ItemTitleEditing(id, title, datetimeString, CheckState.CHECK_NO)
            titleAllList += ItemTitleAll(id, title, false, false, datetimeString, false, CheckState.CHECK_NO)
            isFirst = false
        }
    }

    fun selectAllItems(selectAll: Boolean) {
        val checkState = if (selectAll) CheckState.CHECK_YES else CheckState.CHECK_NO
        for (itemCombineEditing in itemsCombineEditing) {
            itemCombineEditing.checkState.set(checkState)
            itemCombineEditing.innerItems = itemCombineEditing.innerItems.changeAllCheckState(checkState)
        }
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

    fun deleteItemTitle(ids: Set<String>) {
        itemsCombine.forEach {
            val iterator = it.innerItems.iterator()
            while (iterator.hasNext()) {
                if (ids.contains(iterator.next().id)) {
                    iterator.remove()
                }
            }
        }
        itemsCombineEditing.forEach {
            val iterator = it.innerItems.iterator()
            while (iterator.hasNext()) {
                if (ids.contains(iterator.next().id)) {
                    iterator.remove()
                }
            }
        }
    }

    fun List<ItemTitleEditing>.changeAllCheckState(checkState: String) = this.map {
        it.checkState.set(checkState)
        it
    } as ArrayList

    fun List<ItemTitle>.changeAllEditState(isEditing: Boolean) = this.map {
        // it.isEditing.set(isEditing)
        // it
    }
}
