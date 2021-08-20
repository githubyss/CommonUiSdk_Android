package com.githubyss.mobile.common.debug.recyclerview.search.bean

import android.content.Context
import com.githubyss.mobile.common.kit.util.ResourceUtils
import com.githubyss.mobile.common.ui.recyclerview.enumeration.ItemType
import com.githubyss.mobile.common.ui.recyclerview.template.headerhasmore.HeaderHasMoreBean
import org.json.JSONException
import org.json.JSONObject
import java.util.*


/**
 * SearchResultBean
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/01 17:20:08
 */
class SearchResultBean(json: JSONObject?) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = SearchResultBean::class.simpleName ?: "simpleName is null"
        
        fun requestDataByMock(context: Context, searchWord: String): SearchResultBean {
            val jsonStringTabAll = ResourceUtils.getStringFromAssets("json/mock_request_search_result_tab_all.json")
            val jsonStringTabFinancial = ResourceUtils.getStringFromAssets("json/mock_request_search_result_tab_financial.json")
            val jsonStringTabDirectJump = ResourceUtils.getStringFromAssets("json/mock_request_search_result_direct_jump.json")
            val jsonString = jsonStringTabAll
            return SearchResultBean(JSONObject(jsonString))
        }
        
        fun requestDataByNet(context: Context, searchWord: String): SearchResultBean {
            val jsonString = ""
            return SearchResultBean(JSONObject(jsonString))
        }
    }
    
    /** 搜索关键词 */
    var keyWord: String = ""
        private set
    
    /** 模块Tab */
    var moduleTab: String = ""
        private set
    
    /** 查看更多类型
     * 返回 null，隐藏“查看更多”楼层
     * 返回 financial，显示“查看更多”楼层，点击后跳转到理财 Tab
     * */
    var hasMoreType: String = ""
        private set
    
    /** 直达数据 */
    var directJump: DirectJumpBean? = null
        private set
    
    /** 热搜榜 */
    var hotWordsMap: HotWordMapBean? = null
    
    /** 结果列表 */
    var searchResultModuleList: ArrayList<HeaderHasMoreBean> = ArrayList()
        private set
    
    
    /** ********** ********** ********** Constructors ********** ********** ********** */
    
    init {
        setProperties(json)
    }
    
    
    /** ********** ********** ********** Functions ********** ********** ********** */
    
    private fun setProperties(json: JSONObject?) {
        try {
            if (json == null) return
            
            keyWord = json.optString("searchWord")
            moduleTab = json.optString("moduleTab")
            hasMoreType = json.optString("hasMoreType")
            directJump = DirectJumpBean(json.optJSONObject("directResult"))
            hotWordsMap = HotWordMapBean(json.optJSONObject("hotWordsMap"), ItemType.ITEM)
            
            val searchResultModuleListJson = json.optJSONArray("searchResults")
            if (searchResultModuleListJson != null) {
                val searchResultModuleListLength = searchResultModuleListJson.length()
                for (idx in 0 until searchResultModuleListLength) {
                    searchResultModuleList.add(HeaderHasMoreBean(searchResultModuleListJson.getJSONObject(idx), ItemType.HEADER))
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
