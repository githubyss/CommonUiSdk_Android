package com.githubyss.mobile.common.ui.recyclerview.template.header

import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.SectionId
import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * HeaderSeeMoreModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/25 10:10:30
 */
data class HeaderSeeMoreModel constructor(@SectionId var id: String, var header: String, @ItemType override var type: Int) : BaseItemModel(type)
