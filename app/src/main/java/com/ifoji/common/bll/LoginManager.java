package com.ifoji.common.bll;

import android.app.Activity;

import com.ifoji.common.app.KFQBaseApplication;
import com.ifoji.common.dal.event.LoginEvent;
import com.ifoji.common.util.SPUtils;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by Wen
 * Email: wenpeng@qianshengqian.com
 * Date: 2016/12/23
 * Description：登录-Manager
 */
public class LoginManager {

    /**
     * 跳转去登陆
     */
    public static void jumpToLogin(Activity activity) {
        clearLoginData(activity);
        // 跳转去登录
//        JumpRouterActionUtil.gotoActivity(activity, LoginActivity.class, false);
    }

    /**
     * 保存用户登录信息
     */
    public static void saveLoginData(Activity activity, String token, String uid, String phone) {
        // 保存数据
        SPUtils.put(activity, SPUtils.USER_TOKEN, token);
        SPUtils.put(activity, SPUtils.USER_UID, uid);
        SPUtils.put(activity, SPUtils.USER_PHONE, phone);
        KFQBaseApplication.getInstance().TOKEN = token;
        KFQBaseApplication.getInstance().UID = uid;
        // 登录广播
        EventBus.getDefault().post(new LoginEvent());
    }

    /**
     * 清除用户登录信息
     */
    public static void clearLoginData(Activity activity) {
        SPUtils.remove(activity, SPUtils.USER_TOKEN);
        SPUtils.remove(activity, SPUtils.USER_UID);
        KFQBaseApplication.getInstance().TOKEN = "";
        KFQBaseApplication.getInstance().UID = "";
        // 退出广播
        EventBus.getDefault().post(new LoginEvent());
    }

    /**
     * 清除用户登录信息
     */
    public static void clearLoginData() {
        SPUtils.remove(KFQBaseApplication.getInstance(), SPUtils.USER_TOKEN);
        SPUtils.remove(KFQBaseApplication.getInstance(), SPUtils.USER_UID);
        KFQBaseApplication.getInstance().TOKEN = "";
        KFQBaseApplication.getInstance().UID = "";
        // 退出广播
        EventBus.getDefault().post(new LoginEvent());
    }

}
