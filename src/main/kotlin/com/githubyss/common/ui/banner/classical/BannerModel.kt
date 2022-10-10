package com.githubyss.common.ui.banner.classical

import com.githubyss.common.kit.util.logE
import com.githubyss.common.ui.recycler_view.base.classical.BaseItemModel
import com.githubyss.common.ui.recycler_view.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * BannerModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/04/06 15:03:18
 */
data class BannerModel constructor(@ItemType override var type: Int) : BaseItemModel(type) {

    companion object {
        private val TAG: String = BannerModel::class.java.simpleName
    }


    /** ****************************** Properties ****************************** */

    var imageUrl: String = ""
    var jumpUrl: String = ""
    var jumpType: String = ""


    /** ****************************** Constructors ****************************** */

    constructor(imageUrl: String, jumpUrl: String, jumpType: String, @ItemType type: Int) : this(type) {
        this.imageUrl = imageUrl
        this.jumpUrl = jumpUrl
        this.jumpType = jumpType
    }

    constructor(json: JSONObject?, @ItemType type: Int) : this(type) {
        setProperties(json)
    }


    /** ****************************** Functions ****************************** */

    override fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return

            imageUrl = json.optString("imgUrl")
            jumpUrl = json.optString("linkUrl")
            jumpType = json.optString("urlType")
        }
        catch (e: JSONException) {
            logE(TAG, t = e)
        }
    }
}
