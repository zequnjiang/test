package com.ifoji.common.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Wen
 * Email: wenpeng@qianshengqian.com
 * Date: 2017/1/5
 * Description：打开关闭键盘
 */
public class KeyBoardUtils {

    public static int mHeight;
    public static void setKeyBoardHeight(int height) {
        KeyBoardUtils.mHeight = height;
    }
    public static int getKeyBoardHeight() {
        return KeyBoardUtils.mHeight;
    }

    /**
     * 打开键盘
     * @param editText
     * @param context
     */
    public static void openKeyBoard(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
        //imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭键盘
     * @param editText
     * @param context
     */
    public static void closeKeyBoard(EditText editText, Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}
