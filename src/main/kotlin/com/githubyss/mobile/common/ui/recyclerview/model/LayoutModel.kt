package com.githubyss.mobile.common.ui.recyclerview.model

import android.view.View
import com.githubyss.mobile.common.ui.recyclerview.type.MultiType


/**
 * LayoutModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/16 15:15:17
 */
data class LayoutModel constructor(var view: View?, @MultiType var type: Int)
