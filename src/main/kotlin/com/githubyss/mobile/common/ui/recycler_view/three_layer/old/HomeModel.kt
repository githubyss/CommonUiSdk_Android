package com.githubyss.mobile.common.ui.recycler_view.three_layer.old

import com.githubyss.mobile.common.kit.util.getJSONArray
import com.githubyss.mobile.common.kit.util.getJSONObject
import com.githubyss.mobile.common.kit.util.getString
import org.json.JSONException
import org.json.JSONObject


class HomeMineModel(json: JSONObject?)  {
    var responseCode: String? = null
    var responseMsg: String? = null
    var responseDataModel: ResponseDataModel? = null

    init {
        try {
            json?.let {
                responseCode = getString(it, "responseCode")
                responseMsg = getString(it, "responseMsg")
                val responseData = getJSONObject(it, "responseData")
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
                val productInfoList = getJSONArray(it, "productInfos")
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
                id = getString(json, "id")
                productIsShow = getString(json, "productIsShow")
                productName = getString(json, "productName")
                moreJoinTip = getString(json, "moreJoinTip")
                iconUrl = getString(json, "iconUrl")
                isMore = getString(json, "isMore")
                moreJoinType = getString(json, "moreJoinType")
                moreJoinUrl = getString(json, "moreJoinUrl")
                moreIsRealName = getString(json, "moreIsRealName")
                moreIsLogin = getString(json, "moreIsLogin")
                val prodRowsJsonArray = getJSONArray(json, "prodRows")
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
                productItemId = getString(it, "productItemId")
                templateType = getString(it, "templateType")
                isRealName = getString(it, "isRealName")
                isLogin = getString(it, "isLogin")
                joinType = getString(it, "joinType")
                joinTip = getString(it, "joinTip")
                id = getString(it, "id")
                joinUrl = getString(it, "joinUrl")
                val itemObject = getJSONObject(it, "items")
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
                bannerImgUrl = getString(json, "bannerImgUrl")
                newRate = getString(json, "newRate")
                newSign = getString(json, "newSign")
                newRateContent = getString(json, "newRateContent")
                newProdName = getString(json, "newProdName")
                newDesc1 = getString(json, "newDesc1")
                newDesc2 = getString(json, "newDesc2")
                newDesc3 = getString(json, "newDesc3")
                newBgImgUrl = getString(json, "newBgImgUrl")
                newBtnName = getString(json, "newBtnName")
                wealthProdName = getString(json, "wealthProdName")
                wealthRate = getString(json, "wealthRate")
                wealthSign = getString(json, "wealthSign")
                wealthRateContent = getString(json, "wealthRateContent")
                wealthDesc1 = getString(json, "wealthDesc1")
                wealthDesc2 = getString(json, "wealthDesc2")
                loanProdName = getString(json, "loanProdName")
                loanRate = getString(json, "loanRate")
                loanSign = getString(json, "loanSign")
                loanRateContent = getString(json, "loanRateContent")
                loanAmount = getString(json, "loanAmount")
                loanAmountContent = getString(json, "loanAmountContent")
                loanDesc1 = getString(json, "loanDesc1")
                loanDesc2 = getString(json, "loanDesc2")
                loanDesc3 = getString(json, "loanDesc3")
                loanTag = getString(json, "loanTag")
                loanStatus = getString(json, "loanStatus")
                hotEventImgUrl1 = getString(json, "hotEventImgUrl1")
                hotEventImgUrl2 = getString(json, "hotEventImgUrl2")
                hotEventImgUrl3 = getString(json, "hotEventImgUrl3")
                hotEventSkipUrl1 = getString(json, "hotEventSkipUrl1")
                hotEventSkipUrl2 = getString(json, "hotEventSkipUrl2")
                hotEventSkipUrl3 = getString(json, "hotEventSkipUrl3")
                hotEventSkipType1 = getString(json, "hotEventSkipType1")
                hotEventSkipType2 = getString(json, "hotEventSkipType2")
                hotEventSkipType3 = getString(json, "hotEventSkipType3")
                hotProdName = getString(json, "hotProdName")
                hotButtonDesc = getString(json, "hotButtonDesc")
                hotRate = getString(json, "hotRate")
                hotSign = getString(json, "hotSign")
                hotRateComment = getString(json, "hotRateComment")
                hotDesc1 = getString(json, "hotDesc1")
                hotDesc2 = getString(json, "hotDesc2")
                hotDesc3 = getString(json, "hotDesc3")
                hotBgImgUrl = getString(json, "hotBgImgUrl")
                goodProdName = getString(json, "goodProdName")
                goodRate = getString(json, "goodRate")
                goodRateSign = getString(json, "goodRateSign")
                goodRateContent = getString(json, "goodRateContent")
                goodDesc1 = getString(json, "goodDesc1")
                goodDesc2 = getString(json, "goodDesc2")
                financeProdName = getString(json, "financeProdName")
                financeRate = getString(json, "financeRate")
                financeRateSign = getString(json, "financeRateSign")
                amtSign = getString(json, "amtSign")
                termSign = getString(json, "termSign")
                financeRateContent = getString(json, "financeRateContent")
                financeAmount = getString(json, "financeAmount")
                financeAmountContent = getString(json, "financeAmountContent")
                financeTerm = getString(json, "financeTerm")
                financeTermContent = getString(json, "financeTermContent")
                financeDesc1 = getString(json, "financeDesc1")
                financeDesc2 = getString(json, "financeDesc2")
                financeDesc3 = getString(json, "financeDesc3")
                depositProdName = getString(json, "depositProdName")
                depositProdName2 = getString(json, "depositProdName2")
                depositRate = getString(json, "depositRate")
                depositRateSign = getString(json, "depositRateSign")
                depositRateContent = getString(json, "depositRateContent")
                depositDesc1 = getString(json, "depositDesc1")
                depositDesc2 = getString(json, "depositDesc2")
                depositDesc3 = getString(json, "depositDesc3")
                fundProdName = getString(json, "fundProdName")
                fundImgUrl = getString(json, "fundImgUrl")
                fundDesc1 = getString(json, "fundDesc1")
                fundDesc2 = getString(json, "fundDesc2")
                fundDesc3 = getString(json, "fundDesc3")
                creditProdName = getString(json, "creditProdName")
                //                creditAmount = getString(json, "creditAmount")
                paidAmount = getString(json, "paidAmount")
                paidAmountContent = getString(json, "paidAmountContent")
                creditTerm = getString(json, "creditTerm")
                creditDescA = getString(json, "creditDescA")
                creditDescC = getString(json, "creditDescC")
                //                creditRate = getString(json, "creditRate")
                creditRateSign = getString(json, "creditRateSign")
                creditTag = getString(json, "creditTag")
                creditStatus = getString(json, "creditStatus")
                buttonName = getString(json, "buttonName")
                buttonName1 = getString(json, "buttonName1")

                prodName = getString(json, "prodName")
                top1 = getString(json, "top1")
                top2 = getString(json, "top2")
                top3 = getString(json, "top3")
                topFlag = getString(json, "topFlag")
                middle1 = getString(json, "middle1")
                middle2 = getString(json, "middle2")
                bottom1 = getString(json, "bottom1")
                bottom2 = getString(json, "bottom2")
                bottom3 = getString(json, "bottom3")
                button1 = getString(json, "button1")
                button2 = getString(json, "button2")
                myProdName = getString(json, "myProdName")
                myProdDesc = getString(json, "myProdDesc")
                myTop1 = getString(json, "myTop1")
                myTop2 = getString(json, "myTop2")
                myTop3 = getString(json, "myTop3")
                myTop4 = getString(json, "myTop4")
                myTopFlag = getString(json, "myTopFlag")
                myMiddle1 = getString(json, "myMiddle1")
                myMiddle2 = getString(json, "myMiddle2")
                myMiddle3 = getString(json, "myMiddle3")
                myBottom1 = getString(json, "myBottom1")
                myBottom2 = getString(json, "myBottom2")
                myBottom3 = getString(json, "myBottom3")
                productName = getString(json, "productName")
                productDesc1 = getString(json, "productDesc1")
                rate = getString(json, "rate")
                rate2 = getString(json, "rate2")
                productDesc2 = getString(json, "productDesc2")
                productDesc3 = getString(json, "productDesc3")
                rateSign = getString(json, "rateSign")
                addSign = getString(json, "addSign")
                rateSign2 = getString(json, "rateSign2")
                rateDesc = getString(json, "rateDesc")
                desc1 = getString(json, "desc1")
                desc1Sign = getString(json, "desc1Sign")
                desc2 = getString(json, "desc2")
                desc3 = getString(json, "desc3")
                desc4 = getString(json, "desc4")
                desc5 = getString(json, "desc5")
                desc6 = getString(json, "desc6")
                desc7 = getString(json, "desc7")
                productDesc = getString(json, "productDesc")
                productIcon = getString(json, "productIcon")
                hotIcon = getString(json, "hotIcon")
                amt = getString(json, "amt")
            }
        } catch (e: JSONException) {
        }
    }
}


