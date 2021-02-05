package com.githubyss.mobile.common.ui.utils;

import android.app.AppOpsManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.githubyss.mobile.common.kit.enumeration.VersionCode;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * PermissionOverlayUtils
 * <Description>
 * <Details>
 *
 * @author Ace Yan
 * @github githubyss
 * @createdTime 2020/12/17 11:24:04
 */
public class PermissionOverlayUtils {
    
    // ---------- ---------- ---------- Properties ---------- ---------- ----------
    
    private static int OP_SYSTEM_ALERT_WINDOW = 24;
    
    
    // ---------- ---------- ---------- Public Methods ---------- ---------- ----------
    
    /**
     * PermissionOverlayUtils.hasPermission([context])
     * <Description> 是否拥有权限
     * <Details>
     *
     * @param [context]
     * @return boolean
     * @author Ace Yan
     * @github githubyss
     * @createdTime 2020/12/17 11:28:53
     */
    public static boolean hasPermission(Context context) {
        // M(23/6.0) 及以上，通过 Settings 判断悬浮窗权限
        if (Build.VERSION.SDK_INT >= VersionCode.M) {
            // MIUI Android 8.0/8.1 (O(26)/O_MR1(27))) 悬浮框有问题，走系统漏洞，不走正常流程
            if (isMiUiO()) {
                return true;
            }
            return Settings.canDrawOverlays(context);
        }
        // KITKAT(19/4.4) 到 LOLLIPOP_MR1(22/5.1)，加悬浮框权限判断
        else if (Build.VERSION.SDK_INT >= VersionCode.KITKAT) {
            if (needPermission()) {
                return hasPermissionBelowMarshmallow(context);
            } else {
                return true;
            }
        }
        // KITKAT(19/4.4) 以下，不需要悬浮窗权限
        else {
            return true;
        }
    }
    
    public static boolean isMiUiO() {
        return (Build.VERSION.SDK_INT == VersionCode.O || Build.VERSION.SDK_INT == VersionCode.O_MR1) && isMiui();
    }
    
    public static void jumpToOverlayPermission(Context context) {
        try {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            context.startActivity(intent);
        }
    }
    
    
    // ---------- ---------- ---------- Private Methods ---------- ---------- ----------
    
    /**
     * PermissionOverlayUtils.needPermission([])
     * <Description> 是否需要获取权限
     * <Details>
     *
     * @param []
     * @return boolean
     * @author Ace Yan
     * @github githubyss
     * @createdTime 2020/12/17 11:27:43
     */
    private static boolean needPermission() {
        if (isFlyme() || isMiui()) {
            return true;
        }
        return false;
    }
    
    /**
     * PermissionOverlayUtils.isFlyme([])
     * <Description> 是否是魅族系统
     * <Details>
     *
     * @param []
     * @return boolean
     * @author Ace Yan
     * @github githubyss
     * @createdTime 2020/12/17 11:27:18
     */
    public static boolean isFlyme() {
        try {
            String  display                   = Build.DISPLAY;
            String  fingerprint               = Build.FINGERPRINT;
            String  incremental               = Build.VERSION.INCREMENTAL;
            boolean isDisplayContainFlyme     = !TextUtils.isEmpty(display) && display.contains("Flyme");
            boolean isFingerprintContainFlyme = !TextUtils.isEmpty(fingerprint) && fingerprint.contains("Flyme");
            boolean isIncrementalContainFlyme = !TextUtils.isEmpty(incremental) && incremental.contains("Flyme");
            return isDisplayContainFlyme || isFingerprintContainFlyme || isIncrementalContainFlyme;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * PermissionOverlayUtils.isMiui([])
     * <Description> 是否是小米系统
     * <Details>
     *
     * @param []
     * @return boolean
     * @author Ace Yan
     * @github githubyss
     * @createdTime 2020/12/17 11:40:41
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
    
    private static List<String> getSystemProperties(String[] commands) {
        Process          process       = null;
        BufferedReader   successReader = null;
        BufferedReader   errorReader   = null;
        DataOutputStream dos           = null;
        List<String>     resultList    = new ArrayList<>();
        
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
            int    status = process.waitFor();
            String lineStr;
            successReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((lineStr = successReader.readLine()) != null) {
                resultList.add(lineStr);
            }
            // while ((lineStr = errorReader.readLine()) != null) {
            // }
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
     * PermissionOverlayUtils.hasPermissionBelowMarshmallow([context])
     * <Description> Marshmallow (6.0) 以下判断是否有权限
     * <Details> 理论上 6.0 以上才需处理权限，但有的国内 Rom 在 6.0 以下就添加了权限
     * 其实此方式也可以用于判断 6.0 以上版本，只不过有更简单的 Settings.canDrawOverlays 代替
     *
     * @param [context]
     * @return boolean
     * @author Ace Yan
     * @github githubyss
     * @createdTime 2020/12/17 11:51:02
     */
    private static boolean hasPermissionBelowMarshmallow(Context context) {
        try {
            AppOpsManager manager        = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            Method        dispatchMethod = AppOpsManager.class.getMethod("checkOp", int.class, int.class, String.class);
            return AppOpsManager.MODE_ALLOWED == (Integer) dispatchMethod.invoke(manager, OP_SYSTEM_ALERT_WINDOW, Binder.getCallingUid(), context.getApplicationContext().getPackageName());
        } catch (Exception e) {
            return false;
        }
    }
}
