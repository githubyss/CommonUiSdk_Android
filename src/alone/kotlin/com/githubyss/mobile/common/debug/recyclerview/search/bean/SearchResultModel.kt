package com.githubyss.mobile.common.debug.recyclerview.search.bean

import com.githubyss.mobile.common.kit.util.ResourceUtils
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject
import java.util.*


/**
 * SearchResultModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/01 17:20:08
 */
class SearchResultModel(json: JSONObject?) {
    
    /** ********** ********** ********** Properties ********** ********** ********** */
    
    companion object {
        val TAG = SearchResultModel::class.java.simpleName
        
        fun requestDataByLocalJson(searchWord: String): SearchResultModel {
            val jsonStringTabAll = ResourceUtils.getStringFromAssets("json/mock_request_search_result_tab_all_jijin.json")
            val jsonStringTabFinancial = ResourceUtils.getStringFromAssets("json/mock_request_search_result_more_jijin.json")
            val jsonStringTabDirectJump = ResourceUtils.getStringFromAssets("json/mock_request_search_result_tab_all_nanjingditie.json")
            val jsonString = jsonStringTabAll
            return SearchResultModel(JSONObject(jsonString))
        }
        
        fun requestDataByNet(searchWord: String): SearchResultModel {
            val jsonString = ""
            return SearchResultModel(JSONObject(jsonString))
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
            json?.let {
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
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}
