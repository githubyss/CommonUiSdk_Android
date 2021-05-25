package com.githubyss.mobile.common.debug.recyclerview.multi.template.multi

import com.githubyss.mobile.common.ui.recyclerview.type.MultiType


/**
 * MultiModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/10 17:05:53
 */
data class MultiModel constructor(var title: String, var imageUrl: String, var selectStatus: Boolean, @MultiType var type: Int)
