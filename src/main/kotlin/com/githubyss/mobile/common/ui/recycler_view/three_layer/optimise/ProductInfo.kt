package com.githubyss.mobile.common.ui.recycler_view.three_layer.optimise

import com.githubyss.mobile.common.kit.util.JsonUtils
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
                id = JsonUtils.getString(it, "id")
                productIsShow = JsonUtils.getString(it, "productIsShow")
                productName = JsonUtils.getString(it, "productName")
                moreJoinTip = JsonUtils.getString(it, "moreJoinTip")
                moreVersionCode = JsonUtils.getString(it, "moreVersionCode")
                moreUpgradeType = JsonUtils.getString(it, "moreUpgradeType")
                marginTop = JsonUtils.getString(it, "marginTop")
                iconUrl = JsonUtils.getString(it, "iconUrl")
                isMore = JsonUtils.getString(it, "isMore")
                moreJoinType = JsonUtils.getString(it, "moreJoinType")
                moreJoinUrl = JsonUtils.getString(it, "moreJoinUrl")
                moreIsRealName = JsonUtils.getString(it, "moreIsRealName")
                moreIsLogin = JsonUtils.getString(it, "moreIsLogin")
                val productTemplates: JSONArray? = JsonUtils.getJSONArray(it, "productTemplates")
                productTemplates?.let { it2 ->
                    for (i in 0 until it2.length()) {
                        val productTemplate: JSONObject? = JsonUtils.getJSONObject(it2, i)
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
                    templateType = JsonUtils.getString(it, "templateType")

                    JsonUtils.getJSONArray(json, "templateItems")?.let { it2 ->
                        for (i in 0 until it2.length()) {
                            val templateItem: JSONObject? = JsonUtils.getJSONObject(it2, i)
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
                        productItemName = JsonUtils.getString(it, "productItemName")
                        productId = JsonUtils.getString(it, "productId")
                        isRealName = JsonUtils.getString(it, "isRealName")
                        isLogin = JsonUtils.getString(it, "isLogin")
                        joinType = JsonUtils.getString(it, "joinType")
                        joinTip = JsonUtils.getString(it, "joinTip")
                        joinUrl = JsonUtils.getString(it, "joinUrl")
                        id = JsonUtils.getString(it, "id")
                        versionCode = JsonUtils.getString(it, "versionCode")
                        upgradeType = JsonUtils.getString(it, "upgradeType")
                        startTime = JsonUtils.getString(it, "startTime")
                        endTime = JsonUtils.getString(it, "endTime")
                        roundTopLeft = JsonUtils.getString(it, "roundTopLeft")
                        roundTopRight = JsonUtils.getString(it, "roundTopRight")
                        roundBottomLeft = JsonUtils.getString(it, "roundBottomLeft")
                        roundBottomRight = JsonUtils.getString(it, "roundBottomRight")
                        marginLeft = JsonUtils.getString(it, "marginLeft")
                        marginTop = JsonUtils.getString(it, "marginTop")

                        detail = ProductDetail(JsonUtils.getJSONObject(it, "detail"))
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
                            bannerImgUrl = JsonUtils.getString(it, "bannerImgUrl")
                            newRate = JsonUtils.getString(it, "newRate")
                            newSign = JsonUtils.getString(it, "newSign")
                            newRateContent = JsonUtils.getString(it, "newRateContent")
                            newProdName = JsonUtils.getString(it, "newProdName")
                            newDesc1 = JsonUtils.getString(it, "newDesc1")
                            newDesc2 = JsonUtils.getString(it, "newDesc2")
                            newDesc3 = JsonUtils.getString(it, "newDesc3")
                            newBgImgUrl = JsonUtils.getString(it, "newBgImgUrl")
                            newBtnName = JsonUtils.getString(it, "newBtnName")
                            wealthProdName = JsonUtils.getString(it, "wealthProdName")
                            wealthRate = JsonUtils.getString(it, "wealthRate")
                            wealthSign = JsonUtils.getString(it, "wealthSign")
                            wealthRateContent = JsonUtils.getString(it, "wealthRateContent")
                            wealthDesc1 = JsonUtils.getString(it, "wealthDesc1")
                            wealthDesc2 = JsonUtils.getString(it, "wealthDesc2")
                            loanProdName = JsonUtils.getString(it, "loanProdName")
                            loanRate = JsonUtils.getString(it, "loanRate")
                            loanSign = JsonUtils.getString(it, "loanSign")
                            loanRateContent = JsonUtils.getString(it, "loanRateContent")
                            loanAmount = JsonUtils.getString(it, "loanAmount")
                            loanAmountContent = JsonUtils.getString(it, "loanAmountContent")
                            loanDesc1 = JsonUtils.getString(it, "loanDesc1")
                            loanDesc2 = JsonUtils.getString(it, "loanDesc2")
                            loanDesc3 = JsonUtils.getString(it, "loanDesc3")
                            loanTag = JsonUtils.getString(it, "loanTag")
                            loanStatus = JsonUtils.getString(it, "loanStatus")
                            hotEventImgUrl1 = JsonUtils.getString(it, "hotEventImgUrl1")
                            hotEventImgUrl2 = JsonUtils.getString(it, "hotEventImgUrl2")
                            hotEventImgUrl3 = JsonUtils.getString(it, "hotEventImgUrl3")
                            hotEventSkipUrl1 = JsonUtils.getString(it, "hotEventSkipUrl1")
                            hotEventSkipUrl2 = JsonUtils.getString(it, "hotEventSkipUrl2")
                            hotEventSkipUrl3 = JsonUtils.getString(it, "hotEventSkipUrl3")
                            hotEventSkipType1 = JsonUtils.getString(it, "hotEventSkipType1")
                            hotEventSkipType2 = JsonUtils.getString(it, "hotEventSkipType2")
                            hotEventSkipType3 = JsonUtils.getString(it, "hotEventSkipType3")
                            hotProdName = JsonUtils.getString(it, "hotProdName")
                            hotButtonDesc = JsonUtils.getString(it, "hotButtonDesc")
                            hotRate = JsonUtils.getString(it, "hotRate")
                            hotSign = JsonUtils.getString(it, "hotSign")
                            hotRateComment = JsonUtils.getString(it, "hotRateComment")
                            hotDesc1 = JsonUtils.getString(it, "hotDesc1")
                            hotDesc2 = JsonUtils.getString(it, "hotDesc2")
                            hotDesc3 = JsonUtils.getString(it, "hotDesc3")
                            hotBgImgUrl = JsonUtils.getString(it, "hotBgImgUrl")
                            goodProdName = JsonUtils.getString(it, "goodProdName")
                            goodRate = JsonUtils.getString(it, "goodRate")
                            goodRateSign = JsonUtils.getString(it, "goodRateSign")
                            goodRateContent = JsonUtils.getString(it, "goodRateContent")
                            goodDesc1 = JsonUtils.getString(it, "goodDesc1")
                            goodDesc2 = JsonUtils.getString(it, "goodDesc2")
                            financeProdName = JsonUtils.getString(it, "financeProdName")
                            financeRate = JsonUtils.getString(it, "financeRate")
                            financeRateSign = JsonUtils.getString(it, "financeRateSign")
                            amtSign = JsonUtils.getString(it, "amtSign")
                            termSign = JsonUtils.getString(it, "termSign")
                            financeRateContent = JsonUtils.getString(it, "financeRateContent")
                            financeAmount = JsonUtils.getString(it, "financeAmount")
                            financeAmountContent = JsonUtils.getString(it, "financeAmountContent")
                            financeTerm = JsonUtils.getString(it, "financeTerm")
                            financeTermContent = JsonUtils.getString(it, "financeTermContent")
                            financeDesc1 = JsonUtils.getString(it, "financeDesc1")
                            financeDesc2 = JsonUtils.getString(it, "financeDesc2")
                            financeDesc3 = JsonUtils.getString(it, "financeDesc3")
                            depositProdName = JsonUtils.getString(it, "depositProdName")
                            depositProdName2 = JsonUtils.getString(it, "depositProdName2")
                            depositRate = JsonUtils.getString(it, "depositRate")
                            depositRateSign = JsonUtils.getString(it, "depositRateSign")
                            depositRateContent = JsonUtils.getString(it, "depositRateContent")
                            depositDesc1 = JsonUtils.getString(it, "depositDesc1")
                            depositDesc2 = JsonUtils.getString(it, "depositDesc2")
                            depositDesc3 = JsonUtils.getString(it, "depositDesc3")
                            fundProdName = JsonUtils.getString(it, "fundProdName")
                            fundImgUrl = JsonUtils.getString(it, "fundImgUrl")
                            fundDesc1 = JsonUtils.getString(it, "fundDesc1")
                            fundDesc2 = JsonUtils.getString(it, "fundDesc2")
                            fundDesc3 = JsonUtils.getString(it, "fundDesc3")
                            creditProdName = JsonUtils.getString(it, "creditProdName")
                            //                creditAmount = JsonUtils.getString(it, "creditAmount")
                            paidAmount = JsonUtils.getString(it, "paidAmount")
                            paidAmountContent = JsonUtils.getString(it, "paidAmountContent")
                            creditTerm = JsonUtils.getString(it, "creditTerm")
                            creditDescA = JsonUtils.getString(it, "creditDescA")
                            creditDescC = JsonUtils.getString(it, "creditDescC")
                            //                creditRate = JsonUtils.getString(it, "creditRate")
                            creditRateSign = JsonUtils.getString(it, "creditRateSign")
                            creditTag = JsonUtils.getString(it, "creditTag")
                            creditStatus = JsonUtils.getString(it, "creditStatus")
                            buttonName = JsonUtils.getString(it, "buttonName")
                            buttonName1 = JsonUtils.getString(it, "buttonName1")

                            prodName = JsonUtils.getString(it, "prodName")
                            top1 = JsonUtils.getString(it, "top1")
                            top2 = JsonUtils.getString(it, "top2")
                            top3 = JsonUtils.getString(it, "top3")
                            topFlag = JsonUtils.getString(it, "topFlag")
                            middle1 = JsonUtils.getString(it, "middle1")
                            middle2 = JsonUtils.getString(it, "middle2")
                            bottom1 = JsonUtils.getString(it, "bottom1")
                            bottom2 = JsonUtils.getString(it, "bottom2")
                            bottom3 = JsonUtils.getString(it, "bottom3")
                            button1 = JsonUtils.getString(it, "button1")
                            button2 = JsonUtils.getString(it, "button2")
                            myProdName = JsonUtils.getString(it, "myProdName")
                            myProdDesc = JsonUtils.getString(it, "myProdDesc")
                            myTop1 = JsonUtils.getString(it, "myTop1")
                            myTop2 = JsonUtils.getString(it, "myTop2")
                            myTop3 = JsonUtils.getString(it, "myTop3")
                            myTop4 = JsonUtils.getString(it, "myTop4")
                            myTopFlag = JsonUtils.getString(it, "myTopFlag")
                            myMiddle1 = JsonUtils.getString(it, "myMiddle1")
                            myMiddle2 = JsonUtils.getString(it, "myMiddle2")
                            myMiddle3 = JsonUtils.getString(it, "myMiddle3")
                            myBottom1 = JsonUtils.getString(it, "myBottom1")
                            myBottom2 = JsonUtils.getString(it, "myBottom2")
                            myBottom3 = JsonUtils.getString(it, "myBottom3")
                            productName = JsonUtils.getString(it, "productName")
                            productDesc1 = JsonUtils.getString(it, "productDesc1")
                            rate = JsonUtils.getString(it, "rate")
                            rate2 = JsonUtils.getString(it, "rate2")
                            productDesc2 = JsonUtils.getString(it, "productDesc2")
                            productDesc3 = JsonUtils.getString(it, "productDesc3")
                            rateSign = JsonUtils.getString(it, "rateSign")
                            addSign = JsonUtils.getString(it, "addSign")
                            rateSign2 = JsonUtils.getString(it, "rateSign2")
                            rateDesc = JsonUtils.getString(it, "rateDesc")
                            desc1 = JsonUtils.getString(it, "desc1")
                            desc1Sign = JsonUtils.getString(it, "desc1Sign")
                            desc2 = JsonUtils.getString(it, "desc2")
                            desc3 = JsonUtils.getString(it, "desc3")
                            desc4 = JsonUtils.getString(it, "desc4")
                            desc5 = JsonUtils.getString(it, "desc5")
                            desc6 = JsonUtils.getString(it, "desc6")
                            desc7 = JsonUtils.getString(it, "desc7")
                            productDesc = JsonUtils.getString(it, "productDesc")
                            productIcon = JsonUtils.getString(it, "productIcon")
                            hotIcon = JsonUtils.getString(it, "hotIcon")
                            amt = JsonUtils.getString(it, "amt")
                        }
                    }
                    catch (e: JSONException) {
                    }
                }
            }
        }
    }
}
