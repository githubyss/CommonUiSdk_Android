package com.githubyss.mobile.common.ui.recycler_view.three_layer.old

import com.githubyss.mobile.common.kit.util.JsonUtils
import org.json.JSONException
import org.json.JSONObject


class HomeMineModel(json: JSONObject?)  {
    var responseCode: String? = null
    var responseMsg: String? = null
    var responseDataModel: ResponseDataModel? = null

    init {
        try {
            json?.let {
                responseCode = JsonUtils.getString(it, "responseCode")
                responseMsg = JsonUtils.getString(it, "responseMsg")
                val responseData = JsonUtils.getJSONObject(it, "responseData")
                responseDataModel = ResponseDataModel(responseData)
            }
        } catch (e: JSONException) {
        }
    }
}

class ResponseDataModel(json: JSONObject?) {
 var productInfoModels: MutableList<ProductInfo>? = null


    init {
        try {
            json?.let {
                val productInfoList = JsonUtils.getJSONArray(it, "productInfos")
                if (null != productInfoList) {
                    productInfoModels = ArrayList<ProductInfo>()
                    for (i in 0 until productInfoList.length()) {
                        val listItem = productInfoList.optJSONObject(i)
                        val itemModel = ProductInfo(listItem)
                        (productInfoModels as ArrayList<ProductInfo>).add(itemModel)
                    }
                }
            }
        } catch (e: JSONException) {
        }
    }
}

class ProductInfo(json: JSONObject?) {
    var id: String? = null
    var productName: String? = null
    var productIsShow: String? = null

    var iconUrl: String? = null
    var isMore: String? = null

    //-1-不跳转，0-native，1-h5，2-h5-支持小程序分享
    var moreJoinType: String? = null
    var moreJoinUrl: String? = null
    var moreJoinTip: String? = null

    //0-不实名 1-实名
    var moreIsRealName: String? = null

    //0-不登录 1-登录
    var moreIsLogin: String? = null

    var prodRowModels: ArrayList<ArrayList<ProductItem>>? = null

    init {
        try {
            json?.let {
                id = JsonUtils.getString(json, "id")
                productIsShow = JsonUtils.getString(json, "productIsShow")
                productName = JsonUtils.getString(json, "productName")
                moreJoinTip = JsonUtils.getString(json, "moreJoinTip")
                iconUrl = JsonUtils.getString(json, "iconUrl")
                isMore = JsonUtils.getString(json, "isMore")
                moreJoinType = JsonUtils.getString(json, "moreJoinType")
                moreJoinUrl = JsonUtils.getString(json, "moreJoinUrl")
                moreIsRealName = JsonUtils.getString(json, "moreIsRealName")
                moreIsLogin = JsonUtils.getString(json, "moreIsLogin")
                val prodRowsJsonArray = JsonUtils.getJSONArray(json, "prodRows")
                if (null != prodRowsJsonArray) {
                    prodRowModels = ArrayList<ArrayList<ProductItem>>()
                    for (i in 0 until prodRowsJsonArray.length()) {
                        var inProdRowsJsonArray = prodRowsJsonArray.getJSONArray(i)
                        if (null != inProdRowsJsonArray) {
                            var inProdRowModels = ArrayList<ProductItem>()
                            for (j in 0 until inProdRowsJsonArray.length()) {
                                var productItem: ProductItem = ProductItem(inProdRowsJsonArray.getJSONObject(j))
                                inProdRowModels.add(productItem)
                            }
                            prodRowModels!!.add(inProdRowModels)
                        }
                    }
                }
            }
        } catch (e: JSONException) {
        }
    }
}

class ProductItem(json: JSONObject?) {
    var productItemId: String? = null
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
    var isRealName: String? = null
    var isLogin: String? = null
    var joinType: String? = null
    var joinUrl: String? = null
    var item: Item? = null
    var joinTip: String? = null
    var id:String? = null
    constructor() : this(null) {}

    init {
        try {
            json?.let {
                productItemId = JsonUtils.getString(it, "productItemId")
                templateType = JsonUtils.getString(it, "templateType")
                isRealName = JsonUtils.getString(it, "isRealName")
                isLogin = JsonUtils.getString(it, "isLogin")
                joinType = JsonUtils.getString(it, "joinType")
                joinTip = JsonUtils.getString(it, "joinTip")
                id = JsonUtils.getString(it, "id")
                joinUrl = JsonUtils.getString(it, "joinUrl")
                val itemObject = JsonUtils.getJSONObject(it, "items")
                item = Item(itemObject)
            }
        } catch (e: JSONException) {
        }
    }

}

class Item(json: JSONObject?) {
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
                bannerImgUrl = JsonUtils.getString(json, "bannerImgUrl")
                newRate = JsonUtils.getString(json, "newRate")
                newSign = JsonUtils.getString(json, "newSign")
                newRateContent = JsonUtils.getString(json, "newRateContent")
                newProdName = JsonUtils.getString(json, "newProdName")
                newDesc1 = JsonUtils.getString(json, "newDesc1")
                newDesc2 = JsonUtils.getString(json, "newDesc2")
                newDesc3 = JsonUtils.getString(json, "newDesc3")
                newBgImgUrl = JsonUtils.getString(json, "newBgImgUrl")
                newBtnName = JsonUtils.getString(json, "newBtnName")
                wealthProdName = JsonUtils.getString(json, "wealthProdName")
                wealthRate = JsonUtils.getString(json, "wealthRate")
                wealthSign = JsonUtils.getString(json, "wealthSign")
                wealthRateContent = JsonUtils.getString(json, "wealthRateContent")
                wealthDesc1 = JsonUtils.getString(json, "wealthDesc1")
                wealthDesc2 = JsonUtils.getString(json, "wealthDesc2")
                loanProdName = JsonUtils.getString(json, "loanProdName")
                loanRate = JsonUtils.getString(json, "loanRate")
                loanSign = JsonUtils.getString(json, "loanSign")
                loanRateContent = JsonUtils.getString(json, "loanRateContent")
                loanAmount = JsonUtils.getString(json, "loanAmount")
                loanAmountContent = JsonUtils.getString(json, "loanAmountContent")
                loanDesc1 = JsonUtils.getString(json, "loanDesc1")
                loanDesc2 = JsonUtils.getString(json, "loanDesc2")
                loanDesc3 = JsonUtils.getString(json, "loanDesc3")
                loanTag = JsonUtils.getString(json, "loanTag")
                loanStatus = JsonUtils.getString(json, "loanStatus")
                hotEventImgUrl1 = JsonUtils.getString(json, "hotEventImgUrl1")
                hotEventImgUrl2 = JsonUtils.getString(json, "hotEventImgUrl2")
                hotEventImgUrl3 = JsonUtils.getString(json, "hotEventImgUrl3")
                hotEventSkipUrl1 = JsonUtils.getString(json, "hotEventSkipUrl1")
                hotEventSkipUrl2 = JsonUtils.getString(json, "hotEventSkipUrl2")
                hotEventSkipUrl3 = JsonUtils.getString(json, "hotEventSkipUrl3")
                hotEventSkipType1 = JsonUtils.getString(json, "hotEventSkipType1")
                hotEventSkipType2 = JsonUtils.getString(json, "hotEventSkipType2")
                hotEventSkipType3 = JsonUtils.getString(json, "hotEventSkipType3")
                hotProdName = JsonUtils.getString(json, "hotProdName")
                hotButtonDesc = JsonUtils.getString(json, "hotButtonDesc")
                hotRate = JsonUtils.getString(json, "hotRate")
                hotSign = JsonUtils.getString(json, "hotSign")
                hotRateComment = JsonUtils.getString(json, "hotRateComment")
                hotDesc1 = JsonUtils.getString(json, "hotDesc1")
                hotDesc2 = JsonUtils.getString(json, "hotDesc2")
                hotDesc3 = JsonUtils.getString(json, "hotDesc3")
                hotBgImgUrl = JsonUtils.getString(json, "hotBgImgUrl")
                goodProdName = JsonUtils.getString(json, "goodProdName")
                goodRate = JsonUtils.getString(json, "goodRate")
                goodRateSign = JsonUtils.getString(json, "goodRateSign")
                goodRateContent = JsonUtils.getString(json, "goodRateContent")
                goodDesc1 = JsonUtils.getString(json, "goodDesc1")
                goodDesc2 = JsonUtils.getString(json, "goodDesc2")
                financeProdName = JsonUtils.getString(json, "financeProdName")
                financeRate = JsonUtils.getString(json, "financeRate")
                financeRateSign = JsonUtils.getString(json, "financeRateSign")
                amtSign = JsonUtils.getString(json, "amtSign")
                termSign = JsonUtils.getString(json, "termSign")
                financeRateContent = JsonUtils.getString(json, "financeRateContent")
                financeAmount = JsonUtils.getString(json, "financeAmount")
                financeAmountContent = JsonUtils.getString(json, "financeAmountContent")
                financeTerm = JsonUtils.getString(json, "financeTerm")
                financeTermContent = JsonUtils.getString(json, "financeTermContent")
                financeDesc1 = JsonUtils.getString(json, "financeDesc1")
                financeDesc2 = JsonUtils.getString(json, "financeDesc2")
                financeDesc3 = JsonUtils.getString(json, "financeDesc3")
                depositProdName = JsonUtils.getString(json, "depositProdName")
                depositProdName2 = JsonUtils.getString(json, "depositProdName2")
                depositRate = JsonUtils.getString(json, "depositRate")
                depositRateSign = JsonUtils.getString(json, "depositRateSign")
                depositRateContent = JsonUtils.getString(json, "depositRateContent")
                depositDesc1 = JsonUtils.getString(json, "depositDesc1")
                depositDesc2 = JsonUtils.getString(json, "depositDesc2")
                depositDesc3 = JsonUtils.getString(json, "depositDesc3")
                fundProdName = JsonUtils.getString(json, "fundProdName")
                fundImgUrl = JsonUtils.getString(json, "fundImgUrl")
                fundDesc1 = JsonUtils.getString(json, "fundDesc1")
                fundDesc2 = JsonUtils.getString(json, "fundDesc2")
                fundDesc3 = JsonUtils.getString(json, "fundDesc3")
                creditProdName = JsonUtils.getString(json, "creditProdName")
                //                creditAmount = JsonUtils.getString(json, "creditAmount")
                paidAmount = JsonUtils.getString(json, "paidAmount")
                paidAmountContent = JsonUtils.getString(json, "paidAmountContent")
                creditTerm = JsonUtils.getString(json, "creditTerm")
                creditDescA = JsonUtils.getString(json, "creditDescA")
                creditDescC = JsonUtils.getString(json, "creditDescC")
                //                creditRate = JsonUtils.getString(json, "creditRate")
                creditRateSign = JsonUtils.getString(json, "creditRateSign")
                creditTag = JsonUtils.getString(json, "creditTag")
                creditStatus = JsonUtils.getString(json, "creditStatus")
                buttonName = JsonUtils.getString(json, "buttonName")
                buttonName1 = JsonUtils.getString(json, "buttonName1")

                prodName = JsonUtils.getString(json, "prodName")
                top1 = JsonUtils.getString(json, "top1")
                top2 = JsonUtils.getString(json, "top2")
                top3 = JsonUtils.getString(json, "top3")
                topFlag = JsonUtils.getString(json, "topFlag")
                middle1 = JsonUtils.getString(json, "middle1")
                middle2 = JsonUtils.getString(json, "middle2")
                bottom1 = JsonUtils.getString(json, "bottom1")
                bottom2 = JsonUtils.getString(json, "bottom2")
                bottom3 = JsonUtils.getString(json, "bottom3")
                button1 = JsonUtils.getString(json, "button1")
                button2 = JsonUtils.getString(json, "button2")
                myProdName = JsonUtils.getString(json, "myProdName")
                myProdDesc = JsonUtils.getString(json, "myProdDesc")
                myTop1 = JsonUtils.getString(json, "myTop1")
                myTop2 = JsonUtils.getString(json, "myTop2")
                myTop3 = JsonUtils.getString(json, "myTop3")
                myTop4 = JsonUtils.getString(json, "myTop4")
                myTopFlag = JsonUtils.getString(json, "myTopFlag")
                myMiddle1 = JsonUtils.getString(json, "myMiddle1")
                myMiddle2 = JsonUtils.getString(json, "myMiddle2")
                myMiddle3 = JsonUtils.getString(json, "myMiddle3")
                myBottom1 = JsonUtils.getString(json, "myBottom1")
                myBottom2 = JsonUtils.getString(json, "myBottom2")
                myBottom3 = JsonUtils.getString(json, "myBottom3")
                productName = JsonUtils.getString(json, "productName")
                productDesc1 = JsonUtils.getString(json, "productDesc1")
                rate = JsonUtils.getString(json, "rate")
                rate2 = JsonUtils.getString(json, "rate2")
                productDesc2 = JsonUtils.getString(json, "productDesc2")
                productDesc3 = JsonUtils.getString(json, "productDesc3")
                rateSign = JsonUtils.getString(json, "rateSign")
                addSign = JsonUtils.getString(json, "addSign")
                rateSign2 = JsonUtils.getString(json, "rateSign2")
                rateDesc = JsonUtils.getString(json, "rateDesc")
                desc1 = JsonUtils.getString(json, "desc1")
                desc1Sign = JsonUtils.getString(json, "desc1Sign")
                desc2 = JsonUtils.getString(json, "desc2")
                desc3 = JsonUtils.getString(json, "desc3")
                desc4 = JsonUtils.getString(json, "desc4")
                desc5 = JsonUtils.getString(json, "desc5")
                desc6 = JsonUtils.getString(json, "desc6")
                desc7 = JsonUtils.getString(json, "desc7")
                productDesc = JsonUtils.getString(json, "productDesc")
                productIcon = JsonUtils.getString(json, "productIcon")
                hotIcon = JsonUtils.getString(json, "hotIcon")
                amt = JsonUtils.getString(json, "amt")
            }
        } catch (e: JSONException) {
        }
    }
}


