package com.githubyss.mobile.common.debug.recyclerview.search.bean

import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject
import java.util.*


/**
 * HotWordMapModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/11 09:32:11
 */
data class HotWordMapModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var moduleKey: String = ""
        private set
    
    var title: String = ""
        private set
    
    var itemList = ArrayList<HotWordModel>()
        private set
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(moduleKey: String, title: String, itemList: ArrayList<HotWordModel>, @ItemType type: Int) : this(type) {
        this.moduleKey = moduleKey
        this.title = title
        this.itemList = itemList
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            moduleKey = json.optString("key")
            title = json.optString("title")
            
            val itemListJson = json.optJSONArray("items")
            if (itemListJson != null) {
                val itemListLength = itemListJson.length()
                if (itemListLength > 0) {
                    for (i in 0 until itemListLength) {
                        val itemJson = itemListJson.getJSONObject(i)
                        itemList.add(HotWordModel(itemJson))
                    }
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
