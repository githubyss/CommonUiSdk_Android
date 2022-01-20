package com.githubyss.mobile.common.ui.app.page.recycler_view.search.bean

import com.githubyss.mobile.common.kit.mock.OnResponse
import com.githubyss.mobile.common.kit.util.LogUtils
import com.githubyss.mobile.common.kit.util.ResourceUtils
import com.githubyss.mobile.common.ui.BuildConfig
import com.githubyss.mobile.common.ui.recycler_view.enumeration.ItemType
import org.json.JSONException
import org.json.JSONObject


/**
 * SearchResultModel
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2021/06/01 17:20:08
 */
class SearchResultModel(val json: JSONObject?) {
    
    /** ****************************** Companion ****************************** */
    
    companion object {
        val TAG: String = SearchResultModel::class.java.simpleName
        
        fun request(searchWord: String, onResponse: OnResponse<SearchResultModel>?) {
            when (BuildConfig.MOCK_LOCAL) {
                true -> requestDataByLocalJson(searchWord, onResponse)
                false -> requestDataByNet(searchWord, onResponse)
            }
        }
        
        private fun requestDataByLocalJson(searchWord: String, onResponse: OnResponse<SearchResultModel>?) {
            val jsonStringTabAll = ResourceUtils.getStringFromAssets("json/netres/search/mock_request_search_result_tab_all_jijin.json")
            val jsonStringTabFinancial = ResourceUtils.getStringFromAssets("json/netres/search/mock_request_search_result_more_jijin.json")
            val jsonStringTabDirectJump = ResourceUtils.getStringFromAssets("json/netres/search/mock_request_search_result_tab_all_nanjingditie.json")
            onResponse?.onSuccess(SearchResultModel(JSONObject(jsonStringTabAll)))
        }
        
        private fun requestDataByNet(searchWord: String, onResponse: OnResponse<SearchResultModel>?) { //     val reqUrl = ""
            //     val reqJsonObject = JSONObject()
            //     reqJsonObject.put("", "")
            //     var data = ""
            //     var rpd = ""
            //     val randomPass = EncryptProxy.createRandomPass()
            //     // 一次一密
            //     try {
            //         data = URLEncoder.encode(EncryptProxyUtils.encryptRequestData(randomPass, reqJsonObject.toString()), "utf-8")
            //         rpd = EncryptProxyUtils.encryptByPublicKey(randomPass)
            //     } catch (e: UnsupportedEncodingException) {
            //         LogUtils.e(t = e)
            //     }
            //
            //     val pairs: MutableList<NameValuePair> = ArrayList()
            //     pairs.add(BasicNameValuePair("data", data))
            //     pairs.add(BasicNameValuePair("rpd", rpd))
            //
            //     val request = NetRequestUtils.request(reqUrl, URLEncodedUtils.format(pairs, "utf-8"), { response ->
            //         val singleDecryptNetworkBean = SingleDecryptNetworkBean(randomPass, response.getResult())
            //         val resCode = singleDecryptNetworkBean.rsponseCode
            //         val resMsg = singleDecryptNetworkBean.rsponseMsg
            //         val result = singleDecryptNetworkBean.result
            //         if ("0000" == resCode) {
            //             onResponse?.onSuccess(SearchResultModel(result))
            //         } else {
            //             onResponse?.onFail(resMsg)
            //         }
            //     }, { error ->
            //         onResponse?.onFail(VolleyErrorHelper.getMessage(error))
            //     }, false)
            //     VolleyRequestController.getInstance().addToRequestQueueWithoutCache(request, this)
            // }
        }
    }
    
    
    /** ****************************** Properties ****************************** */
    
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
    
    
    /** ****************************** Constructors ****************************** */
    
    init {
        setProperties(json)
    }
    
    
    /** ****************************** Functions ****************************** */
    
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
            LogUtils.e(TAG, t = e)
        }
    }
}
