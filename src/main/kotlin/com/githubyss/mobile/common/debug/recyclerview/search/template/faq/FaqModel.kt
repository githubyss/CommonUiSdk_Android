package com.githubyss.mobile.common.debug.recyclerview.search.template.faq

import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType
import org.json.JSONException
import org.json.JSONObject
import kotlin.jvm.Throws


/**
 * FaqModel
 * 常见问题数据结构
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 17:26:05
 */
data class FaqModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var content: String = ""
    var jumpUrl: String = ""
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(content: String, jumpUrl: String, @ItemType type: Int) : this(type) {
        this.content = content
        this.jumpUrl = jumpUrl
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    @Throws(JSONException::class)
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            content = json.optString("title")
            jumpUrl = json.optString("linkUrl")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
