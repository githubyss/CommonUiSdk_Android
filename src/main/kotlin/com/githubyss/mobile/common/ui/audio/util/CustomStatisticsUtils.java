package com.githubyss.mobile.common.ui.audio.util;// package com.ace.accessibility.automatic.audio.util;
//
// import com.suning.mobile.epa.statistic.CustomStatisticsProxy;
//
// import java.util.HashMap;
// import java.util.Map;
//
// /**
//  * 88396251
//  * 2018/6/29
//  * 音频埋点工具类
//  */
//
// public class CustomStatisticsUtils {
//     private static final String EVENTNAME="comclick";//事件类型
//     private static final String PAGEID="M5Gd";//页面id
//     private static final String MODID_ACTIVITY="DrzTA";//音频播放页
//     private static final String MODID_FLOAT="XSZSb";//音频弹窗
//     private static final String MODID_WGIB="wGIb";//音频悬浮
//     public static final String ELEID_BACK="videoback";//音频播放页-返回按钮
//     public static final String ELEID_SHARE="videoshare";//音频播放页-分享按钮
//     public static final String ELEID_LAST="videolast";//音频播放页-上一首
//     public static final String ELEID_NEXT="videonext";//音频播放页-下一首
//     public static final String ELEID_PAUSE="videopause";//音频播放页-暂停
//     public static final String ELEID_PALY="videoplay";//音频播放页-播放
//     public static final String ELEID_BACKGROUND="videobackground";//音频弹窗-后台播放
//     public static final String ELEID_QUIT="videoquit";//音频弹窗-退出播放
//     public static final String ELEID_FRAME="videoframe";//音频悬浮
//
//     public static void setCustomEvent(String eleid) {
//         Map<String, String> eventDetail =  new HashMap<>();
//         eventDetail.put("pageid",PAGEID);
//         eventDetail.put("eleid",eleid);
//         switch (eleid){
//             case ELEID_BACK:
//             case ELEID_SHARE:
//             case ELEID_LAST:
//             case ELEID_NEXT:
//             case ELEID_PAUSE:
//             case ELEID_PALY:
//                 eventDetail.put("modid",MODID_ACTIVITY);
//                 break;
//             case ELEID_BACKGROUND:
//             case ELEID_QUIT:
//                 eventDetail.put("modid",MODID_FLOAT);
//                 break;
//             case ELEID_FRAME:
//                 eventDetail.put("modid",MODID_WGIB);
//                 break;
//         }
//         CustomStatisticsProxy.setCustomEvent(EVENTNAME,eventDetail);
//     }
// }
