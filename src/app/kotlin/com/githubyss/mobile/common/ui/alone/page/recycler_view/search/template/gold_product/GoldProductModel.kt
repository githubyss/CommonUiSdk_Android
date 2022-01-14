package com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.gold_product

import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * GoldProductModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/24 15:06:26
 */
data class GoldProductModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ****************************** Properties ****************************** */
    
    var title: String = ""
    var price: String = ""
    var unit: String = ""
    var code: String = ""
    var risk: String = ""
    var classify: String = ""
    var priceTime: String = ""
    var jumpUrl: String = ""
    
    
    /** ****************************** Constructors ****************************** */
    
    constructor(title: String, price: String, unit: String, code: String, risk: String, classify: String, priceTime: String, jumpUrl: String, @ItemType type: Int) : this(type) {
        this.title = title
        this.price = price
        this.unit = unit
        this.code = code
        this.risk = risk
        this.classify = classify
        this.priceTime = priceTime
        this.jumpUrl = jumpUrl
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ****************************** Functions ****************************** */
    
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            title = json.optString("productName")
            price = json.optString("price")
            unit = "元/克"
            code = json.optString("fundCode")
            risk = json.optString("riskLevel")
            classify = json.optString("fundType")
            priceTime = json.optString("priceDesc")
            jumpUrl = json.optString("linkUrl")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
