package com.ifoji.common.bll;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Wen
 * Email: wenpeng@qianshengqian.com
 * Date: 2016/12/21
 * Description：
 */
public class ActivityManager {

    static AtomicInteger showingCount = new AtomicInteger();
    static AtomicBoolean isShowing = new AtomicBoolean();

    public static void registShowing() {
        int count = showingCount.incrementAndGet();
        if(count == 1) {
            isShowing.set(true);
        }
    }

    public static void unRegistShowing() {
        int count = showingCount.incrementAndGet();
        if(count <= 0) {
            isShowing.set(false);
        }
    }

    public static boolean isShowing() {
        return isShowing.get();
    }

}
