package com.githubyss.mobile.common.ui.recycler_view.three_layer.optimise

import com.githubyss.mobile.common.kit.util.getJSONArray
import com.githubyss.mobile.common.kit.util.getJSONObject
import com.githubyss.mobile.common.kit.util.getString
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
                id = getString(it, "id")
                productIsShow = getString(it, "productIsShow")
                productName = getString(it, "productName")
                moreJoinTip = getString(it, "moreJoinTip")
                moreVersionCode = getString(it, "moreVersionCode")
                moreUpgradeType = getString(it, "moreUpgradeType")
                marginTop = getString(it, "marginTop")
                iconUrl = getString(it, "iconUrl")
                isMore = getString(it, "isMore")
                moreJoinType = getString(it, "moreJoinType")
                moreJoinUrl = getString(it, "moreJoinUrl")
                moreIsRealName = getString(it, "moreIsRealName")
                moreIsLogin = getString(it, "moreIsLogin")
                val productTemplates: JSONArray? = getJSONArray(it, "productTemplates")
                productTemplates?.let { it2 ->
                    for (i in 0 until it2.length()) {
                        val productTemplate: JSONObject? = getJSONObject(it2, i)
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
                    templateType = getString(it, "templateType")

                    getJSONArray(json, "templateItems")?.let { it2 ->
                        for (i in 0 until it2.length()) {
                            val templateItem: JSONObject? = getJSONObject(it2, i)
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
                        productItemName = getString(it, "productItemName")
                        productId = getString(it, "productId")
                        isRealName = getString(it, "isRealName")
                        isLogin = getString(it, "isLogin")
                        joinType = getString(it, "joinType")
                        joinTip = getString(it, "joinTip")
                        joinUrl = getString(it, "joinUrl")
                        id = getString(it, "id")
                        versionCode = getString(it, "versionCode")
                        upgradeType = getString(it, "upgradeType")
                        startTime = getString(it, "startTime")
                        endTime = getString(it, "endTime")
                        roundTopLeft = getString(it, "roundTopLeft")
                        roundTopRight = getString(it, "roundTopRight")
                        roundBottomLeft = getString(it, "roundBottomLeft")
                        roundBottomRight = getString(it, "roundBottomRight")
                        marginLeft = getString(it, "marginLeft")
                        marginTop = getString(it, "marginTop")

                        detail = ProductDetail(getJSONObject(it, "detail"))
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
                            bannerImgUrl = getString(it, "bannerImgUrl")
                            newRate = getString(it, "newRate")
                            newSign = getString(it, "newSign")
                            newRateContent = getString(it, "newRateContent")
                            newProdName = getString(it, "newProdName")
                            newDesc1 = getString(it, "newDesc1")
                            newDesc2 = getString(it, "newDesc2")
                            newDesc3 = getString(it, "newDesc3")
                            newBgImgUrl = getString(it, "newBgImgUrl")
                            newBtnName = getString(it, "newBtnName")
                            wealthProdName = getString(it, "wealthProdName")
                            wealthRate = getString(it, "wealthRate")
                            wealthSign = getString(it, "wealthSign")
                            wealthRateContent = getString(it, "wealthRateContent")
                            wealthDesc1 = getString(it, "wealthDesc1")
                            wealthDesc2 = getString(it, "wealthDesc2")
                            loanProdName = getString(it, "loanProdName")
                            loanRate = getString(it, "loanRate")
                            loanSign = getString(it, "loanSign")
                            loanRateContent = getString(it, "loanRateContent")
                            loanAmount = getString(it, "loanAmount")
                            loanAmountContent = getString(it, "loanAmountContent")
                            loanDesc1 = getString(it, "loanDesc1")
                            loanDesc2 = getString(it, "loanDesc2")
                            loanDesc3 = getString(it, "loanDesc3")
                            loanTag = getString(it, "loanTag")
                            loanStatus = getString(it, "loanStatus")
                            hotEventImgUrl1 = getString(it, "hotEventImgUrl1")
                            hotEventImgUrl2 = getString(it, "hotEventImgUrl2")
                            hotEventImgUrl3 = getString(it, "hotEventImgUrl3")
                            hotEventSkipUrl1 = getString(it, "hotEventSkipUrl1")
                            hotEventSkipUrl2 = getString(it, "hotEventSkipUrl2")
                            hotEventSkipUrl3 = getString(it, "hotEventSkipUrl3")
                            hotEventSkipType1 = getString(it, "hotEventSkipType1")
                            hotEventSkipType2 = getString(it, "hotEventSkipType2")
                            hotEventSkipType3 = getString(it, "hotEventSkipType3")
                            hotProdName = getString(it, "hotProdName")
                            hotButtonDesc = getString(it, "hotButtonDesc")
                            hotRate = getString(it, "hotRate")
                            hotSign = getString(it, "hotSign")
                            hotRateComment = getString(it, "hotRateComment")
                            hotDesc1 = getString(it, "hotDesc1")
                            hotDesc2 = getString(it, "hotDesc2")
                            hotDesc3 = getString(it, "hotDesc3")
                            hotBgImgUrl = getString(it, "hotBgImgUrl")
                            goodProdName = getString(it, "goodProdName")
                            goodRate = getString(it, "goodRate")
                            goodRateSign = getString(it, "goodRateSign")
                            goodRateContent = getString(it, "goodRateContent")
                            goodDesc1 = getString(it, "goodDesc1")
                            goodDesc2 = getString(it, "goodDesc2")
                            financeProdName = getString(it, "financeProdName")
                            financeRate = getString(it, "financeRate")
                            financeRateSign = getString(it, "financeRateSign")
                            amtSign = getString(it, "amtSign")
                            termSign = getString(it, "termSign")
                            financeRateContent = getString(it, "financeRateContent")
                            financeAmount = getString(it, "financeAmount")
                            financeAmountContent = getString(it, "financeAmountContent")
                            financeTerm = getString(it, "financeTerm")
                            financeTermContent = getString(it, "financeTermContent")
                            financeDesc1 = getString(it, "financeDesc1")
                            financeDesc2 = getString(it, "financeDesc2")
                            financeDesc3 = getString(it, "financeDesc3")
                            depositProdName = getString(it, "depositProdName")
                            depositProdName2 = getString(it, "depositProdName2")
                            depositRate = getString(it, "depositRate")
                            depositRateSign = getString(it, "depositRateSign")
                            depositRateContent = getString(it, "depositRateContent")
                            depositDesc1 = getString(it, "depositDesc1")
                            depositDesc2 = getString(it, "depositDesc2")
                            depositDesc3 = getString(it, "depositDesc3")
                            fundProdName = getString(it, "fundProdName")
                            fundImgUrl = getString(it, "fundImgUrl")
                            fundDesc1 = getString(it, "fundDesc1")
                            fundDesc2 = getString(it, "fundDesc2")
                            fundDesc3 = getString(it, "fundDesc3")
                            creditProdName = getString(it, "creditProdName")
                            //                creditAmount = getString(it, "creditAmount")
                            paidAmount = getString(it, "paidAmount")
                            paidAmountContent = getString(it, "paidAmountContent")
                            creditTerm = getString(it, "creditTerm")
                            creditDescA = getString(it, "creditDescA")
                            creditDescC = getString(it, "creditDescC")
                            //                creditRate = getString(it, "creditRate")
                            creditRateSign = getString(it, "creditRateSign")
                            creditTag = getString(it, "creditTag")
                            creditStatus = getString(it, "creditStatus")
                            buttonName = getString(it, "buttonName")
                            buttonName1 = getString(it, "buttonName1")

                            prodName = getString(it, "prodName")
                            top1 = getString(it, "top1")
                            top2 = getString(it, "top2")
                            top3 = getString(it, "top3")
                            topFlag = getString(it, "topFlag")
                            middle1 = getString(it, "middle1")
                            middle2 = getString(it, "middle2")
                            bottom1 = getString(it, "bottom1")
                            bottom2 = getString(it, "bottom2")
                            bottom3 = getString(it, "bottom3")
                            button1 = getString(it, "button1")
                            button2 = getString(it, "button2")
                            myProdName = getString(it, "myProdName")
                            myProdDesc = getString(it, "myProdDesc")
                            myTop1 = getString(it, "myTop1")
                            myTop2 = getString(it, "myTop2")
                            myTop3 = getString(it, "myTop3")
                            myTop4 = getString(it, "myTop4")
                            myTopFlag = getString(it, "myTopFlag")
                            myMiddle1 = getString(it, "myMiddle1")
                            myMiddle2 = getString(it, "myMiddle2")
                            myMiddle3 = getString(it, "myMiddle3")
                            myBottom1 = getString(it, "myBottom1")
                            myBottom2 = getString(it, "myBottom2")
                            myBottom3 = getString(it, "myBottom3")
                            productName = getString(it, "productName")
                            productDesc1 = getString(it, "productDesc1")
                            rate = getString(it, "rate")
                            rate2 = getString(it, "rate2")
                            productDesc2 = getString(it, "productDesc2")
                            productDesc3 = getString(it, "productDesc3")
                            rateSign = getString(it, "rateSign")
                            addSign = getString(it, "addSign")
                            rateSign2 = getString(it, "rateSign2")
                            rateDesc = getString(it, "rateDesc")
                            desc1 = getString(it, "desc1")
                            desc1Sign = getString(it, "desc1Sign")
                            desc2 = getString(it, "desc2")
                            desc3 = getString(it, "desc3")
                            desc4 = getString(it, "desc4")
                            desc5 = getString(it, "desc5")
                            desc6 = getString(it, "desc6")
                            desc7 = getString(it, "desc7")
                            productDesc = getString(it, "productDesc")
                            productIcon = getString(it, "productIcon")
                            hotIcon = getString(it, "hotIcon")
                            amt = getString(it, "amt")
                        }
                    }
                    catch (e: JSONException) {
                    }
                }
            }
        }
    }
}
