package com.githubyss.mobile.common.ui.app.page.recycler_view.search.template.special_topic

import com.githubyss.mobile.common.ui.app.page.recycler_view.search.template.app_icon.AppIconModel
import com.githubyss.mobile.common.ui.banner.BannerModel
import com.githubyss.mobile.common.ui.recycler_view.base.BaseItemModel
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * SpecialTopicModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 16:10:02
 */
data class SpecialTopicModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ****************************** Properties ****************************** */
    
    var bgImageUrl: String = ""
    var bgTitle: String = ""
    var bgDescription: String = ""
    var bgJumpUrl: String = ""
    var topicIconUrl: String = ""
    var topicTitle: String = ""
    var topicDescription: String = ""
    var topicJumpUrl: String = ""
    var advertList: ArrayList<BannerModel> = ArrayList()
    var iconList: ArrayList<AppIconModel> = ArrayList()
    
    
    /** ****************************** Constructors ****************************** */
    
    constructor(bgImageUrl: String, bgTitle: String, bgDescription: String, bgJumpUrl: String, topicIconUrl: String, topicTitle: String, topicDescription: String, topicJumpUrl: String, advertList: ArrayList<BannerModel>, iconList: ArrayList<AppIconModel>, @ItemType type: Int) : this(type) {
        this.bgImageUrl = bgImageUrl
        this.bgTitle = bgTitle
        this.bgDescription = bgDescription
        this.bgJumpUrl = bgJumpUrl
        this.topicIconUrl = topicIconUrl
        this.topicTitle = topicTitle
        this.topicDescription = topicDescription
        this.topicJumpUrl = topicJumpUrl
        this.advertList = advertList
        this.iconList = iconList
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ****************************** Functions ****************************** */
    
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            bgImageUrl = json.optString("bgImgUrl")
            bgTitle = json.optString("bgtitle")
            bgDescription = json.optString("bgSubTitle")
            bgJumpUrl = json.optString("bgLinkUrl")
            topicIconUrl = json.optString("subjectImgUrl")
            topicTitle = json.optString("subjectName")
            topicDescription = json.optString("subTitle")
            topicJumpUrl = json.optString("subjectTitleLinkUrl")
            
            val advertsJson = json.optJSONArray("advertList")
            if (advertsJson != null && advertsJson.length() > 0) {
                val length = advertsJson.length()
                for (i in 0 until length) {
                    val advertJson = advertsJson.getJSONObject(i)
                    advertList.add(BannerModel(advertJson, ItemType.ITEM))
                }
            }
            
            val iconsJson = json.optJSONArray("iconList")
            if (iconsJson != null && iconsJson.length() > 0) {
                val length = iconsJson.length()
                for (i in 0 until length) {
                    val iconJson = iconsJson.getJSONObject(i)
                    iconList.add(AppIconModel(iconJson, ItemType.ITEM))
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
