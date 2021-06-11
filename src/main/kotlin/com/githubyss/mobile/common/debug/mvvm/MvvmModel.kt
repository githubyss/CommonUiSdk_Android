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
    
    class MvvmBean {
        
        /** ********** ********** ********** Properties ********** ********** ********** */
        
        companion object {
            val TAG = MvvmBean::class.java.simpleName
        }
        
        var text: String = ""
        var imageUrl: String = ""
        
        
        /** ********** ********** ********** Constructors ********** ********** ********** */
        
        constructor(text: String, imageUrl: String) {
            this.text = text
            this.imageUrl = imageUrl
        }
        
        constructor(json: JSONObject?) {
            setProperties(json)
        }
        
        
        /** ********** ********** ********** Functions ********** ********** ********** */
        
        private fun setProperties(json: JSONObject?) {
            try {
                json ?: return
                
                text = json.optString("text")
                imageUrl = json.optString("imageUrl")
            } catch (e: JSONException) {
                LogcatUtils.e(TAG, e)
            }
        }
    }
}
