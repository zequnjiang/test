package com.ifoji.common.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.ifoji.common.app.KFQConstants.isDebug;


/**
 * Log工具类
 * Created by sqq on 16/9/26.
 */

public class Logs {

    /**
     * json格式化显示的缩进字符数
     */
    private final static int JSON_INDENT = 4;
    /**
     * 应用名标签（用以本应用Log打印的默认TAG）
     */
    private final static String TAG = "[paydayloan]";
    /**
     * 行分隔符
     */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private Logs() {
       /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void i(Object str) {
        i(TAG, str);
    }

    public static void d(Object str) {
        d(TAG, str);
    }

    public static void v(Object str) {
        v(TAG, str);
    }

    public static void w(Object str) {
        w(TAG, str);
    }

    public static void e(Object str) {
        e(TAG, str);
    }

    public static void i(String tag, Object str) {
        if (isDebug) {
            String name = getFunctionName();
            if (name != null) {
                Log.i(tag, name + " - " + str);
            } else {
                Log.i(tag, str.toString());
            }
        }

    }

    public static void d(String tag, Object str) {
        if (isDebug) {
            String name = getFunctionName();
            if (name != null) {
                Log.d(tag, name + " - " + str);
            } else {
                Log.d(tag, str.toString());
            }
        }
    }

    public static void v(String tag, Object str) {
        if (isDebug) {
            String name = getFunctionName();
            if (name != null) {
                Log.v(tag, name + " - " + str);
            } else {
                Log.v(tag, str.toString());
            }
        }
    }

    public static void w(String tag, Object str) {
        if (isDebug) {
            String name = getFunctionName();
            if (name != null) {
                Log.w(tag, name + " - " + str);
            } else {
                Log.w(tag, str.toString());
            }
        }
    }

    public static void e(String tag, Object str) {
        if (isDebug) {
            String name = getFunctionName();
            if (name != null) {
                Log.e(tag, name + " - " + str);
            } else {
                Log.e(tag, str.toString());
            }
        }
    }

    public static void e(Exception ex) {
        if (isDebug) {
            Log.e(TAG, "error", ex);
        }
    }

    public static void e(String tag, String log, Throwable tr) {
        if (isDebug) {
            String line = getFunctionName();
            Log.e(tag, "{Thread:" + Thread.currentThread().getName() + "}"
                    + "[" + line + ":] " + log + "\n", tr);
        }
    }

    public static void e(String log, Throwable tr) {
        e(TAG, log, tr);
    }

    public static void json(Object json) {
        json(TAG, json);
    }

    /**
     * 格式化输出json
     *
     * @param tag
     * @param json
     */
    public static void json(String tag, Object json) {
        if (isDebug) {
            String name = getFunctionName();
            if (name != null) {
                Log.i(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
                printJson(tag, name, json.toString());
                Log.i(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
            } else {
                Log.i(tag, json.toString());
            }
        }
    }

    /**
     * 格式化json
     */
    private static void printJson(String tag, String name, String json) {
        String message;
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                message = jsonObject.toString(JSON_INDENT);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                message = jsonArray.toString(JSON_INDENT);
            } else {
                message = json;
            }
        } catch (JSONException e) {
            message = json;
        }
        message = name + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.i(tag, "║ " + line);
        }
    }

    /**
     * 获取当前线程、类、方法、行
     *
     * @return
     */
    private static String getFunctionName() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(Logs.class.getName())) {
                continue;
            }
            return "[ " + Thread.currentThread().getName() + ": ("
                    + st.getFileName() + ":" + st.getLineNumber() + ") "
                    + st.getMethodName() + " ]";
        }
        return null;
    }


    public static void info(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void debug(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void error(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }
}
