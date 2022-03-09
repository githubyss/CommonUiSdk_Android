package com.githubyss.mobile.common.ui.recycler_view.three_layer.optimise

import com.githubyss.mobile.common.kit.util.getJSONArrayFromJSONObject
import com.githubyss.mobile.common.kit.util.getJSONObjectFromJSONObject
import com.githubyss.mobile.common.kit.util.getJSONObjectFromJSONArray
import com.githubyss.mobile.common.kit.util.getStringFromJSONObject
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class ProductInfo(json: JSONObject?) {
    var id: String = ""
    var productName: String = ""
    var productIsShow: String = ""

    var iconUrl: String = ""
    var isMore: String = ""

    //-1-不跳转，0-native，1-h5，2-h5-支持小程序分享
    var moreJoinType: String = ""
    var moreJoinUrl: String = ""
    var moreJoinTip: String = ""
    var moreVersionCode: String = ""
    var moreUpgradeType: String = ""

    var marginTop: String = ""

    //0-不实名 1-实名
    var moreIsRealName: String = ""

    //0-不登录 1-登录
    var moreIsLogin: String = ""

    var productTemplateList: ArrayList<ProductTemplate> = ArrayList()
        private set

    init {
        try {
            json?.let {
                id = getStringFromJSONObject(it, "id")
                productIsShow = getStringFromJSONObject(it, "productIsShow")
                productName = getStringFromJSONObject(it, "productName")
                moreJoinTip = getStringFromJSONObject(it, "moreJoinTip")
                moreVersionCode = getStringFromJSONObject(it, "moreVersionCode")
                moreUpgradeType = getStringFromJSONObject(it, "moreUpgradeType")
                marginTop = getStringFromJSONObject(it, "marginTop")
                iconUrl = getStringFromJSONObject(it, "iconUrl")
                isMore = getStringFromJSONObject(it, "isMore")
                moreJoinType = getStringFromJSONObject(it, "moreJoinType")
                moreJoinUrl = getStringFromJSONObject(it, "moreJoinUrl")
                moreIsRealName = getStringFromJSONObject(it, "moreIsRealName")
                moreIsLogin = getStringFromJSONObject(it, "moreIsLogin")
                val productTemplates: JSONArray? = getJSONArrayFromJSONObject(it, "productTemplates")
                productTemplates?.let { it2 ->
                    for (i in 0 until it2.length()) {
                        val productTemplate: JSONObject? = getJSONObjectFromJSONArray(it2, i)
                        productTemplateList.add(ProductTemplate(productTemplate))

                        // val inProdRowsJsonArray = it2.getJSONArray(i)
                        // if (null != inProdRowsJsonArray) {
                        //     var inProdRowModels = ArrayList<ProductItem>()
                        //     for (j in 0 until inProdRowsJsonArray.length()) {
                        //         var productItem: ProductItem = ProductItem(inProdRowsJsonArray.getJSONObject(j))
                        //         inProdRowModels.add(productItem)
                        //     }
                        //     productTemplateList!!.add(inProdRowModels)
                        // }
                    }
                }
            }
        }
        catch (e: JSONException) {
        }
    }


    class ProductTemplate(json: JSONObject?) {
        /**
         * 010 代表01的产品上下排列, 011代表01的产品左右排列
         * 011-新人模板
         * 021-财富模板
         * 030-升级贷模板
         * 040-微商贷模板
         * 051-热门活动模板
         * 061-热门产品模板
         * 071-优选模板
         * 080-理财模板
         * 091-banner模板
         * 100-存款模板
         * 111-基金模板
         * 120-我的贷款模板
         * 180-借钱头部模板
         */
        var templateType: String? = null
        var templateItemList: ArrayList<ProductTemplateItem> = ArrayList()
            private set

        init {
            try {
                json?.let {
                    templateType = getStringFromJSONObject(it, "templateType")

                    getJSONArrayFromJSONObject(json, "templateItems")?.let { it2 ->
                        for (i in 0 until it2.length()) {
                            val templateItem: JSONObject? = getJSONObjectFromJSONArray(it2, i)
                            templateItemList.add(ProductTemplateItem(templateItem))
                        }
                    }
                }
            }
            catch (e: JSONException) {
            }
        }


        class ProductTemplateItem(json: JSONObject?) {
            var productItemName: String? = null
            var productId: String? = null
            var isRealName: String? = null
            var isLogin: String? = null
            var joinType: String? = null
            var joinUrl: String? = null
            var joinTip: String? = null
            var id: String? = null
            var versionCode: String? = null
            var upgradeType: String? = null
            var startTime: String? = null
            var endTime: String? = null
            var roundTopLeft: String? = null
            var roundTopRight: String? = null
            var roundBottomLeft: String? = null
            var roundBottomRight: String? = null
            var marginLeft: String? = null
            var marginTop: String? = null

            var detail: ProductDetail? = null

            init {
                try {
                    json?.let {
                        productItemName = getStringFromJSONObject(it, "productItemName")
                        productId = getStringFromJSONObject(it, "productId")
                        isRealName = getStringFromJSONObject(it, "isRealName")
                        isLogin = getStringFromJSONObject(it, "isLogin")
                        joinType = getStringFromJSONObject(it, "joinType")
                        joinTip = getStringFromJSONObject(it, "joinTip")
                        joinUrl = getStringFromJSONObject(it, "joinUrl")
                        id = getStringFromJSONObject(it, "id")
                        versionCode = getStringFromJSONObject(it, "versionCode")
                        upgradeType = getStringFromJSONObject(it, "upgradeType")
                        startTime = getStringFromJSONObject(it, "startTime")
                        endTime = getStringFromJSONObject(it, "endTime")
                        roundTopLeft = getStringFromJSONObject(it, "roundTopLeft")
                        roundTopRight = getStringFromJSONObject(it, "roundTopRight")
                        roundBottomLeft = getStringFromJSONObject(it, "roundBottomLeft")
                        roundBottomRight = getStringFromJSONObject(it, "roundBottomRight")
                        marginLeft = getStringFromJSONObject(it, "marginLeft")
                        marginTop = getStringFromJSONObject(it, "marginTop")

                        detail = ProductDetail(getJSONObjectFromJSONObject(it, "detail"))
                    }
                }
                catch (e: JSONException) {
                }
            }


            class ProductDetail(json: JSONObject?) {
                var bannerImgUrl: String? = null
                var newRate: String? = null
                var newSign: String? = null
                var newRateContent: String? = null
                var newProdName: String? = null
                var newDesc1: String? = null
                var newDesc2: String? = null
                var newDesc3: String? = null
                var newBgImgUrl: String? = null
                var newBtnName: String? = null
                var wealthProdName: String? = null
                var wealthRate: String? = null
                var wealthSign: String? = null
                var wealthRateContent: String? = null
                var wealthDesc1: String? = null
                var wealthDesc2: String? = null
                var loanProdName: String? = null
                var loanRate: String? = null
                var loanSign: String? = null
                var loanRateContent: String? = null
                var loanAmount: String? = null
                var loanAmountContent: String? = null
                var loanDesc1: String? = null
                var loanDesc2: String? = null
                var loanDesc3: String? = null
                var loanTag: String? = null

                /**
                 * a-未授信
                 * b-已授信未出金额
                 * c-授信成功但未借钱
                 * d-已借钱
                 * e-额度已用完
                 * f-已逾期
                 */
                var loanStatus: String? = null
                var hotEventImgUrl1: String? = null
                var hotEventImgUrl2: String? = null
                var hotEventImgUrl3: String? = null
                var hotEventSkipUrl1: String? = null
                var hotEventSkipUrl2: String? = null
                var hotEventSkipUrl3: String? = null
                var hotEventSkipType1: String? = null
                var hotEventSkipType2: String? = null
                var hotEventSkipType3: String? = null
                var hotProdName: String? = null
                var hotButtonDesc: String? = null
                var hotRate: String? = null
                var hotSign: String? = null
                var hotRateComment: String? = null
                var hotDesc1: String? = null
                var hotDesc2: String? = null
                var hotDesc3: String? = null
                var hotBgImgUrl: String? = null
                var goodProdName: String? = null
                var goodRate: String? = null
                var goodRateSign: String? = null
                var goodRateContent: String? = null
                var goodDesc1: String? = null
                var goodDesc2: String? = null
                var financeProdName: String? = null
                var financeRate: String? = null
                var financeRateSign: String? = null
                var amtSign: String? = null
                var termSign: String? = null
                var financeRateContent: String? = null
                var financeAmount: String? = null
                var financeAmountContent: String? = null
                var financeTerm: String? = null
                var financeTermContent: String? = null
                var financeDesc1: String? = null
                var financeDesc2: String? = null
                var financeDesc3: String? = null
                var depositProdName: String? = null
                var depositProdName2: String? = null
                var depositRate: String? = null
                var depositRateSign: String? = null
                var depositRateContent: String? = null
                var depositDesc1: String? = null
                var depositDesc2: String? = null
                var depositDesc3: String? = null
                var fundProdName: String? = null
                var fundImgUrl: String? = null
                var fundDesc1: String? = null
                var fundDesc2: String? = null
                var fundDesc3: String? = null
                //    var creditContent: String? = null
                //    var creditAmount: String? = null
                //    var creditRate: String? = null


                var creditRateSign: String? = null
                var buttonName: String? = null
                var buttonName1: String? = null

                var productName: String? = null
                var addSign: String? = null
                var productDesc1: String? = null
                var rate: String? = null
                var rate2: String? = null
                var productDesc2: String? = null
                var productDesc3: String? = null
                var rateSign: String? = null
                var rateSign2: String? = null
                var rateDesc: String? = null
                var desc1: String? = null
                var desc1Sign: String? = null

                var desc2: String? = null
                var desc3: String? = null
                var desc4: String? = null
                var desc5: String? = null
                var desc6: String? = null
                var desc7: String? = null
                var productDesc: String? = null
                var productIcon: String? = null
                var hotIcon: String? = null
                var amt: String? = null


                /**
                 * a-未授信
                 * b-已授信未出金额
                 * c-授信成功但未借钱
                 * d-已借钱
                 * e-额度已用完
                 * f-已逾期
                 */
                var creditStatus: String? = null
                var creditProdName: String? = null
                var creditTag: String? = null
                var paidAmount: String? = null
                var paidAmountContent: String? = null
                var creditTerm: String? = null
                var creditDescA: String? = null
                var creditDescC: String? = null

                //新的贷款
                var prodName: String? = null
                var top1: String? = null
                var top2: String? = null
                var top3: String? = null
                var topFlag: String? = null
                var middle1: String? = null
                var middle2: String? = null
                var bottom1: String? = null
                var bottom2: String? = null
                var bottom3: String? = null
                var button1: String? = null
                var button2: String? = null

                //新的我的贷款
                var myProdName: String? = null
                var myProdDesc: String? = null
                var myTop1: String? = null
                var myTop2: String? = null
                var myTop3: String? = null
                var myTop4: String? = null
                var myTopFlag: String? = null
                var myMiddle1: String? = null
                var myMiddle2: String? = null
                var myMiddle3: String? = null
                var myBottom1: String? = null
                var myBottom2: String? = null
                var myBottom3: String? = null

                init {
                    try {
                        json?.let {
                            bannerImgUrl = getStringFromJSONObject(it, "bannerImgUrl")
                            newRate = getStringFromJSONObject(it, "newRate")
                            newSign = getStringFromJSONObject(it, "newSign")
                            newRateContent = getStringFromJSONObject(it, "newRateContent")
                            newProdName = getStringFromJSONObject(it, "newProdName")
                            newDesc1 = getStringFromJSONObject(it, "newDesc1")
                            newDesc2 = getStringFromJSONObject(it, "newDesc2")
                            newDesc3 = getStringFromJSONObject(it, "newDesc3")
                            newBgImgUrl = getStringFromJSONObject(it, "newBgImgUrl")
                            newBtnName = getStringFromJSONObject(it, "newBtnName")
                            wealthProdName = getStringFromJSONObject(it, "wealthProdName")
                            wealthRate = getStringFromJSONObject(it, "wealthRate")
                            wealthSign = getStringFromJSONObject(it, "wealthSign")
                            wealthRateContent = getStringFromJSONObject(it, "wealthRateContent")
                            wealthDesc1 = getStringFromJSONObject(it, "wealthDesc1")
                            wealthDesc2 = getStringFromJSONObject(it, "wealthDesc2")
                            loanProdName = getStringFromJSONObject(it, "loanProdName")
                            loanRate = getStringFromJSONObject(it, "loanRate")
                            loanSign = getStringFromJSONObject(it, "loanSign")
                            loanRateContent = getStringFromJSONObject(it, "loanRateContent")
                            loanAmount = getStringFromJSONObject(it, "loanAmount")
                            loanAmountContent = getStringFromJSONObject(it, "loanAmountContent")
                            loanDesc1 = getStringFromJSONObject(it, "loanDesc1")
                            loanDesc2 = getStringFromJSONObject(it, "loanDesc2")
                            loanDesc3 = getStringFromJSONObject(it, "loanDesc3")
                            loanTag = getStringFromJSONObject(it, "loanTag")
                            loanStatus = getStringFromJSONObject(it, "loanStatus")
                            hotEventImgUrl1 = getStringFromJSONObject(it, "hotEventImgUrl1")
                            hotEventImgUrl2 = getStringFromJSONObject(it, "hotEventImgUrl2")
                            hotEventImgUrl3 = getStringFromJSONObject(it, "hotEventImgUrl3")
                            hotEventSkipUrl1 = getStringFromJSONObject(it, "hotEventSkipUrl1")
                            hotEventSkipUrl2 = getStringFromJSONObject(it, "hotEventSkipUrl2")
                            hotEventSkipUrl3 = getStringFromJSONObject(it, "hotEventSkipUrl3")
                            hotEventSkipType1 = getStringFromJSONObject(it, "hotEventSkipType1")
                            hotEventSkipType2 = getStringFromJSONObject(it, "hotEventSkipType2")
                            hotEventSkipType3 = getStringFromJSONObject(it, "hotEventSkipType3")
                            hotProdName = getStringFromJSONObject(it, "hotProdName")
                            hotButtonDesc = getStringFromJSONObject(it, "hotButtonDesc")
                            hotRate = getStringFromJSONObject(it, "hotRate")
                            hotSign = getStringFromJSONObject(it, "hotSign")
                            hotRateComment = getStringFromJSONObject(it, "hotRateComment")
                            hotDesc1 = getStringFromJSONObject(it, "hotDesc1")
                            hotDesc2 = getStringFromJSONObject(it, "hotDesc2")
                            hotDesc3 = getStringFromJSONObject(it, "hotDesc3")
                            hotBgImgUrl = getStringFromJSONObject(it, "hotBgImgUrl")
                            goodProdName = getStringFromJSONObject(it, "goodProdName")
                            goodRate = getStringFromJSONObject(it, "goodRate")
                            goodRateSign = getStringFromJSONObject(it, "goodRateSign")
                            goodRateContent = getStringFromJSONObject(it, "goodRateContent")
                            goodDesc1 = getStringFromJSONObject(it, "goodDesc1")
                            goodDesc2 = getStringFromJSONObject(it, "goodDesc2")
                            financeProdName = getStringFromJSONObject(it, "financeProdName")
                            financeRate = getStringFromJSONObject(it, "financeRate")
                            financeRateSign = getStringFromJSONObject(it, "financeRateSign")
                            amtSign = getStringFromJSONObject(it, "amtSign")
                            termSign = getStringFromJSONObject(it, "termSign")
                            financeRateContent = getStringFromJSONObject(it, "financeRateContent")
                            financeAmount = getStringFromJSONObject(it, "financeAmount")
                            financeAmountContent = getStringFromJSONObject(it, "financeAmountContent")
                            financeTerm = getStringFromJSONObject(it, "financeTerm")
                            financeTermContent = getStringFromJSONObject(it, "financeTermContent")
                            financeDesc1 = getStringFromJSONObject(it, "financeDesc1")
                            financeDesc2 = getStringFromJSONObject(it, "financeDesc2")
                            financeDesc3 = getStringFromJSONObject(it, "financeDesc3")
                            depositProdName = getStringFromJSONObject(it, "depositProdName")
                            depositProdName2 = getStringFromJSONObject(it, "depositProdName2")
                            depositRate = getStringFromJSONObject(it, "depositRate")
                            depositRateSign = getStringFromJSONObject(it, "depositRateSign")
                            depositRateContent = getStringFromJSONObject(it, "depositRateContent")
                            depositDesc1 = getStringFromJSONObject(it, "depositDesc1")
                            depositDesc2 = getStringFromJSONObject(it, "depositDesc2")
                            depositDesc3 = getStringFromJSONObject(it, "depositDesc3")
                            fundProdName = getStringFromJSONObject(it, "fundProdName")
                            fundImgUrl = getStringFromJSONObject(it, "fundImgUrl")
                            fundDesc1 = getStringFromJSONObject(it, "fundDesc1")
                            fundDesc2 = getStringFromJSONObject(it, "fundDesc2")
                            fundDesc3 = getStringFromJSONObject(it, "fundDesc3")
                            creditProdName = getStringFromJSONObject(it, "creditProdName")
                            //                creditAmount = getStringFromJSONObject(it, "creditAmount")
                            paidAmount = getStringFromJSONObject(it, "paidAmount")
                            paidAmountContent = getStringFromJSONObject(it, "paidAmountContent")
                            creditTerm = getStringFromJSONObject(it, "creditTerm")
                            creditDescA = getStringFromJSONObject(it, "creditDescA")
                            creditDescC = getStringFromJSONObject(it, "creditDescC")
                            //                creditRate = getStringFromJSONObject(it, "creditRate")
                            creditRateSign = getStringFromJSONObject(it, "creditRateSign")
                            creditTag = getStringFromJSONObject(it, "creditTag")
                            creditStatus = getStringFromJSONObject(it, "creditStatus")
                            buttonName = getStringFromJSONObject(it, "buttonName")
                            buttonName1 = getStringFromJSONObject(it, "buttonName1")

                            prodName = getStringFromJSONObject(it, "prodName")
                            top1 = getStringFromJSONObject(it, "top1")
                            top2 = getStringFromJSONObject(it, "top2")
                            top3 = getStringFromJSONObject(it, "top3")
                            topFlag = getStringFromJSONObject(it, "topFlag")
                            middle1 = getStringFromJSONObject(it, "middle1")
                            middle2 = getStringFromJSONObject(it, "middle2")
                            bottom1 = getStringFromJSONObject(it, "bottom1")
                            bottom2 = getStringFromJSONObject(it, "bottom2")
                            bottom3 = getStringFromJSONObject(it, "bottom3")
                            button1 = getStringFromJSONObject(it, "button1")
                            button2 = getStringFromJSONObject(it, "button2")
                            myProdName = getStringFromJSONObject(it, "myProdName")
                            myProdDesc = getStringFromJSONObject(it, "myProdDesc")
                            myTop1 = getStringFromJSONObject(it, "myTop1")
                            myTop2 = getStringFromJSONObject(it, "myTop2")
                            myTop3 = getStringFromJSONObject(it, "myTop3")
                            myTop4 = getStringFromJSONObject(it, "myTop4")
                            myTopFlag = getStringFromJSONObject(it, "myTopFlag")
                            myMiddle1 = getStringFromJSONObject(it, "myMiddle1")
                            myMiddle2 = getStringFromJSONObject(it, "myMiddle2")
                            myMiddle3 = getStringFromJSONObject(it, "myMiddle3")
                            myBottom1 = getStringFromJSONObject(it, "myBottom1")
                            myBottom2 = getStringFromJSONObject(it, "myBottom2")
                            myBottom3 = getStringFromJSONObject(it, "myBottom3")
                            productName = getStringFromJSONObject(it, "productName")
                            productDesc1 = getStringFromJSONObject(it, "productDesc1")
                            rate = getStringFromJSONObject(it, "rate")
                            rate2 = getStringFromJSONObject(it, "rate2")
                            productDesc2 = getStringFromJSONObject(it, "productDesc2")
                            productDesc3 = getStringFromJSONObject(it, "productDesc3")
                            rateSign = getStringFromJSONObject(it, "rateSign")
                            addSign = getStringFromJSONObject(it, "addSign")
                            rateSign2 = getStringFromJSONObject(it, "rateSign2")
                            rateDesc = getStringFromJSONObject(it, "rateDesc")
                            desc1 = getStringFromJSONObject(it, "desc1")
                            desc1Sign = getStringFromJSONObject(it, "desc1Sign")
                            desc2 = getStringFromJSONObject(it, "desc2")
                            desc3 = getStringFromJSONObject(it, "desc3")
                            desc4 = getStringFromJSONObject(it, "desc4")
                            desc5 = getStringFromJSONObject(it, "desc5")
                            desc6 = getStringFromJSONObject(it, "desc6")
                            desc7 = getStringFromJSONObject(it, "desc7")
                            productDesc = getStringFromJSONObject(it, "productDesc")
                            productIcon = getStringFromJSONObject(it, "productIcon")
                            hotIcon = getStringFromJSONObject(it, "hotIcon")
                            amt = getStringFromJSONObject(it, "amt")
                        }
                    }
                    catch (e: JSONException) {
                    }
                }
            }
        }
    }
}
