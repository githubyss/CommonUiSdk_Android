package com.githubyss.mobile.common.debug.recyclerview.search.template.appicon

import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * AppIconModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/26 10:17:52
 */
data class AppIconModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var iconUrl: String = ""
    var label: String = ""
    var jumpUrl: String = ""
    var jumpType: String = ""
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(iconUrl: String, label: String, jumpUrl: String, jumpType: String, @ItemType type: Int) : this(type) {
        this.iconUrl = iconUrl
        this.label = label
        this.jumpUrl = jumpUrl
        this.jumpType = jumpType
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            iconUrl = json.optString("imgUrl")
            label = json.optString("title")
            jumpUrl = json.optString("linkUrl")
            jumpType = json.optString("urlType")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
