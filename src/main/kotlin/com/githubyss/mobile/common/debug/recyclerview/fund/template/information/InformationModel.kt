package com.githubyss.mobile.common.debug.recyclerview.fund.template.information

import com.githubyss.mobile.common.ui.recyclerview.template.itemlist.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * InformationModel
 * 资讯数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 17:42:34
 */
data class InformationModel constructor(var imageUrl: String, var title: String, var content: String, var time: String, var author: String, var jumpUrl: String, @ItemType override var type: Int) : BaseItemModel(type)
