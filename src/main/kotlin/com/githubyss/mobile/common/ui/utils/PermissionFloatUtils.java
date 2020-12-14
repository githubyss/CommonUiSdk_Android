package com.githubyss.mobile.common.ui.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.githubyss.mobile.common.ui.audio.constant.Constant;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 88396251
 * 2018/5/23
 * 权限申请类
 */

public class PermissionFloatUtils {
    private static int OP_SYSTEM_ALERT_WINDOW = 24;
    //权限白名单，这个里面的型号，不用进行权限判断
    private static String WHITE = "white";
    //权限白名单，这个里面的型号，进行权限判断
    private static String BLACK = "black";

    public static boolean hasPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean MiUiO = (Build.VERSION.SDK_INT == Constant.VERSION_CODES_O || Build.VERSION.SDK_INT == Constant.VERSION_CODES_O_MR1)
                    && PermissionFloatUtils.isMiui();
            if (MiUiO) {
                //小米8.0悬浮框有问题，走系统漏洞，不走正常流程
                return true;
            }
            return Settings.canDrawOverlays(context);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //19到24这个范围内的手机加悬浮框判断
            if (needPermission()) {
                return hasPermissionBelowMarshmallow(context);
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private static boolean needPermission() {
        if (isFlyme() || isMiui()) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否存在白名单或者黑名单
     * @param array
     * @return
     * @throws JSONException
     */
    private static boolean isExistence(JSONArray array) throws JSONException {
        if (array == null) {
            return false;
        }
        //手机品牌
        String manufacturer = Build.MANUFACTURER;
        //手机型号
        String model = Build.MODEL;
        int size = array.length();
        for (int i = 0; i < size; i++) {
            String[] items = ((String) array.get(i)).split("-");
            if (items.length == 2) {
                if (items[1].contains(model)) {
                    return true;
                }
            } else {
                if (items[0].contains(manufacturer)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是魅族系统
     *
     * @return
     */
    public static boolean isFlyme() {
        try {
            String display = Build.DISPLAY;
            String fingerprint = Build.FINGERPRINT;
            String incremental = Build.VERSION.INCREMENTAL;
            if ((!TextUtils.isEmpty(display) && display.contains("Flyme")
                    || !TextUtils.isEmpty(fingerprint) && fingerprint.contains("Flyme")
                    || !TextUtils.isEmpty(incremental) && incremental.contains("Flyme"))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 是否是MIUI系统
     *
     * @return
     */
    public static boolean isMiui() {
        List<String> versionList = getSystemProperties(new String[]{"ro.miui.ui.version.name"});

        if (versionList.isEmpty()) {
            return false;
        }
        if (!TextUtils.isEmpty(versionList.get(0))) {
            return true;
        }
        return false;
    }

    public static List<String> getSystemProperties(String[] commands) {
        Process process = null;
        BufferedReader successReader = null;
        BufferedReader errorReader = null;
        DataOutputStream dos = null;
        List<String> resultList = new ArrayList<>();

        if (commands == null || commands.length == 0) {
            return resultList;
        }
        try {
            process = Runtime.getRuntime().exec("sh");
            dos = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (TextUtils.isEmpty(command)) {
                    continue;
                }
                command = "getprop " + command;
                dos.write(command.getBytes());
                dos.writeBytes("\n");
                dos.flush();
            }
            dos.writeBytes("exit\n");
            dos.flush();
            int status = process.waitFor();
            String lineStr;
            successReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            errorReader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            while ((lineStr = successReader.readLine()) != null) {
                resultList.add(lineStr);
            }
            //            while ((lineStr = errorReader.readLine()) != null) {
            //            }
        } catch (IOException e) {
        } catch (Exception e) {
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
                if (successReader != null) {
                    successReader.close();
                }
                if (errorReader != null) {
                    errorReader.close();
                }
            } catch (IOException e) {
            }

            if (process != null) {
                process.destroy();
            }
        }
        return resultList;
    }

    /**
     * 6.0以下判断是否有权限
     * 理论上6.0以上才需处理权限，但有的国内rom在6.0以下就添加了权限
     * 其实此方式也可以用于判断6.0以上版本，只不过有更简单的canDrawOverlays代替
     */
    private static boolean hasPermissionBelowMarshmallow(Context context) {
        try {
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            Method dispatchMethod = AppOpsManager.class.getMethod("checkOp", int.class, int.class, String.class);
            return AppOpsManager.MODE_ALLOWED == (Integer) dispatchMethod.invoke(
                    manager, OP_SYSTEM_ALERT_WINDOW, Binder.getCallingUid(), context.getApplicationContext().getPackageName());
        } catch (Exception e) {
            return false;
        }
    }
}
