package com.githubyss.mobile.common.debug.recyclerview.image

import com.githubyss.mobile.common.ui.recyclerview.type.MultiType


/**
 * ImageModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 17:47:55
 */
data class ImageModel constructor(var description: String, var imageUrl: String, @MultiType var type: Int)
