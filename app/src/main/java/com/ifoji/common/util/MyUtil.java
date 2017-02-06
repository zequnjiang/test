package com.ifoji.common.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;


import com.ifoji.common.app.KFQBaseApplication;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static com.ifoji.common.app.KFQConstants.APP_DIR;


/**
 * Created by xiaozepro.
 * Email zequn@qianshengqian.com
 * Time 16/8/29 14:15
 */
public class MyUtil {
    private static Handler handler = new Handler(Looper.getMainLooper());
    /**
     * 按钮点击时间阈值
     */
    private static final int FAST_CLICK_THRESHOLD = 1000;
    /**
     * 上次按钮点击时间
     */
    private static long lastClickTime;

    /**
     * @function: 防止按钮快速多次点击
     * @usage: if (MyUtil.isFastDoubleClick()) {return;}
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < FAST_CLICK_THRESHOLD)
            return true;
        lastClickTime = time;
        return false;
    }

    /**
     * 创建钱升钱文件夹
     */
    public static String createDaishangqianFolder(final Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + APP_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
                Log.d("MyUtil", "创建快分期文件夹成功");
            }
            return dir.getPath();
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    MyToast.show("请检查是否有SD卡");
                }
            });

        }
        return null;
    }

    /**
     * 创建父类的文件夹
     * @param file
     * @return
     */
    public static String createBaseFolder(File file) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File dir = new File(file.getParent());
            if (!dir.exists()) {
                dir.mkdirs();
                Log.d("MyUtil", "创建快分期文件夹成功");
            }
            return dir.getPath();
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    MyToast.show("请检查是否有SD卡");
                }
            });

        }
        return null;
    }


    /**
     * 时间戳转换
     * @param dataFormat
     * @param timeStamp
     * @return
     */
    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        //timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }

    /**
     * Get UDID
     * @return
     */
    public static String getUDID() {
        String mDevicedShort = "35" +
                // 主板
                Build.BOARD.length() % 10 +
                // android 系统定制商
                Build.BRAND.length() % 10 +
                // cpu 指令集
                Build.CPU_ABI.length() % 10 +
                // 设备参数
                Build.DEVICE.length() % 10 +
                // 显示屏参数
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                // 修订版本列表
                Build.ID.length() % 10 +
                // 硬件制造商
                Build.MANUFACTURER.length() % 10 +
                // 版本
                Build.MODEL.length() % 10 +
                // 手机制造商
                Build.PRODUCT.length() % 10 +
                // 描述build的标签
                Build.TAGS.length() % 10 +
                // builder 类型
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10;
        String serial = Build.SERIAL;
        String mIMEI = new UUID(mDevicedShort.hashCode(), serial.hashCode()).toString();
        return toMD5(mIMEI);
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
     * 格式化数据 - 保留两位小数
     * @param number
     * @return
     */
    public static String getInterest(Double number) {
        if (number == 0) {
            return "0.00";
        }

        BigDecimal bd = new BigDecimal(Double.valueOf(number));
        String m = bd.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        String newM = m.substring(m.indexOf(".") + 1, m.length());
        if (newM.length() == 1) {
            newM = newM + "0";
        }
        newM = m.substring(0, m.indexOf(".")) + "." + newM;
        return newM;
    }

    /**
     * @param str
     * @return
     * trim 并且 把中间的空格去掉
     */
    public static String replaceBlank(String str){
        return str.replace(" ", "");
    }

    /**
     * 从Assert中读取地址 json 数据
     * @param context
     * @param fileName
     * @return
     */
    public static String readAssert(Context context, String fileName){
        String resultString="";
        try {
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            resultString=new String(buffer,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * 信息过滤
     * @param pData
     * @return
     */
    public static String getNetData(String pData) {
        if(TextUtils.isEmpty(pData)) {
            return "";
        }
        return pData;
    }

    /**
     * 检查是否登录
     * @return
     */
    public static boolean checkLogin() {
        String oauthToken = SPUtils.get(KFQBaseApplication.getInstance(), SPUtils.USER_TOKEN, "").toString();
        if(!TextUtils.isEmpty(oauthToken)) {
            return true;
        }
        return false;
    }
}
