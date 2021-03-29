package com.githubyss.mobile.common.ui.recyclerview.template.itemlist

import com.githubyss.mobile.common.ui.recyclerview.type.ItemType


/**
 * BaseItemModel
 * 基础的数据结构，包括头、脚、类型
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/24 14:39:42
 */
open class BaseItemModel(@ItemType open var type: Int)
