package com.ifoji.common.util;

import android.app.Activity;

import com.ifoji.R;


/**
 * Created by xiaozepro.
 * Email zequn@qianshengqian.com
 * Time 16/8/29 17:20
 */
public class AnimUtil {
    /**
     * 登录页进入动画（从底部进入）
     *
     * @param activity
     */
    public static void loginAnimIn(Activity activity) {
        activity.overridePendingTransition(R.anim.in_from_bottom, R.anim.alpha_out);
    }

    /**
     * 登录页退出动画（从底部退出）
     *
     * @param activity
     */
    public static void loginAnimOut(Activity activity) {
        activity.overridePendingTransition(R.anim.alpha_in, R.anim.out_to_bottom);
    }

    /**
     * 其他Activity的加载动画（右进左出）
     *
     * @param activity
     */
    public static void activityAnimIn(Activity activity) {
        activity.overridePendingTransition(R.anim.in_from_right, R.anim.alpha_out);
    }

    /**
     * 回退栈中Activity的回退加载动画(左进右出)
     *
     * @param activity
     */
    public static void activityAnimFinish(Activity activity) {
        activity.overridePendingTransition(R.anim.alpha_in, R.anim.out_to_right);
    }
}
