package com.githubyss.mobile.common.ui.app.page.recycler_view.search.template.see_more

import com.githubyss.mobile.common.ui.recycler_view.base.classical.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * SeeMoreModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/26 15:16:34
 */
data class SeeMoreModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ****************************** Properties ****************************** */
    
    var hasMoreType: String = ""
    
    
    /** ****************************** Constructors ****************************** */
    
    constructor(hasMoreType: String, @ItemType type: Int) : this(type) {
        this.hasMoreType = hasMoreType
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ****************************** Functions ****************************** */
    
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            hasMoreType = json.optString("hasMoreType")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
