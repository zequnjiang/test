package com.ifoji.common.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ifoji.R;


/**
 * Created by zhaofei.
 * Email zhaofei@qianshengqian.com
 * Time 16/6/28 17:46
 * Explain: loading 框工具类
 */
public class LoadDialogUtil {
    /**
     * 加载框
     */
    private static Dialog mLoadingDialog;
    private TextView dlgMsg;

    /**
     * @param msg 加载框文本
     *            显示加载框
     */
    public static void showLoadDialog(Context context, CharSequence msg) {
        try {
            if (!(mLoadingDialog != null && mLoadingDialog.isShowing())) {
                mLoadingDialog = new Dialog(context, R.style.loading_trans);
                View viewDialog = LayoutInflater.from(context).inflate(R.layout.view_dialog, null);
                TextView dlgMsg = ((TextView) viewDialog.findViewById(R.id.dialog_msg));
                dlgMsg.setText(msg);
                mLoadingDialog.setCancelable(false); // 摁返回键 、 触摸其他地方不消失
                mLoadingDialog.setContentView(viewDialog);
                mLoadingDialog.getWindow().getAttributes().windowAnimations = R.style.loading_dialog_animation;
                mLoadingDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLoadingMsg(CharSequence msg) {
        if (dlgMsg != null)
            dlgMsg.setText(msg);
    }

    /**
     * @param msg 加载框文本
     *            显示加载框
     */
    public static void showLoadDialogCanBeClosed(Context context, CharSequence msg) {
        try {
            mLoadingDialog = new Dialog(context, R.style.loading_trans);
            View viewDialog = LayoutInflater.from(context).inflate(R.layout.view_dialog, null);
            ((TextView) viewDialog.findViewById(R.id.dialog_msg)).setText(msg);
            mLoadingDialog.setCancelable(true); // 摁返回键 、 触摸其他地方不消失
            mLoadingDialog.setContentView(viewDialog);
            mLoadingDialog.getWindow().getAttributes().windowAnimations = R.style.loading_dialog_animation;
            if (!mLoadingDialog.isShowing())
                mLoadingDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭加载框
     */
    public static void closeLoadDialog() {
        if (null != mLoadingDialog && mLoadingDialog.isShowing()) {
            try {
                mLoadingDialog.dismiss();
                mLoadingDialog = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
