package com.githubyss.mobile.common.debug.recyclerview.search.bean

import org.json.JSONException
import org.json.JSONObject


/**
 * HotWordModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/12 20:57:01
 */
class HotWordModel constructor() {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var title: String = ""
    var orderNumber: String = ""
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(title: String, orderNumber: String) : this() {
        this.title = title
        this.orderNumber = orderNumber
    }
    
    constructor(json: JSONObject?) : this() {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            title = json.optString("title")
            orderNumber = json.optString("orderNumber")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
