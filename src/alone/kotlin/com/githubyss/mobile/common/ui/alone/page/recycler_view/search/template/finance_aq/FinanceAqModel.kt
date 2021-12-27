package com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.finance_aq

import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * FinanceAqModel
 * 财顾问答数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 11:55:16
 */
data class FinanceAqModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var title: String = ""
    var content: String = ""
    var jumpUrl: String = ""
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(title: String, content: String, jumpUrl: String, @ItemType type: Int) : this(type) {
        this.title = content
        this.content = content
        this.jumpUrl = jumpUrl
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            title = json.optString("title")
            content = json.optString("content")
            jumpUrl = json.optString("linkUrl")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
