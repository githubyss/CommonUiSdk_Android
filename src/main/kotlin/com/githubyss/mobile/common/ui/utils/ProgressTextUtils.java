package com.githubyss.mobile.common.ui.utils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 88396251
 * 2018/5/23
 * 时间格式化类
 */

public class ProgressTextUtils {

    public static String getProgressText(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        double minute = calendar.get(Calendar.MINUTE);
        double second = calendar.get(Calendar.SECOND);

        DecimalFormat format = new DecimalFormat("00");
        return format.format(minute) + ":" + format.format(second);
    }
}
