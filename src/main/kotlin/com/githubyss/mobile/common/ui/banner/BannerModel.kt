package com.githubyss.mobile.common.ui.banner

import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * BannerModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 15:03:18
 */
data class BannerModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var imageUrl: String = ""
    var jumpUrl: String = ""
    var jumpType: String = ""
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(imageUrl: String, jumpUrl: String, jumpType: String, @ItemType type: Int) : this(type) {
        this.imageUrl = imageUrl
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
            
            imageUrl = json.optString("imgUrl")
            jumpUrl = json.optString("linkUrl")
            jumpType = json.optString("urlType")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
