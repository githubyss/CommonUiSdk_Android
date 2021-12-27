package com.githubyss.mobile.common.ui.alone.page.recycler_view.search.bean

import org.json.JSONException
import org.json.JSONObject


/**
 * HotWordBean
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/12 20:57:01
 */
class HotWordBean constructor(val json: JSONObject?) {
    
    /** ****************************** Properties ****************************** */
    
    /**  */
    var title: String = ""
        private set
    
    /**  */
    var orderNumber: String = ""
        private set
    
    
    /** ****************************** Constructors ****************************** */
    
    init {
        setProperties(json)
    }
    
    
    /** ****************************** Functions ****************************** */
    
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
