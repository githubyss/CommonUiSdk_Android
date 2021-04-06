package com.githubyss.mobile.common.ui.banner

import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * BannerModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 15:03:18
 */
data class BannerModel constructor(var imageUrl: String, var jumpUrl: String, var jumpType: String, @ItemType override var type: Int) : BaseItemModel(type)
