package com.githubyss.mobile.common.debug.recyclerview.search.template.information

import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * InformationModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 17:42:34
 */
data class InformationModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var imageUrl: String = ""
    var title: String = ""
    var content: String = ""
    var time: String = ""
    var author: String = ""
    var jumpUrl: String = ""
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(imageUrl: String, title: String, content: String, time: String, author: String, jumpUrl: String, @ItemType type: Int) : this(type) {
        this.imageUrl = imageUrl
        this.title = title
        this.content = content
        this.time = time
        this.author = author
        this.jumpUrl = jumpUrl
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            imageUrl = json.optString("imgUrl")
            title = json.optString("title")
            content = json.optString("content")
            time = json.optString("updateTime")
            author = json.optString("source")
            jumpUrl = json.optString("linkUrl")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
