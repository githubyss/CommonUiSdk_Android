package com.githubyss.mobile.common.debug.mvvm

import com.githubyss.mobile.common.kit.util.LogcatUtils
import org.json.JSONException
import org.json.JSONObject


/**
 * MvvmModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/10 11:40:36
 */
interface MvvmModel {


    /** ********** ********** ********** Class ********** ********** ********** */

    /** 文本 */
    class TextBean {

        /** ********** ********** ********** Properties ********** ********** ********** */

        companion object {
            val TAG = TextBean::class.java.simpleName
        }

        var text: String = ""


        /** ********** ********** ********** Constructors ********** ********** ********** */

        constructor()

        constructor(text: String) {
            this.text = text
        }

        constructor(json: JSONObject?) {
            setProperties(json)
        }


        /** ********** ********** ********** Functions ********** ********** ********** */

        private fun setProperties(json: JSONObject?) {
            try {
                json ?: return

                text = json.optString("text")
            } catch (e: JSONException) {
                LogcatUtils.e(TAG, e)
            }
        }
    }

    /** 图片 */
    class ImageBean {

        /** ********** ********** ********** Properties ********** ********** ********** */

        companion object {
            val TAG = TextBean::class.java.simpleName
        }

        var imageUrl: String = ""


        /** ********** ********** ********** Constructors ********** ********** ********** */

        constructor()

        constructor(imageUrl: String) {
            this.imageUrl = imageUrl
        }

        constructor(json: JSONObject?) {
            setProperties(json)
        }


        /** ********** ********** ********** Functions ********** ********** ********** */

        private fun setProperties(json: JSONObject?) {
            try {
                json ?: return

                imageUrl = json.optString("imageUrl")
            } catch (e: JSONException) {
                LogcatUtils.e(TAG, e)
            }
        }
    }
}
