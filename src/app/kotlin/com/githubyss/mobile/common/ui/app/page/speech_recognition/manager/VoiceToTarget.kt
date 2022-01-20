package com.githubyss.mobile.common.ui.app.page.speech_recognition.manager

import android.content.Context
import android.graphics.drawable.Icon
import android.net.Uri
import android.text.TextUtils
import androidx.fragment.app.FragmentActivity
import com.githubyss.mobile.common.kit.util.LogUtils
import com.githubyss.mobile.common.ui.app.application.ComuiApplication
import java.util.*

class VoiceToTarget private constructor(context: Context) {
    companion object {
        val TAG: String = VoiceToTarget::class.java.simpleName
        private var sInstance: VoiceToTarget? = null
        private const val SCHEME = "com.suning.jr"
        private const val SCHEME_SMS = "http"
        private const val HOST = "t.suning.cn"
        private const val KEY = "key"

        //H5专用key,需要校验域名的合法性
        private const val WEBKEY = "web"
        private const val KEY_URL = "url"
        private const val ACCOUNTKEY = "account"

        //大数据统计key
        private const val KEY_UTM_SOURCE = "utm_source"
        private const val KEY_UTM_MEDIUM = "utm_medium"

        @JvmStatic
        val instance: VoiceToTarget?
            get() {
                if (sInstance == null) {
                    sInstance = VoiceToTarget(ComuiApplication.getApp())
                }
                return sInstance
            }

        fun isNativeApp(uriString: String?): Boolean {
            if (!TextUtils.isEmpty(uriString)) {
                try {
                    val uri = Uri.parse(uriString)
                    val scheme = uri.scheme
                    val keyvalue = uri.getQueryParameter(KEY)
                    //此处只粗略校验scheme是否符合要求，key值是否为空，具体校验在跳转处进行
                    if (!TextUtils.isEmpty(scheme)
                        && !TextUtils.isEmpty(keyvalue)
                        && (SCHEME == scheme || SCHEME_SMS == scheme)) {
                        return true
                    }
                }
                catch (e: Exception) {
                    LogUtils.e(TAG, t = e)
                    return false
                }
            }
            return false
        }

        fun getAppKey(uriString: String?): String? {
            if (!TextUtils.isEmpty(uriString)) {
                try {
                    val uri = Uri.parse(uriString)
                    return uri.getQueryParameter(KEY)
                }
                catch (e: Exception) {
                    LogUtils.e(TAG, t = e)
                }
            }
            return uriString
        }

        fun getStringUrl(uriString: String?): Uri? {
            if (!TextUtils.isEmpty(uriString)) {
                try {
                    return Uri.parse(uriString)
                }
                catch (e: Exception) {
                    LogUtils.e(TAG, t = e)
                }
            }
            return null
        }

        fun getUtmSource(uriString: String?): String? {
            if (!TextUtils.isEmpty(uriString)) {
                try {
                    val uri = Uri.parse(uriString)
                    return uri.getQueryParameter(KEY_UTM_SOURCE)
                }
                catch (e: Exception) {
                    LogUtils.e(TAG, t = e)
                }
            }
            return null
        }

        fun getUtmMedium(uriString: String?): String? {
            if (!TextUtils.isEmpty(uriString)) {
                try {
                    val uri = Uri.parse(uriString)
                    return uri.getQueryParameter(KEY_UTM_MEDIUM)
                }
                catch (e: Exception) {
                    LogUtils.e(TAG, t = e)
                }
            }
            return null
        }
    }

    private val mIconMap: Map<String?, Icon>? = HashMap()
    private var iconItem: Icon? = null
    private val mActivity: FragmentActivity? = null
    fun refreshIcon() {
        // LauncherMode launcherMode = new LauncherMode(mActivity, LauncherMode.ALL);
        // mIconMap = launcherMode.mAllIconWithH5Map;
    }

    var account = ""
    fun toApp(activity: FragmentActivity?, uriString: String?) {
        if (activity == null
            || TextUtils.isEmpty(uriString)) {
            return
        }
        var key = ""
        try {
            val uri = Uri.parse(uriString)
            key = uri.getQueryParameter(KEY).toString()
        }
        catch (e: Exception) {
            LogUtils.e(TAG, t = e)
            return
        }
        //        mActivity = activity;
        if (mIconMap != null && mIconMap.size > 0 && mIconMap.containsKey(key)) {
            iconItem = mIconMap[key]
            // if (!TextUtils.isEmpty(iconItem.getUrl())) {
            // EpaPageRouterUtil.getInstance().pageJump(activity, iconItem.getUrl())
            // }
            // else {
            // EpaPageRouterUtil.getInstance().pageJump(activity, uriString)
            // }
        }
    }

    private fun isNeedLogin(iconItem: Icon): Boolean {
        val needLoginIcon = arrayOf("lqd", "sjcz", "zz", "xykhk", "rxf",
                                    "shjf", "csykt", "snk", "customerService", "hongbao", "scan", "paycode", "rxd",
                                    "addbankcard", "changemobile", "resetpaypwd", "accountbalance", "xyykt", "sjfw", "fdjsq")
        //本地应用除了易购图标都需要登录
        // if ("0" == iconItem.getJoinType() && "snyg" != iconItem.getAppFunction()
        //     && "xyzq" != iconItem.getAppFunction()
        //     && "zc" != iconItem.getAppFunction()
        //     && "lc" != iconItem.getAppFunction()) {
        //     return true
        // }
        // //特殊要求需要登录的H5,要考虑无返回时本地的区分
        // for (value in needLoginIcon) {
        //     if (value == iconItem.getAppFunction()) {
        //         return true
        //     }
        // }
        return false
    }

    private fun validateWebUrl(url: String?): Boolean {
        if (url != null && "" != url) {
            try {
                val uri = Uri.parse(url)
                val webHost = uri.host
                // if (ConfigCommerce.getInstance().marketingUrl.equals(webHost)) {
                //     return true
                // }
            }
            catch (e: Exception) {
            }
            return false
        }
        return false
    }

    fun checkAllIConHasKey(voiceKey: String?): Boolean {
        return mIconMap != null && mIconMap.size > 0 && mIconMap.containsKey(voiceKey)
    }
}