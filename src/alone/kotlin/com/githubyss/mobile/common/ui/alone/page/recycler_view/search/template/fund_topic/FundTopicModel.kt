package com.githubyss.mobile.common.ui.alone.page.recycler_view.search.template.fund_topic

import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * FundTopicModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/22 17:11:19
 */
data class FundTopicModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ****************************** Properties ****************************** */
    
    var title: String = ""
    var riseFallRatio: String = ""
    var hint: String = ""
    var riseFallTimeSpan: String = ""
    var jumpUrl: String = ""
    
    
    /** ****************************** Constructors ****************************** */
    
    constructor(title: String, riseFallRatio: String, hint: String, riseFallTimeSpan: String, jumpUrl: String, @ItemType type: Int) : this(type) {
        this.title = title
        this.riseFallRatio = riseFallRatio
        this.hint = hint
        this.riseFallTimeSpan = riseFallTimeSpan
        this.jumpUrl = jumpUrl
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ****************************** Functions ****************************** */
    
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            title = json.optString("fundTopicName")
            riseFallRatio = json.optString("interestRate")
            hint = json.optString("fundTopicDesc")
            riseFallTimeSpan = json.optString("interestRateDesc")
            jumpUrl = json.optString("linkUrl")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
