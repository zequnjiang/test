package com.ifoji.common.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by zhaofei.
 * Email zhaofei@qianshengqian.com
 * Time 16/6/28 16:52
 * Explain: 跳转类
 */
public class KFQCallUtil {
    /**
     * 加载框
     */
    public static final String QSQ_PHONE = "tel:";
    /**
     * @param context
     * @param phone   拨打的电话号码
     */
    public static void callPhone(Context context, String phone) {
        if(context == null || TextUtils.isEmpty(phone)) {
            return;
        }
        String callPhone = QSQ_PHONE + phone;
        // ACTION_CALL直接拨打
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(callPhone));
        context.startActivity(intent);
    }
    /**
     * @param context
     * @param phone   直接启动拨打的电话号码
     */
    public static void directDialTelephone(Context context, String phone) {
        // ACTION_CALL直接拨打 - 需要做权限处理
        //Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(phone));
        //context.startActivity(intent);
    }
}
