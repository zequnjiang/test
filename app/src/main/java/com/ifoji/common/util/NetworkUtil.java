package com.ifoji.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络状态判断
 * Created by qsq on 16/11/15.
 */
public class NetworkUtil {

    public final static int NONE = 0;
    public final static int WIFI = 1;
    public final static int MOBILE = 2;

    /**
     * 获取当前网络状态(是否可用)
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context){
        boolean netWorkStatus = false;
        if(null!=context){
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connManager.getActiveNetworkInfo()!=null){
                netWorkStatus = connManager.getActiveNetworkInfo().isAvailable();
            }
        }
        return netWorkStatus;
    }

    /**
     * 获取3G或者WIFI网络
     * @param context
     * @return
     */
    public static int getNetworkState(Context context){
        if(null!=context){
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo.State state;
            NetworkInfo networkInfo;
            if(null!=connManager){
                //Wifi网络判断
                networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if(null!=networkInfo){
                    state = networkInfo.getState();
                    if(state == NetworkInfo.State.CONNECTED||state == NetworkInfo.State.CONNECTING){
                        return WIFI;
                    }
                }

                //3G网络判断
                networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if(null!=networkInfo){
                    state = networkInfo.getState();
                    if(state == NetworkInfo.State.CONNECTED||state == NetworkInfo.State.CONNECTING){
                        return MOBILE;
                    }
                }
            }

        }
        return NONE;
    }

}
