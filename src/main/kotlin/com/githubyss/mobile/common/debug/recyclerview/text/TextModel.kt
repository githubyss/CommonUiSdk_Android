package com.githubyss.mobile.common.debug.recyclerview.text

import com.githubyss.mobile.common.debug.recyclerview.type.MultiType


/**
 * TextModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 17:41:30
 */
data class TextModel constructor(var text: String, var selectStatus: Boolean, @MultiType var type: Int)
