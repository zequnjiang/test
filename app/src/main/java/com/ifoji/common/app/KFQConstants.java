package com.ifoji.common.app;

import android.os.Environment;


import java.io.File;

/**
 * Created by Wen
 * Email: wenpeng@qianshengqian.com
 * Date: 2016/12/20
 * Description：基本配置参数
 */
public class KFQConstants {

    /**
     * 是否DEBUG模式
     */
    public final static boolean isDebug = true;
    public static final int VERSION_CODE = 1;

    // 注册推送是否成功
    public static boolean PUSH_REGISTER_STATUS = false;

    // Base host url
    public static final String HOST = "http://lmd-test1.fond.io";
    //public static final String HOST = "http://lmd-test.fond.io";

    public static final String JS_FLAG = "qsqapi";

    public static final String WEB_HOME = "http://lmd-test1.fond.io/romantic2/#!/multi-home";
    //public static final String WEB_HOME = "http://10.6.40.242:7777/#!/test-jsbridge";

    //public static final String WEB_BASE_URL = "http://lmd-test1.fond.io/romantic2/#!/multi-home";
    //public static final String WEB_HOME = "http://lmd-test1.fond.io/v2/transfer/index?token=" + KFQBaseApplication.TOKEN +"&h5_scene_type=app&type=1&state=";

    public static final String WEB_BASE = "http://lmd-test1.fond.io/v2/transfer/index?token=";
    public static final String WEB_AND = "&h5_scene_type=app&type=1&state=";
    public static final String WEB_URL = "http://lmd-test1.fond.io/romantic2/#!/multi-home";

    // download - params
    public static final String APP_DIR = "KuqiFenQi";
    public static final String APP_UPDATE_NAME = "kfq.apk";

    // permission
    public static String PERMISSION_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    public static String PERMISSION_CONTRACTS = "android.permission.READ_CONTACTS";
    public static String PERMISSION_CALL = "android.permission.READ_CALL_LOG";
    public static String PERMISSION_PHONESTATE = "android.permission.READ_PHONE_STATE";
    public static String PERMISSION_WRITESTORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    // permission - add
    public static String PERMISSION_CAMERA = "android.permission.CAMERA";
    public static String PERMISSION_READ_SMS = "android.permission.READ_SMS";

    // dialog - params
    public static final int DIALOG_TYPE_NORMAL = 0x0010;
    public static final int DIALOG_TYPE_PIC = 0x0011;

    // message upload staus
    public static int SMS_STATUS;
    public static String SMS_LAST_UPLOAD_TIME;

    public static String UPLOAD_PIC_TEMP_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + APP_DIR + File.separator +"temppic" + File.separator + "tmepkfq.jpg";

    public static String WEB_JUMP_URL = "jumpurl";

}
