package com.githubyss.mobile.common.ui.recyclerview.model

import android.view.View
import com.githubyss.mobile.common.ui.recyclerview.type.MultiType


/**
 * FragmentModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 19:57:11
 */
data class ViewModel constructor(var view: View?, @MultiType var type: Int)
