package com.ifoji.common.util;

import android.widget.Toast;

import com.ifoji.common.app.KFQBaseApplication;


/**
 * Created by xiaoze on 16/8/24.
 */
public class MyToast {

    private static Toast mToast;

    public static void show(String text){
        if (mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(KFQBaseApplication.getInstance().getApplicationContext(), text, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showLong(String text){
        if (mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(KFQBaseApplication.getInstance().getApplicationContext(), text, Toast.LENGTH_LONG);
        mToast.show();
    }

}
