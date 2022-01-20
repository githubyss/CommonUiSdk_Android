package com.githubyss.mobile.common.ui.app.page.recycler_view.search.template.fund_product

import com.githubyss.mobile.common.ui.app.page.recycler_view.search.enumeration.IsFollow
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * FundProductModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/17 16:17:32
 */
data class FundProductModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ****************************** Properties ****************************** */
    
    var title: String = ""
    var riseFallRatio: String = ""
    var code: String = ""
    var risk: String = ""
    var classify: String = ""
    var riseFallTimeSpan: String = ""
    var followCount: String = ""
    var isFollowed: String = ""
    var jumpUrl: String = ""
    
    
    /** ****************************** Constructors ****************************** */
    
    constructor(title: String, riseFallRatio: String, code: String, risk: String, classify: String, riseFallTimeSpan: String, followCount: String, @IsFollow isFollowed: String, jumpUrl: String, @ItemType type: Int) : this(type) {
        this.title = title
        this.riseFallRatio = riseFallRatio
        this.code = code
        this.risk = risk
        this.classify = classify
        this.riseFallTimeSpan = riseFallTimeSpan
        this.followCount = followCount
        this.isFollowed = isFollowed
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
            riseFallRatio = json.optString("interestRate")
            code = json.optString("fundCode")
            risk = json.optString("riskLevel")
            classify = json.optString("fundType")
            riseFallTimeSpan = json.optString("interestRateDesc")
            followCount = json.optString("selfSelecDesc")
            isFollowed = json.optString("isSelfSelec")
            jumpUrl = json.optString("linkUrl")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
