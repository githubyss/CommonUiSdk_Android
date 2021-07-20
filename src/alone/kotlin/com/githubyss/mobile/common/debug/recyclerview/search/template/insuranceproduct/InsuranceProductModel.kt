package com.githubyss.mobile.common.debug.recyclerview.search.template.insuranceproduct

import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.type.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * InsuranceProductModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 16:54:24
 */
data class InsuranceProductModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var imageUrl: String = ""
    var title: String = ""
    var hint: String = ""
    var price: String = ""
    var jumpUrl: String = ""
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(imageUrl: String, title: String, hint: String, price: String, jumpUrl: String, @ItemType type: Int) : this(type) {
        this.imageUrl = imageUrl
        this.title = title
        this.hint = hint
        this.price = price
        this.jumpUrl = jumpUrl
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            imageUrl = json.optString("imgUrl")
            title = json.optString("title")
            hint = json.optString("subTitle")
            price = json.optString("showPrice")
            jumpUrl = json.optString("linkUrl")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
