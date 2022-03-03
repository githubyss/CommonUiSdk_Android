package com.githubyss.mobile.common.ui.app.page.recycler_view.search.bean

import com.githubyss.mobile.common.ui.recycler_view.base.classical.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject
import java.util.*


/**
 * HotWordMapBean
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/11 09:32:11
 */
data class HotWordMapBean constructor(val json: JSONObject?, @ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ****************************** Properties ****************************** */
    
    /**  */
    var moduleKey: String = ""
        private set
    
    /**  */
    var title: String = ""
        private set
    
    /**  */
    var itemList = ArrayList<HotWordBean>()
        private set
    
    
    /** ****************************** Constructors ****************************** */
    
    init {
        setProperties(json)
    }
    
    
    /** ****************************** Functions ****************************** */
    
    override fun setProperties(json: JSONObject?) {
        try {
            json?.let {
                moduleKey = json.optString("key")
                title = json.optString("title")
                
                val itemListJson = json.optJSONArray("items")
                if (itemListJson != null) {
                    val itemListLength = itemListJson.length()
                    if (itemListLength > 0) {
                        for (i in 0 until itemListLength) {
                            val itemJson = itemListJson.getJSONObject(i)
                            itemList.add(HotWordBean(itemJson))
                        }
                    }
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
