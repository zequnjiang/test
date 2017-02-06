package com.ifoji.common.bll.net;

import android.os.Build;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.OkHttpRequestBuilder;
import com.zhy.http.okhttp.builder.PostFileBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;

/**
 * 基础请求的构造
 * Created by xiaoze on 16/8/25.
 */

public class OkHttpUtilsRequest {

    /**
     * 添加请求Header
     * @param okHttpRequestBuilder
     */
    private static void addAppHeader(OkHttpRequestBuilder okHttpRequestBuilder) {
//        okHttpRequestBuilder
//                .addHeader("X-CLIENT-ID", KFQBaseApplication.getInstance().SOURCE)
//                .addHeader("X-App-ID", KFQBaseApplication.getInstance().APP_ID)
//                .addHeader("X-UDID", KFQBaseApplication.getInstance().UDID)
//                .addHeader("X-User-Token", "")
//                .addHeader("X-TOKEN", KFQBaseApplication.TOKEN)
//                .addHeader("X-User-Phone-Brand", Build.MODEL)
//                .addHeader("X-User-System", Build.VERSION.RELEASE);
    }

    public static PostFormBuilder post() {
        PostFormBuilder postFormBuilder = OkHttpUtils.post();
        addAppHeader(postFormBuilder);
        return postFormBuilder;
    }

    public static GetBuilder get() {
        GetBuilder getBuilder = OkHttpUtils.get();
        addAppHeader(getBuilder);
        return getBuilder;
    }

    public static PostFileBuilder uploadFile() {
        PostFileBuilder postFileBuilder = OkHttpUtils.postFile();
        addAppHeader(postFileBuilder);
        return postFileBuilder;
    }



}
