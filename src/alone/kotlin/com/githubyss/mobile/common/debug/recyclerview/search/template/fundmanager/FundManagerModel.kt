package com.githubyss.mobile.common.debug.recyclerview.search.template.fundmanager

import com.githubyss.mobile.common.debug.recyclerview.search.template.fundproduct.FundProductModel
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * FundManagerModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/23 11:32:39
 */
data class FundManagerModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var imageUrl: String = ""
    var title: String = ""
    var bestReturn: String = ""
    var riseFallRatio: String = ""
    var description: String = ""
    var jumpUrl: String = ""
    var fundProductList = ArrayList<BaseItemModel>()
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(imageUrl: String, title: String, bestReturn: String, riseFallRatio: String, description: String, jumpUrl: String, fundProductList: ArrayList<BaseItemModel>, @ItemType type: Int) : this(type) {
        this.imageUrl = imageUrl
        this.title = title
        this.bestReturn = bestReturn
        this.riseFallRatio = riseFallRatio
        this.description = description
        this.jumpUrl = jumpUrl
        this.fundProductList = fundProductList
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            imageUrl = json.optString("managerUrl")
            title = json.optString("managerName")
            bestReturn = "任期最佳回报"
            riseFallRatio = json.optString("performance")
            description = json.optString("background")
            jumpUrl = json.optString("linkUrl")
            
            val fundProductsJson = json.optJSONArray("fundList")
            if (fundProductsJson != null && fundProductsJson.length() > 0) {
                val length = fundProductsJson.length()
                for (i in 0 until length) {
                    val item = fundProductsJson.getJSONObject(i)
                    fundProductList.add(FundProductModel(item, ItemType.ITEM))
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
