package com.githubyss.mobile.common.ui.recyclerview.model

import com.githubyss.mobile.common.ui.recyclerview.multitype.MultiType


/**
 * TextModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 17:41:30
 */
data class TextModel constructor(var text: String, var selectStatus: Boolean, @MultiType var type: Int)
