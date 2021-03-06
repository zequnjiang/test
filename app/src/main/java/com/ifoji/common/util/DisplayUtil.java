package com.ifoji.common.util;

import android.content.Context;
import android.content.res.Resources;
import android.view.WindowManager;


import com.ifoji.common.app.KFQBaseApplication;

import java.lang.reflect.Field;

/**
 * 转换界面尺寸的工具类
 * Created by qsq on 16/3/18.
 */
public class DisplayUtil {

    public static int screenW;
    public static int screenH;

    //状态栏的高度
    public static int statusBarHeight;

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenW(Context context) {
        if (screenW == 0) {
            initScreen(context);
        }
        return screenW;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenH(Context context) {
        if (screenH == 0) {
            initScreen(context);
        }
        return screenH;
    }

    /**
     * 屏幕数据初始化
     */
    private static void initScreen(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        screenW = wm.getDefaultDisplay().getWidth();
        screenH = wm.getDefaultDisplay().getHeight();
    }

    /**
     * @param dip
     * @return
     */
    public static int dp2px(Context context, float dip) {
        Resources resources = context.getResources();

        float scale = resources.getDisplayMetrics().density;

        resources = null;

        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    /**
     * @param dip
     * @return
     */
    public static int dp2px(float dip) {
        Resources resources = KFQBaseApplication.getInstance().getResources();
        float scale = resources.getDisplayMetrics().density;
        resources = null;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    /**
     * PX转换DP
     * @return
     */
    public static int px2dp(Context context, float px) {
        Resources resources = context.getResources();
        float scale = resources.getDisplayMetrics().density;
        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));
    }

    /**
     * 获取状态栏的高度
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        if(statusBarHeight == 0) {
            initStatusBarHeight(context);
        }
        return statusBarHeight;
    }

    /**
     * 初始化状态栏的高度
     * @return
     */
    private static void initStatusBarHeight(Context context) {
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            Field field = clazz.getField("status_bar_height");
            //反射出该对象中status_bar_height字段所对应的在R文件的id值
            //该id值由系统工具自动生成,文档描述如下:
            //The desired resource identifier, as generated by the aapt tool.
            int id = Integer.parseInt(field.get(object).toString());
            //依据id值获取到状态栏的高度,单位为像素
            statusBarHeight = context.getResources().getDimensionPixelSize(id);
        } catch (Exception e) {
            statusBarHeight = 0;
        }
    }

}
