package com.githubyss.mobile.common.debug.recyclerview.search.template.seemore

import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType
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
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var hasMoreType: String = ""
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(hasMoreType: String, @ItemType type: Int) : this(type) {
        this.hasMoreType = hasMoreType
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    @Throws(JSONException::class)
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            hasMoreType = json.optString("hasMoreType")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
