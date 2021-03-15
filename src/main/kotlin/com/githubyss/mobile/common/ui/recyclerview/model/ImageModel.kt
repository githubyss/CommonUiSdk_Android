package com.githubyss.mobile.common.ui.recyclerview.model

import com.githubyss.mobile.common.ui.recyclerview.multitype.MultiType


/**
 * ImageModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 17:47:55
 */
data class ImageModel constructor(var description: String, var imageUrl: String, @MultiType var type: Int)
