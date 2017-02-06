package com.ifoji.common.bll.net;


import com.ifoji.common.dal.model.KFQBaseModel;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created By: sqq
 * Created Time: 16/9/29 上午11:17.
 */

public class BaseCall<T extends KFQBaseModel> extends Callback<T> {

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        return null;
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(T response, int id) {

    }
}
