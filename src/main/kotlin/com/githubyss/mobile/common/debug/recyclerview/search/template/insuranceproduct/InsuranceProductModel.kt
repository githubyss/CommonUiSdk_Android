package com.githubyss.mobile.common.debug.recyclerview.search.template.insuranceproduct

import com.githubyss.mobile.common.ui.recyclerview.template.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * InsuranceProductModel
 * 保险产品数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 16:54:24
 */
data class InsuranceProductModel constructor(var imageUrl: String, var title: String, var hint: String, var price: String, var jumpUrl: String, @ItemType override var type: Int) : BaseItemModel(type)
