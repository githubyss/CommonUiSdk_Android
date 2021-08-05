package com.githubyss.mobile.common.debug.recyclerview.search.template.wealthaccount

import com.githubyss.mobile.common.debug.recyclerview.search.enumeration.IsFollow
import com.githubyss.mobile.common.ui.recyclerview.base.BaseItemModel
import com.githubyss.mobile.common.ui.recyclerview.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * WealthAccountModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/03/30 18:04:58
 */
data class WealthAccountModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    var imageUrl: String = ""
    var title: String = ""
    var content: String = ""
    var account: String = ""
    var isFollowed: String = ""
    var jumpUrl: String = ""
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    constructor(imageUrl: String, title: String, content: String, account: String, @IsFollow isFollowed: String, jumpUrl: String, @ItemType type: Int) : this(type) {
        this.imageUrl = imageUrl
        this.title = title
        this.content = content
        this.account = account
        this.isFollowed = isFollowed
        this.jumpUrl = jumpUrl
    }
    
    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            imageUrl = json.optString("fortuneLogo")
            title = json.optString("fortuneName")
            content = json.optString("fortuneDesc")
            account = json.optString("createrAccount")
            isFollowed = json.optString("isConcern")
            jumpUrl = json.optString("linkUrl")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
