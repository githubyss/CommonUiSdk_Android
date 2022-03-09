package com.githubyss.mobile.common.ui.recycler_view.three_layer.old

import com.githubyss.mobile.common.kit.util.getJSONArrayFromJSONObject
import com.githubyss.mobile.common.kit.util.getJSONObjectFromJSONObject
import com.githubyss.mobile.common.kit.util.getStringFromJSONObject
import org.json.JSONException
import org.json.JSONObject


class HomeMineModel(json: JSONObject?)  {
    var responseCode: String? = null
    var responseMsg: String? = null
    var responseDataModel: ResponseDataModel? = null

    init {
        try {
            json?.let {
                responseCode = getStringFromJSONObject(it, "responseCode")
                responseMsg = getStringFromJSONObject(it, "responseMsg")
                val responseData = getJSONObjectFromJSONObject(it, "responseData")
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
                val productInfoList = getJSONArrayFromJSONObject(it, "productInfos")
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
                id = getStringFromJSONObject(json, "id")
                productIsShow = getStringFromJSONObject(json, "productIsShow")
                productName = getStringFromJSONObject(json, "productName")
                moreJoinTip = getStringFromJSONObject(json, "moreJoinTip")
                iconUrl = getStringFromJSONObject(json, "iconUrl")
                isMore = getStringFromJSONObject(json, "isMore")
                moreJoinType = getStringFromJSONObject(json, "moreJoinType")
                moreJoinUrl = getStringFromJSONObject(json, "moreJoinUrl")
                moreIsRealName = getStringFromJSONObject(json, "moreIsRealName")
                moreIsLogin = getStringFromJSONObject(json, "moreIsLogin")
                val prodRowsJsonArray = getJSONArrayFromJSONObject(json, "prodRows")
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
                productItemId = getStringFromJSONObject(it, "productItemId")
                templateType = getStringFromJSONObject(it, "templateType")
                isRealName = getStringFromJSONObject(it, "isRealName")
                isLogin = getStringFromJSONObject(it, "isLogin")
                joinType = getStringFromJSONObject(it, "joinType")
                joinTip = getStringFromJSONObject(it, "joinTip")
                id = getStringFromJSONObject(it, "id")
                joinUrl = getStringFromJSONObject(it, "joinUrl")
                val itemObject = getJSONObjectFromJSONObject(it, "items")
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
                bannerImgUrl = getStringFromJSONObject(json, "bannerImgUrl")
                newRate = getStringFromJSONObject(json, "newRate")
                newSign = getStringFromJSONObject(json, "newSign")
                newRateContent = getStringFromJSONObject(json, "newRateContent")
                newProdName = getStringFromJSONObject(json, "newProdName")
                newDesc1 = getStringFromJSONObject(json, "newDesc1")
                newDesc2 = getStringFromJSONObject(json, "newDesc2")
                newDesc3 = getStringFromJSONObject(json, "newDesc3")
                newBgImgUrl = getStringFromJSONObject(json, "newBgImgUrl")
                newBtnName = getStringFromJSONObject(json, "newBtnName")
                wealthProdName = getStringFromJSONObject(json, "wealthProdName")
                wealthRate = getStringFromJSONObject(json, "wealthRate")
                wealthSign = getStringFromJSONObject(json, "wealthSign")
                wealthRateContent = getStringFromJSONObject(json, "wealthRateContent")
                wealthDesc1 = getStringFromJSONObject(json, "wealthDesc1")
                wealthDesc2 = getStringFromJSONObject(json, "wealthDesc2")
                loanProdName = getStringFromJSONObject(json, "loanProdName")
                loanRate = getStringFromJSONObject(json, "loanRate")
                loanSign = getStringFromJSONObject(json, "loanSign")
                loanRateContent = getStringFromJSONObject(json, "loanRateContent")
                loanAmount = getStringFromJSONObject(json, "loanAmount")
                loanAmountContent = getStringFromJSONObject(json, "loanAmountContent")
                loanDesc1 = getStringFromJSONObject(json, "loanDesc1")
                loanDesc2 = getStringFromJSONObject(json, "loanDesc2")
                loanDesc3 = getStringFromJSONObject(json, "loanDesc3")
                loanTag = getStringFromJSONObject(json, "loanTag")
                loanStatus = getStringFromJSONObject(json, "loanStatus")
                hotEventImgUrl1 = getStringFromJSONObject(json, "hotEventImgUrl1")
                hotEventImgUrl2 = getStringFromJSONObject(json, "hotEventImgUrl2")
                hotEventImgUrl3 = getStringFromJSONObject(json, "hotEventImgUrl3")
                hotEventSkipUrl1 = getStringFromJSONObject(json, "hotEventSkipUrl1")
                hotEventSkipUrl2 = getStringFromJSONObject(json, "hotEventSkipUrl2")
                hotEventSkipUrl3 = getStringFromJSONObject(json, "hotEventSkipUrl3")
                hotEventSkipType1 = getStringFromJSONObject(json, "hotEventSkipType1")
                hotEventSkipType2 = getStringFromJSONObject(json, "hotEventSkipType2")
                hotEventSkipType3 = getStringFromJSONObject(json, "hotEventSkipType3")
                hotProdName = getStringFromJSONObject(json, "hotProdName")
                hotButtonDesc = getStringFromJSONObject(json, "hotButtonDesc")
                hotRate = getStringFromJSONObject(json, "hotRate")
                hotSign = getStringFromJSONObject(json, "hotSign")
                hotRateComment = getStringFromJSONObject(json, "hotRateComment")
                hotDesc1 = getStringFromJSONObject(json, "hotDesc1")
                hotDesc2 = getStringFromJSONObject(json, "hotDesc2")
                hotDesc3 = getStringFromJSONObject(json, "hotDesc3")
                hotBgImgUrl = getStringFromJSONObject(json, "hotBgImgUrl")
                goodProdName = getStringFromJSONObject(json, "goodProdName")
                goodRate = getStringFromJSONObject(json, "goodRate")
                goodRateSign = getStringFromJSONObject(json, "goodRateSign")
                goodRateContent = getStringFromJSONObject(json, "goodRateContent")
                goodDesc1 = getStringFromJSONObject(json, "goodDesc1")
                goodDesc2 = getStringFromJSONObject(json, "goodDesc2")
                financeProdName = getStringFromJSONObject(json, "financeProdName")
                financeRate = getStringFromJSONObject(json, "financeRate")
                financeRateSign = getStringFromJSONObject(json, "financeRateSign")
                amtSign = getStringFromJSONObject(json, "amtSign")
                termSign = getStringFromJSONObject(json, "termSign")
                financeRateContent = getStringFromJSONObject(json, "financeRateContent")
                financeAmount = getStringFromJSONObject(json, "financeAmount")
                financeAmountContent = getStringFromJSONObject(json, "financeAmountContent")
                financeTerm = getStringFromJSONObject(json, "financeTerm")
                financeTermContent = getStringFromJSONObject(json, "financeTermContent")
                financeDesc1 = getStringFromJSONObject(json, "financeDesc1")
                financeDesc2 = getStringFromJSONObject(json, "financeDesc2")
                financeDesc3 = getStringFromJSONObject(json, "financeDesc3")
                depositProdName = getStringFromJSONObject(json, "depositProdName")
                depositProdName2 = getStringFromJSONObject(json, "depositProdName2")
                depositRate = getStringFromJSONObject(json, "depositRate")
                depositRateSign = getStringFromJSONObject(json, "depositRateSign")
                depositRateContent = getStringFromJSONObject(json, "depositRateContent")
                depositDesc1 = getStringFromJSONObject(json, "depositDesc1")
                depositDesc2 = getStringFromJSONObject(json, "depositDesc2")
                depositDesc3 = getStringFromJSONObject(json, "depositDesc3")
                fundProdName = getStringFromJSONObject(json, "fundProdName")
                fundImgUrl = getStringFromJSONObject(json, "fundImgUrl")
                fundDesc1 = getStringFromJSONObject(json, "fundDesc1")
                fundDesc2 = getStringFromJSONObject(json, "fundDesc2")
                fundDesc3 = getStringFromJSONObject(json, "fundDesc3")
                creditProdName = getStringFromJSONObject(json, "creditProdName")
                //                creditAmount = getStringFromJSONObject(json, "creditAmount")
                paidAmount = getStringFromJSONObject(json, "paidAmount")
                paidAmountContent = getStringFromJSONObject(json, "paidAmountContent")
                creditTerm = getStringFromJSONObject(json, "creditTerm")
                creditDescA = getStringFromJSONObject(json, "creditDescA")
                creditDescC = getStringFromJSONObject(json, "creditDescC")
                //                creditRate = getStringFromJSONObject(json, "creditRate")
                creditRateSign = getStringFromJSONObject(json, "creditRateSign")
                creditTag = getStringFromJSONObject(json, "creditTag")
                creditStatus = getStringFromJSONObject(json, "creditStatus")
                buttonName = getStringFromJSONObject(json, "buttonName")
                buttonName1 = getStringFromJSONObject(json, "buttonName1")

                prodName = getStringFromJSONObject(json, "prodName")
                top1 = getStringFromJSONObject(json, "top1")
                top2 = getStringFromJSONObject(json, "top2")
                top3 = getStringFromJSONObject(json, "top3")
                topFlag = getStringFromJSONObject(json, "topFlag")
                middle1 = getStringFromJSONObject(json, "middle1")
                middle2 = getStringFromJSONObject(json, "middle2")
                bottom1 = getStringFromJSONObject(json, "bottom1")
                bottom2 = getStringFromJSONObject(json, "bottom2")
                bottom3 = getStringFromJSONObject(json, "bottom3")
                button1 = getStringFromJSONObject(json, "button1")
                button2 = getStringFromJSONObject(json, "button2")
                myProdName = getStringFromJSONObject(json, "myProdName")
                myProdDesc = getStringFromJSONObject(json, "myProdDesc")
                myTop1 = getStringFromJSONObject(json, "myTop1")
                myTop2 = getStringFromJSONObject(json, "myTop2")
                myTop3 = getStringFromJSONObject(json, "myTop3")
                myTop4 = getStringFromJSONObject(json, "myTop4")
                myTopFlag = getStringFromJSONObject(json, "myTopFlag")
                myMiddle1 = getStringFromJSONObject(json, "myMiddle1")
                myMiddle2 = getStringFromJSONObject(json, "myMiddle2")
                myMiddle3 = getStringFromJSONObject(json, "myMiddle3")
                myBottom1 = getStringFromJSONObject(json, "myBottom1")
                myBottom2 = getStringFromJSONObject(json, "myBottom2")
                myBottom3 = getStringFromJSONObject(json, "myBottom3")
                productName = getStringFromJSONObject(json, "productName")
                productDesc1 = getStringFromJSONObject(json, "productDesc1")
                rate = getStringFromJSONObject(json, "rate")
                rate2 = getStringFromJSONObject(json, "rate2")
                productDesc2 = getStringFromJSONObject(json, "productDesc2")
                productDesc3 = getStringFromJSONObject(json, "productDesc3")
                rateSign = getStringFromJSONObject(json, "rateSign")
                addSign = getStringFromJSONObject(json, "addSign")
                rateSign2 = getStringFromJSONObject(json, "rateSign2")
                rateDesc = getStringFromJSONObject(json, "rateDesc")
                desc1 = getStringFromJSONObject(json, "desc1")
                desc1Sign = getStringFromJSONObject(json, "desc1Sign")
                desc2 = getStringFromJSONObject(json, "desc2")
                desc3 = getStringFromJSONObject(json, "desc3")
                desc4 = getStringFromJSONObject(json, "desc4")
                desc5 = getStringFromJSONObject(json, "desc5")
                desc6 = getStringFromJSONObject(json, "desc6")
                desc7 = getStringFromJSONObject(json, "desc7")
                productDesc = getStringFromJSONObject(json, "productDesc")
                productIcon = getStringFromJSONObject(json, "productIcon")
                hotIcon = getStringFromJSONObject(json, "hotIcon")
                amt = getStringFromJSONObject(json, "amt")
            }
        } catch (e: JSONException) {
        }
    }
}


