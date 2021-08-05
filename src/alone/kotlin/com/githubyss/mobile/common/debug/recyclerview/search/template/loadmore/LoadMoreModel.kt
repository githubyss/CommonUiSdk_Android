package com.githubyss.mobile.common.debug.recyclerview.search.template.loadmore

import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * LoadMoreModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/26 15:16:13
 */
data class LoadMoreModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var loadState: String = ""
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(loadState: String, @ItemType type: Int) : this(type) {
        this.loadState = loadState
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
