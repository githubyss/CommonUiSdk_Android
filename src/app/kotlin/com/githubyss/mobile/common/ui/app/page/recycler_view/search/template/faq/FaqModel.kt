package com.githubyss.mobile.common.ui.app.page.recycler_view.search.template.faq

import com.githubyss.mobile.common.ui.recycler_view.base.classical.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * FaqModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 17:26:05
 */
data class FaqModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ****************************** Properties ****************************** */
    
    var content: String = ""
    var jumpUrl: String = ""
    
    
    /** ****************************** Constructors ****************************** */
    
    constructor(content: String, jumpUrl: String, @ItemType type: Int) : this(type) {
        this.content = content
        this.jumpUrl = jumpUrl
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ****************************** Functions ****************************** */
    
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
