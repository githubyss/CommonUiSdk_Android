package com.githubyss.mobile.common.debug.recyclerview.search.bean

import org.json.JSONException
import org.json.JSONObject


/**
 * DirectJumpBean
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/09 15:18:46
 */
class DirectJumpBean constructor(val json: JSONObject?) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    /** 跳转链接 */
    var jumpUrl: String = ""
        private set
    
    /** 跳转模式 */
    var jumpMode: String = ""
        private set
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    init {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun setProperties(json: JSONObject?) {
        try {
            json?.let {
                jumpUrl = json.optString("linkUrl")
                jumpMode = json.optString("urlType")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
    
    
    /** ********** ********** ********** Interface ********** ********** ********** */
    
    interface OnDirectJumpListener {
        fun onDirectJump(directJump: DirectJumpBean)
    }
}
