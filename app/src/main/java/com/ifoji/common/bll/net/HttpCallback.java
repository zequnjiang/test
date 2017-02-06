package com.ifoji.common.bll.net;


import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.ifoji.common.bll.LoginManager;
import com.ifoji.common.dal.model.KFQBaseModel;
import com.ifoji.common.util.LoadDialogUtil;
import com.ifoji.common.util.Logs;
import com.ifoji.common.util.MyToast;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.utils.Platform;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;


/**
 * 网络回调的统一处理
 * Created by xiaoze on 16/8/25.
 */
public abstract class HttpCallback<T extends KFQBaseModel> extends Callback<T> {

    /** code-成功 **/
    private static final int REQUEST_SUCCESS = 0;
    private static final int REQUEST_ACTION = 2;

    private static final int REQUERST_N_LOGIN = 1002;

    private Platform mPlatform;

    private Activity mActivity;

    protected boolean isCloseLoadingOnAfter;

    public HttpCallback(boolean isCloseLoadingOnAfter) {
        this.isCloseLoadingOnAfter = isCloseLoadingOnAfter;
    }

    public HttpCallback() {
        this(true);
        mPlatform = Platform.get();
    }

    public HttpCallback(Activity pActivity) {
        this(true);
        this.mActivity = pActivity;
        mPlatform = Platform.get();
    }

    /**
     * 请求前必走,UI Thread
     */
    @Override
    public void onBefore(Request request, int id) {
        super.onBefore(request, id);
    }

    /**
     * 请求后必走,UI Thread
     */
    @Override
    public void onAfter(int id) {
        super.onAfter(id);
        if (isCloseLoadingOnAfter) {
            LoadDialogUtil.closeLoadDialog();
        }
    }

    /**
     * 获取泛型的类型
     */
    private Type getModelClass() {
        Type classType = null;
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            Logs.i("getModelClass: types = " + types.length + ",," + types);
            classType = types[0];
        }
        return classType;
    }

    /**
     * 子线程  ,处理耗时操作
     */
    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String json = response.body().string();
        Logs.json(json);
        return new Gson().fromJson(json, getModelClass());
    }

    /**
     * UI Thread
     */
    @Override
    public void onError(Call call, Exception e, int id) {
        String message;
        if (e != null) {
            if (e instanceof UnknownHostException) {
                message = "网络错误";
            } else if (e instanceof SocketTimeoutException) {
                message = "网络超时请重试";
            } else if (e instanceof ConnectException) {
                message = "服务器连接失败了,请检查网络...";
            } else if (e instanceof SSLHandshakeException) {
                message = "证书过期，请核对手机系统日期是否设置为当前日期";
            } else {
                message = e.getMessage();
            }
            Logs.e(e);
        } else {
            message = "网络错误";
        }
        MyToast.show(message);
        onRequestFailure(message);

    }

    /**
     * 成功请求数据,UI Thread
     */
    @Override
    public void onResponse(final T response, final int id) {
        int code = response.getCode();
        String msg = response.getMessage();
        switch (code) {
            case REQUEST_SUCCESS:
                if (response.getData() != null) {
                    onRequestSuccess(response.getData());
                } else {
                    onRequestSuccess("");
                }
                break;
            case REQUEST_ACTION:
                // 需要处理
                showToast(msg);
                if (response.getData() != null) {
                    onRequestSuccess(response.getData());
                } else {
                    onRequestSuccess(null);
                }
                break;
            case REQUERST_N_LOGIN:
                // 清除数据
                MyToast.show(response.getMessage());
                LoginManager.clearLoginData();
                break;
            default:
                onCodeError(code, response.getMessage(), id);
        }
    }

    /**
     * Show Toast
     * @param message
     */
    private void showToast(String message) {
        if(!TextUtils.isEmpty(message)) {
            MyToast.show(message);
        }
    }

    /**
     * 跳转去登录
     */
    private void goLogin() {
        if(mPlatform != null) {
            mPlatform.execute(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    }

    public abstract void onRequestSuccess(Object response);

    public void onRequestFailure(String message) {

    }

    /**
     * 处理错误code,,UI
     *
     * @param message 错误信息
     * @param code    code
     */
    public void onCodeError(int code, String message, int id) {
        Logs.i("onCodeError() called with: " + "code = [" + code + "], message = [" + message + "], id = [" + id + "]");
        if (TextUtils.isEmpty(message)) {
            message = "-错误code码:'" + code + "'-";
        }
        MyToast.show(message);
        onRequestFailure(message);
    }

}
