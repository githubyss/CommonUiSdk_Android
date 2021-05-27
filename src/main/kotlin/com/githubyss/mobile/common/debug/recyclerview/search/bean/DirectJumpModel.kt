package com.githubyss.mobile.common.debug.recyclerview.search.bean

import org.json.JSONException
import org.json.JSONObject


/**
 * DirectJumpModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/09 15:18:46
 */
class DirectJumpModel constructor() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var title: String = ""
    var jumpUrl: String = ""
    var jumpType: String = ""
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(title: String, jumpUrl: String, jumpType: String) : this() {
        this.title = title
        this.jumpUrl = jumpUrl
        this.jumpType = jumpType
    }
    
    constructor(json: JSONObject?) : this() {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            title = json.optString("title")
            jumpUrl = json.optString("linkUrl")
            jumpType = json.optString("urlType")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface OnDirectJumpListener {
        fun onDirectJump(directJump: DirectJumpModel)
    }
}
