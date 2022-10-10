package com.githubyss.common.ui.app.page.speech_recognition.manager;

import android.os.Handler;
import android.util.SparseArray;

import androidx.fragment.app.FragmentActivity;


/**
 * 因为不同的业务走不同的跳转，有的走页面路由，有的走不了需要手动处理，所以用Runable设计，并没有采用key统一处理
 */

public class VoiceIntentManager {

    public static final int ACTION_DEFAULT = 00000;

    public static final int ACTION_TRANSFER = 01001;
    public static final int ACTION_REPAYMENT = 01002;
    public static final int ACTION_PHONE_CHARGE = 01003;
    public static final int ACTION_PHONE_DATA_CHARGE = 01004;
    public static final int ACTION_LIFEPAYMENT = 01005;
    public static final int ACTION_GO_MOVIE = 01006;
    public static final int ACNTION_GO_PLAY = 01007;
    public static final int ACTION_MANAGE_MONEY = 010010;
    public static final int ACTION_GO_NEWS = 01011;
    public static final int ACTION_SMALL_CHANGE = 01012;
    public static final int ACTION_TRAIN_TICKETS = 01013;
    public static final int ACTION_BUS_TICKETS = 01014;
    public static final int ACTION_SCHOOL_PAYMENT = 01015;
    public static final int ACTION_FORTUNE_CENTER = 01016;
    public static final int ACTION_SUNING_COM = 01017;
    public static final int ACTION_BILL = 01020;
    public static final int ACTION_ONE_CARD_PASS = 01021;
    public static final int ACTION_SHARE_CAR = 01022;
    public static final int ACTION_CAR_WASH = 01023;
    public static final int ACTION_XIAOMING_BUYCLE = 01024;
    public static final int ACTION_WILLFUL_PAYMENT = 01025;
    public static final int ACTION_INSURANCE = 01026;
    public static final int ACTION_CROWD_FUNDING = 01027;
    public static final int ACTION_SUNING_CARD = 01030;
    public static final int ACTION_ONLINE_SERVICE = 01031;
    public static final int ACTION_REDPACKETS = 01032;
    public static final int ACTION_SUPER_MARKET = 01033;
    public static final int ACTION_PPTV = 01034;
    public static final int ACTION_REGISTERD = 01035;
    public static final int ACTION_APPLY_CREDITCARD = 01036;
    public static final int ACTION_ENTERTAINMENT_CHARGE = 01037;
    public static final int ACTION_PARKING_PAYMENT = 01040;
    public static final int ACTION_FUEL_CARD = 01041;
    public static final int ACTION_SUNING_HELPER = 01042;
    public static final int ACTION_NEARBY_HOSPITAL = 01043;
    public static final int ACTION_HOSPITAL_GUIDE = 01044;
    public static final int ACTION_SCHOOL_ONE_CARD = 01045;
    public static final int ACTION_FOUNDATION = 01046;
    public static final int ACTION_RXD = 01047;

    private static final VoiceState defaultState = new VoiceState("未知命令");

    private final SparseArray<VoiceState> actionMap = new SparseArray<VoiceState>();
    private FragmentActivity activity;
    private int intentCode;

    public void initialActionMap() {
        actionMap.put(ACTION_TRANSFER, new VoiceState("zz", "转账", "正在为您打开转账页面", defaultAction));
        actionMap.put(ACTION_GO_NEWS, new VoiceState("新闻资讯", "为您打开资讯", doOpenNews));
        actionMap.put(ACTION_REPAYMENT, new VoiceState("xykhk", "信用卡还款", "正在为您打开信用卡还款", defaultAction));
        actionMap.put(ACTION_PHONE_CHARGE, new VoiceState("sjcz", "手机充值", "正在为您打开手机充值", defaultAction));
        actionMap.put(ACTION_PHONE_DATA_CHARGE, new VoiceState("sjcz", "流量充值", "正在为您打开流量充值", defaultAction));
        actionMap.put(ACTION_LIFEPAYMENT, new VoiceState("shjf", "生活缴费", "正在为您打开生活缴费", defaultAction));
        actionMap.put(ACTION_GO_MOVIE, new VoiceState("dyp", "电影票", "正在为您打开电影票", defaultAction));
        actionMap.put(ACNTION_GO_PLAY, new VoiceState("chwl", "吃喝玩乐", "为您打开吃喝玩乐", defaultAction));
        actionMap.put(ACTION_MANAGE_MONEY, new VoiceState("lcx", "理财", "为您打开理财", defaultAction));
        actionMap.put(ACTION_SMALL_CHANGE, new VoiceState("lqb", "零钱宝", "为您打开零钱宝", defaultAction));
        actionMap.put(ACTION_TRAIN_TICKETS, new VoiceState("traintickets", "火车票", "为您打开火车票", defaultAction));
        actionMap.put(ACTION_BUS_TICKETS, new VoiceState("qcp", "汽车票", "为您打开汽车票", defaultAction));
        actionMap.put(ACTION_SCHOOL_PAYMENT, new VoiceState("jyjf", "缴学费", "为您打开缴学费", defaultAction));
        actionMap.put(ACTION_FORTUNE_CENTER, new VoiceState("cfzx", "财富中心", "为您打开财富中心", defaultAction));
        actionMap.put(ACTION_SUNING_COM, new VoiceState("snyg", "苏宁易购", "为您打开苏宁易购", defaultAction));
        actionMap.put(ACTION_BILL, new VoiceState("账单", "为您打开账单", doBillPayment));
        actionMap.put(ACTION_ONE_CARD_PASS, new VoiceState("csykt", "一卡通", "正在为您打开城市一卡通", defaultAction));
        actionMap.put(ACTION_SHARE_CAR, new VoiceState("gxqc", "共享汽车", "为您打开共享汽车", defaultAction));
        actionMap.put(ACTION_CAR_WASH, new VoiceState("smxc", "上门洗车", "为您打开上门洗车", defaultAction));
        actionMap.put(ACTION_XIAOMING_BUYCLE, new VoiceState("xmbike", "小鸣单车", "为您打开小鸣单车", defaultAction));
        actionMap.put(ACTION_WILLFUL_PAYMENT, new VoiceState("rxf", "任性付", "为您打开任性付", defaultAction));
        actionMap.put(ACTION_INSURANCE, new VoiceState("bx", "保险", "为您打开保险", defaultAction));
        actionMap.put(ACTION_CROWD_FUNDING, new VoiceState("zc", "众筹", "为您打开众筹", defaultAction));
        actionMap.put(ACTION_SUNING_CARD, new VoiceState("snk", "苏宁卡", "为您打开苏宁卡", defaultAction));
        actionMap.put(ACTION_ONLINE_SERVICE, new VoiceState("customerService", "在线客服", "为您打开在线客服", defaultAction));
        actionMap.put(ACTION_REDPACKETS, new VoiceState("hongbao", "拼手气红包", "为您打开拼手气红包", defaultAction));
        actionMap.put(ACTION_SUPER_MARKET, new VoiceState("SNCS", "苏宁超市", "为您打开苏宁超市", defaultAction));
        actionMap.put(ACTION_PPTV, new VoiceState("JLSP", "聚力视频", "为您打开聚力视频", defaultAction));
        actionMap.put(ACTION_REGISTERD, new VoiceState("yygh", "预约挂号", "为您打开预约挂号", defaultAction));
        actionMap.put(ACTION_APPLY_CREDITCARD, new VoiceState("xyksq", "申请信用卡", "为您打开申请信用卡", defaultAction));
        actionMap.put(ACTION_ENTERTAINMENT_CHARGE, new VoiceState("ylcz", "娱乐充值", "为您打开娱乐充值", defaultAction));
        actionMap.put(ACTION_PARKING_PAYMENT, new VoiceState("tcjf", "停车缴费", "为您打开停车缴费", defaultAction));
        actionMap.put(ACTION_FUEL_CARD, new VoiceState("petrolcard", "加油卡充值", "为您打开加油卡充值", defaultAction));
        actionMap.put(ACTION_SUNING_HELPER, new VoiceState("SNBK", "苏宁帮客", "为您打开苏宁帮客", defaultAction));
        actionMap.put(ACTION_NEARBY_HOSPITAL, new VoiceState("fjyy", "附近医院", "为您打开附近医院", defaultAction));
        actionMap.put(ACTION_HOSPITAL_GUIDE, new VoiceState("zndz", "智能导诊", "为您打开智能导诊", defaultAction));
        actionMap.put(ACTION_SCHOOL_ONE_CARD, new VoiceState("xyykt", "校园卡", "为您打开校园一卡通", defaultAction));
        actionMap.put(ACTION_FOUNDATION, new VoiceState("jijin", "基金", "为您打开基金", defaultAction));
        actionMap.put(ACTION_RXD, new VoiceState("rxd", "任性贷", "为您打开任性贷", defaultAction));
    }

    public VoiceIntentManager(FragmentActivity activity, int stateCode) {
        this.activity = activity;
        this.intentCode = stateCode;
        initialActionMap();
    }

    public VoiceState getState() {
        VoiceToTarget.getInstance().refreshIcon();
        VoiceState voiceState = actionMap.get(intentCode);
        if (voiceState == null) {
            return defaultState;
        }

        if (voiceState.getVoiceKey() == null) {
            //固定打开逻辑
            return voiceState;
        }
        else if (voiceState.getVoiceKey() != null && VoiceToTarget.getInstance().checkAllIConHasKey(voiceState.getVoiceKey())) {
            //有可能后台尚未获取到
            return voiceState;
        }
        else {
            // AppHomePresenter.AppHomeIconsQueryCallBack allIconCB = new AppHomePresenter.AppHomeIconsQueryCallBack(){
            //     @Override
            //     public void queryFail(String code, String failMsg) {
            //         VoiceToTarget.getInstance().refreshIcon();
            //     }
            //
            //     @Override
            //     public void querySuccess(EPABean bean) {
            //         VoiceToTarget.getInstance().refreshIcon();
            //     }
            // };
            // AppHomePresenter homePresenter = new AppHomePresenter();
            // homePresenter.sendAppHomeIconsReq(allIconCB);
            return defaultState;
        }
    }

    public String getAnswer() {
        return getState().getAnswerText();
    }

    public void doAction() {
        getState().doAction(new Handler(activity.getMainLooper()));
    }


    /**
     * 默认Action
     */
    private Runnable defaultAction = new Runnable() {
        @Override
        public void run() {
            VoiceToTarget.getInstance().toApp(activity, "com.suning.jr://?key=" + getState().getVoiceKey());
        }
    };


    /**
     * 资讯
     */
    private Runnable doOpenNews = new Runnable() {
        @Override
        public void run() {
            //资讯页目前为tab，不能直接跳转

        }
    };

    /**
     * 账单
     */
    private Runnable doBillPayment = new Runnable() {
        @Override
        public void run() {
            // Intent intent = new Intent(activity,MyBillsMainActivity.class);
            // activity.startActivity(intent);
        }
    };

}
