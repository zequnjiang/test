package com.ifoji.common.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import com.ifoji.common.app.KFQBaseApplication;

import java.util.Map;

/**
 * Created by ly on 16/3/31.
 */
public class JumpRouterActionUtil {

    /**
     * 不带参数的跳转
     * 当前跳转的activity
     *
     * @param curActivity    跳往的activity
     * @param targetActivity 是否关闭当前activity
     * @param finish
     */
    public static void gotoActivity(Activity curActivity,
                                    Class<?> targetActivity, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(curActivity, targetActivity);
        curActivity.startActivity(intent);

        if (finish) {
            curActivity.finish();
        }
    }

    /**
     * 带参数的跳转
     *
     * @param curActivity    当前跳转activity
     * @param targetActivity 跳往的activity
     * @param bundle         携带的参数
     * @param finish         是否关闭
     */
    public static void gotoActivityWithData(Activity curActivity,
                                            Class<?> targetActivity, Bundle bundle, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(curActivity, targetActivity);
        intent.putExtras(bundle);
        curActivity.startActivity(intent);

        if (finish) {
            curActivity.finish();
        }
    }

    /**
     * 带参数跳转需要返回值
     * 跳转前的activity
     *
     * @param curActivity    跳往的activity
     * @param targetActivity 携带的参数
     * @param bundle         请求码
     * @param RequestCode    是否关闭
     * @param finish
     */

    public static void gotoActivityWithDataForResult(Activity curActivity,
                                                     Class<?> targetActivity, Bundle bundle, int RequestCode,
                                                     boolean finish) {
        Intent intent = new Intent();
        intent.setClass(curActivity, targetActivity);
        intent.putExtras(bundle);
        curActivity.startActivityForResult(intent, RequestCode);

        if (finish) {
            curActivity.finish();
        }
    }

    /**
     * 带参数跳转需要返回值
     * 跳转前的activity
     *
     * @param curActivity    跳往的activity
     * @param targetActivity 携带的参数
     * @param bundle         请求码
     * @param RequestCode    来自
     * @param from           是否关闭
     * @param finish
     */

    public static void gotoActivityWithDataForResult(Activity curActivity,
                                                     Class<?> targetActivity, Bundle bundle, int RequestCode, int from,
                                                     boolean finish) {
        Intent intent = new Intent();
        intent.putExtra("from", from);
        intent.setClass(curActivity, targetActivity);
        intent.putExtras(bundle);
        curActivity.startActivityForResult(intent, RequestCode);

        if (finish) {
            curActivity.finish();
        }
    }

    /**
     * 不带参数跳转需要返回值
     * 跳转前activity
     *
     * @param curActivity    跳往activity
     * @param targetActivity 请求码
     * @param Code           是否关闭
     * @param finish
     */
    public static void gotoActivityForResult(Activity curActivity,
                                             Class<?> targetActivity, int Code, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(curActivity, targetActivity);
        curActivity.startActivityForResult(intent, Code);

        if (finish) {
            curActivity.finish();
        }
    }

    public static void gotoOnlyActivity(Activity curActivity, Class<?> targetActivity) {

    }

    public static void gotoWebActivity(Activity curActivity, String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putBoolean("isMian", false);
        //gotoActivityWithData(curActivity, Web2Activity.class, bundle, false);
//        gotoActivityWithData(curActivity, KFQWebActivity.class, bundle, false);
    }

    /**
     * 关闭Activity
     *
     * @param curActivity 需要操作的Activity
     * @param isFinish    是否关闭这个Activity  true 关闭这个Activity
     */
    public static void removeActivity(Class<?> curActivity, boolean isFinish) {
        Map map = KFQBaseApplication.getInstance().mapValue;
        if (isFinish) { //关闭这个Activity
            if (null != KFQBaseApplication.getInstance().mapValue.get(curActivity.getName())) {
                ((Activity) KFQBaseApplication.getInstance().mapValue.get(curActivity.getName())).finish();
            }
        } else { //从Application里移除这个key
            if (null != KFQBaseApplication.getInstance().mapValue.get(curActivity.getName())) {
                KFQBaseApplication.getInstance().mapValue.remove(curActivity.getName());
            }
        }

    }

}


