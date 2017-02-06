package com.ifoji.common.bll;

import com.ifoji.common.app.KFQConstants;
import com.ifoji.common.bll.net.HttpCallback;
import com.ifoji.common.bll.net.OkHttpUtilsRequest;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.util.Map;

/**
 * Created by Wen
 * Email: wenpeng@qianshengqian.com
 * Date: 2016/12/21
 * Description：
 */
public class BaseApi {

    /**
     * 执行 GET 请求
     * @param url
     * @param params
     * @param tag
     * @param httpCallback
     */
    protected void doGet(String url, Map<String, String> params, String tag, HttpCallback httpCallback) {
        String getUrl = KFQConstants.HOST + url;
        OkHttpUtilsRequest.get()
                .url(getUrl)
                .params(params)
                .tag(tag)
                .build()
                .execute(httpCallback);
    }

    /**
     * 执行 POST 请求
     * @param url
     * @param params
     * @param tag
     * @param httpCallback
     */
    protected void doPost(String url, Map<String, String> params, String tag, HttpCallback httpCallback) {
        String postUrl = KFQConstants.HOST + url;
        OkHttpUtilsRequest.post()
                .url(postUrl)
                .params(params)
                .tag(tag)
                .build()
                .execute(httpCallback);
    }

    /**
     * 下载文件
     * @param url
     * @param FileCallBack
     */
    protected void doDownloadFile(String url, FileCallBack FileCallBack) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(FileCallBack);
    }

}
