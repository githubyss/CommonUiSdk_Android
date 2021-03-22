package com.githubyss.mobile.common.ui.recyclerview.multitype

import com.githubyss.mobile.common.ui.recyclerview.type.MultiType


/**
 * MultiTypeModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/10 17:05:53
 */
data class MultiTypeModel constructor(var title: String, var imageUrl: String, var selectStatus: Boolean, @MultiType var type: Int)
