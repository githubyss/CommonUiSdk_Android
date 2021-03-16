package com.githubyss.mobile.common.ui.recyclerview.model

import androidx.fragment.app.Fragment
import com.githubyss.mobile.common.ui.recyclerview.type.MultiType


/**
 * FragmentModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/11 19:57:11
 */
data class FragmentModel constructor(var fragment: Fragment?, @MultiType var type: Int)
