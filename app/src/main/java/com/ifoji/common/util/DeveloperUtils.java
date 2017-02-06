package com.ifoji.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

public class DeveloperUtils {

    /**
     * @return 获取Sdcard路径
     */
    public static String getSDPath() {
        String sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory().toString();// 获取跟目录
        }
        return sdDir;
    }

    /**
     * @param name
     * @return 在根目录下创建文件夹并且返回创建的路径
     */
    public static String savePath(String name) {
        String path = getSDPath() + "/" + name;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    /**
     * @param string
     * @return MD5加密串
     */
    public static String toMD5(String string) {

        byte[] hash;

        try {

            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException("Huh, MD5 should be supported?", e);

        } catch (UnsupportedEncodingException e) {

            throw new RuntimeException("Huh, UTF-8 should be supported?", e);

        }

        StringBuilder hex = new StringBuilder(hash.length * 2);

        for (byte b : hash) {

            if ((b & 0xFF) < 0x10)
                hex.append("0");

            hex.append(Integer.toHexString(b & 0xFF));

        }

        return hex.toString();

    }

    /**
     * 获取当前的时分秒 yyyy-MM-dd HH:mm:ss ; yyyy-MM-dd hh:mm:ss 获取当前的年月日时分秒
     *
     * @return 当前的时分秒
     */
    public static String getData() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String ly_time = sdf.format(new java.util.Date());
        return ly_time;
    }

    /**
     * @param context 上下文对象
     * @return 网络是否可以连接
     */
    public static boolean isConn(Context context) {
        boolean bisConnFlag = false;
        ConnectivityManager conManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = conManager.getActiveNetworkInfo();
        if (network != null) {
            bisConnFlag = conManager.getActiveNetworkInfo().isAvailable();
        }
        return bisConnFlag;
    }

    /**
     * @param context 上下文对象
     * @return WIFI是否已经打开
     */
    public static Boolean wifiCheck(Context context) {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        int wifiApState = wifiManager.getWifiState();
        if (wifiApState == WifiManager.WIFI_STATE_DISABLED
                || wifiApState == WifiManager.WIFI_STATE_DISABLING) {
            return false;
        } else if (wifiApState == WifiManager.WIFI_STATE_ENABLED
                || wifiApState == WifiManager.WIFI_STATE_ENABLING) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param context
     * @param name    AndroidManifest节点名称
     * @return 获取AndroidManifest节点信息
     */
    public static String getMetaDataValue(Context context, String name) {
        String value = null;
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getApplicationContext().getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                value = applicationInfo.metaData.getString(name);
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not read the name in the manifest file.", e);
        }

        if (value == null) {
            throw new RuntimeException("The name '" + name
                    + "' is not defined in the manifest file's meta data.");
        }

        return value;
    }

    /**
     * 获取VersionCode和VersionName
     * @param context
     * @return
     */
    public static String getVersion(Context context){//获取版本号
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName + "_" + pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //return context.getString(R.string.version_unknown);
            return "";
        }
    }

    /**
     * 获取VersionName
     */
    public static String getVersionName(Context context){
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            //return context.getString(R.string.version_unknown);
            return "";
        }
    }

    /**
     * @return
     * 检查系统版本是否大于6.0
     */
    public static boolean canMakeSmores(){
        return(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    /**
     * 获取权限列表
     *
     * @param context
     */
    public static boolean checkSelfPermissions(Context context, String permissions) {
        PackageManager pm = context.getPackageManager();
        boolean permission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission(permissions, "com.qsq.paydayloan"));
        if (permission) {
            return true;
        } else {
            return false;
        }
    }

    /*private boolean hasPermission(Context context, String permission){
        if(canMakeSmores()){
            return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
        }
        return true;
    }*/


}
