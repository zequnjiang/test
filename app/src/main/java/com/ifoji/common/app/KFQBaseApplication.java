package com.ifoji.common.app;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Wen
 * Email: wenpeng@qianshengqian.com
 * Date: 2016/12/20
 * Description：基础 Application
 */
public class KFQBaseApplication extends Application {

    //Default time
    private final int TIMEOUT_CONNECT = 30000;
    private final int TIMEOUT_READ = 30000;
    private final int TIMEOUT_WRITE = 30000;

    // base data
    public static String TOKEN;
    public static String UID;
    public static String SOURCE;
    public static String APP_ID;
    public static String UDID;

    public Map<Object, Object> mapValue;

    // instance
    private static KFQBaseApplication instance;
    public static KFQBaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        instance = this;
        initBaseData();
        initHttp();
    }

    private void initBaseData() {
        mapValue = new HashMap<>();
//        SOURCE = DeveloperUtils.getMetaDataValue(instance, "UMENG_CHANNEL");
//        APP_ID = SOURCE + "_" + DeveloperUtils.getVersion(this);
//        TOKEN = SPUtils.get(this, SPUtils.USER_TOKEN, "").toString();
//        UID = SPUtils.get(this, SPUtils.USER_UID, "").toString();
//        UDID = MyUtil.getUDID();

    }

    /**
     * 初始化 okhttp
     */
    private void initHttp() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("KuaiFenQi-TAG"))
                .readTimeout(TIMEOUT_READ, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.MILLISECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }



}
