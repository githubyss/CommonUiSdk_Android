package com.githubyss.mobile.common.ui.alone.page.speech_recognition.manager

import com.githubyss.mobile.common.ui.alone.page.speech_recognition.manager.VoiceIntentManager


/**
 * 由于产品去科大讯飞后台配置需要大量的工作量，为了达到目的，加上没有服务端支持，客户端自己去做关键词拦截，看起来有点二
 */
object VoiceLocalMatch {
    fun getActionCode(command: String): Int {
        if (command.contains("转账") || command.contains("转个账") || command.contains("到卡") || command.contains("到账户") || command.contains("易付宝")) {
            return VoiceIntentManager.ACTION_TRANSFER
        }
        else if (command.contains("苏宁卡") || command.contains("心意卡") || command.contains("电子卡") || command.contains("经典卡") || command.contains("洋狮卡")) {
            return VoiceIntentManager.ACTION_SUNING_CARD
        }
        else if (command.contains("申请信用卡") || command.contains("办卡")) {
            return VoiceIntentManager.ACTION_APPLY_CREDITCARD
        }
        else if (command.contains("还款") || command.contains("信用卡") || command.contains("还卡债")) {
            return VoiceIntentManager.ACTION_REPAYMENT
        }
        else if (command.contains("娱乐")) {
            return VoiceIntentManager.ACTION_ENTERTAINMENT_CHARGE
        }
        else if (command.contains("充话费") || command.contains("充值") || command.contains("本机")) {
            return VoiceIntentManager.ACTION_PHONE_CHARGE
        }
        else if (command.contains("流量")) {
            return VoiceIntentManager.ACTION_PHONE_DATA_CHARGE
        }
        else if (command.contains("停车") || command.contains("车费")) {
            return VoiceIntentManager.ACTION_PARKING_PAYMENT
        }
        else if (command.contains("缴费") || command.contains("水费") || command.contains("电费") || command.contains("燃气费")) {
            return VoiceIntentManager.ACTION_LIFEPAYMENT
        }
        else if (command.contains("账单")) {
            return VoiceIntentManager.ACTION_BILL
        }
        else if (command.contains("电影") || command.contains("上映") || command.contains("热映")) {
            return VoiceIntentManager.ACTION_GO_MOVIE
        }
        else if (command.contains("理财") || command.contains("投资") || command.contains("产品") || command.contains("赚钱")) {
            return VoiceIntentManager.ACTION_MANAGE_MONEY
        }
        else if (command.contains("美食") || command.contains("推荐") || command.contains("甜点") || command.contains("聚餐") || command.contains("西餐") || command.contains("下午茶") || command.contains("吃") || command.contains("团购") || command.contains("吃") || command.contains("KTV") || command.contains("菜") || command.contains("吃")) {
            return VoiceIntentManager.ACNTION_GO_PLAY
        }
        else if (command.contains("新闻") || command.contains("新鲜事") || command.contains("今日") || command.contains("股市") || command.contains("资讯")) {
            return VoiceIntentManager.ACTION_GO_NEWS
        }
        else if (command.contains("零钱") || command.contains("多少钱") || command.contains("盈利") || command.contains("收益")) {
            return VoiceIntentManager.ACTION_SMALL_CHANGE
        }
        else if (command.contains("汽车票")) {
            return VoiceIntentManager.ACTION_BUS_TICKETS
        }
        else if (command.contains("票") || command.contains("火车")) {
            return VoiceIntentManager.ACTION_TRAIN_TICKETS
        }
        else if (command.contains("缴学费") || command.contains("大学") || command.contains("学院") || command.contains("中学") || command.contains("小学") || command.contains("幼儿园") || command.contains("学校")) {
            return VoiceIntentManager.ACTION_SCHOOL_PAYMENT
        }
        else if (command.contains("门店") || command.contains("财富中心")) {
            return VoiceIntentManager.ACTION_FORTUNE_CENTER
        }
        else if (command.contains("保险")) {
            return VoiceIntentManager.ACTION_INSURANCE
        }
        else if (command.contains("易购") || command.contains("洗衣机") || command.contains("冰箱") || command.contains("买") || command.contains("电器") || command.contains("苏宁")) {
            return VoiceIntentManager.ACTION_SUNING_COM
        }
        else if (command.contains("一卡通") || command.contains("公交卡") || command.contains("市民卡") || command.contains("羊城通")) {
            return VoiceIntentManager.ACTION_ONE_CARD_PASS
        }
        else if (command.contains("校园")) {
            return VoiceIntentManager.ACTION_SCHOOL_ONE_CARD
        }
        else if (command.contains("汽车")) {
            return VoiceIntentManager.ACTION_SHARE_CAR
        }
        else if (command.contains("洗车") || command.contains("上门")) {
            return VoiceIntentManager.ACTION_CAR_WASH
        }
        else if (command.contains("单车") || command.contains("骑车")) {
            return VoiceIntentManager.ACTION_XIAOMING_BUYCLE
        }
        else if (command.contains("任性贷") || command.contains("贷款")) {
            return VoiceIntentManager.ACTION_RXD
        }
        else if (command.contains("任性")) {
            return VoiceIntentManager.ACTION_WILLFUL_PAYMENT
        }
        else if (command.contains("众筹")) {
            return VoiceIntentManager.ACTION_CROWD_FUNDING
        }
        else if (command.contains("在线客服")) {
            return VoiceIntentManager.ACTION_ONLINE_SERVICE
        }
        else if (command.contains("拼手气") || command.contains("红包")) {
            return VoiceIntentManager.ACTION_REDPACKETS
        }
        else if (command.contains("超市") || command.contains("购物")) {
            return VoiceIntentManager.ACTION_SUPER_MARKET
        }
        else if (command.contains("pptv") || command.contains("聚力视频")) {
            return VoiceIntentManager.ACTION_PPTV
        }
        else if (command.contains("挂号") || command.contains("预约")) {
            return VoiceIntentManager.ACTION_REGISTERD
        }
        else if (command.contains("加油卡") || command.contains("中石化") || command.contains("中石油") || command.contains("圈存")) {
            return VoiceIntentManager.ACTION_FUEL_CARD
        }
        else if (command.contains("帮客")) {
            return VoiceIntentManager.ACTION_SUNING_HELPER
        }
        else if (command.contains("医院")) {
            return VoiceIntentManager.ACTION_NEARBY_HOSPITAL
        }
        else if (command.contains("导诊")) {
            return VoiceIntentManager.ACTION_HOSPITAL_GUIDE
        }
        else if (command.contains("基金")) {
            return VoiceIntentManager.ACTION_FOUNDATION
        }
        return VoiceIntentManager.ACTION_DEFAULT
    }
}