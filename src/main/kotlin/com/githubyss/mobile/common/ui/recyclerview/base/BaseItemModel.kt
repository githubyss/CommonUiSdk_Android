package com.githubyss.mobile.common.ui.recyclerview.base

import com.githubyss.mobile.common.ui.recyclerview.enumeration.ItemType
import org.json.JSONObject


/**
 * BaseItemModel
 * 基础的数据结构，包括头、脚、类型
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/24 14:39:42
 */
abstract class BaseItemModel(@ItemType open var type: Int) {
    abstract fun setProperties(json: JSONObject?)
}
