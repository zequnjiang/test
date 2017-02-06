package com.ifoji.common.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by xiaozepro.
 * Email zequn@qianshengqian.com
 * Time 16/10/31 19:32
 */
public class BaseWebView extends WebView {

    public BaseWebView(Context context) {
        super(context);
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
    }

    public void init(){
        WebSettings settings = this.getSettings();

        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //设置编码
        settings.setDefaultTextEncodingName("UTF-8");
        //WebView中启用或禁用文件访问
        settings.setAllowFileAccess(true);
        /* 一般很少会用到这个，用WebView组件显示普通网页时一般会出现横向滚动条，这样会导致页面查看起来非常不方便。LayoutAlgorithm是一个枚举，用来控制html的布局，总共有三种类型：
        NORMAL：正常显示，没有渲染变化。
        SINGLE_COLUMN：把所有内容放到WebView组件等宽的一列中。
        NARROW_COLUMNS：可能的话，使所有列的宽度不超过屏幕宽度。*/
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        //设置是否支持缩放，我这里为false，默认为true。
        settings.setSupportZoom(false);
        //置是否显示缩放工具，默认为false。
        settings.setBuiltInZoomControls(false);
        //设置webview推荐使用的窗口 设置加载进来的页面自适应手机屏幕
        settings.setUseWideViewPort(false);
        settings.setLoadWithOverviewMode(true);
        //默认的是false，也就是说WebView默人不支持新窗口，但是这个不是说WebView不能打开多个页面了，只是你点击页面上的连接，当它的target属性是_blank时。它会在当前你所看到的页面继续加载那个连接。而不是重新打开一个窗口。
        //当你设置为true时，就代表你想要你的WebView支持多窗口，但是一旦设置为true，必须要重写WebChromeClient的onCreateWindow方法。
        settings.setSupportMultipleWindows(false);
        // 启用缓存 默认关闭，即，H5的缓存无法使用。
        settings.setAppCacheEnabled(true);
        // 开启数据库缓存
        settings.setDatabaseEnabled(true);
        // 开启Dom缓存
        settings.setDomStorageEnabled(true);
        // 表示支持js，如果想让java和js交互或者本身希望js完成一定的功能请把false改为true。
        settings.setJavaScriptEnabled(true);
        // 启用地理定位
        settings.setGeolocationEnabled(true);
        // 设置缓存大小
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setAppCachePath(this.getContext().getDir("appcache", 0).getPath());
        settings.setDatabasePath(this.getContext().getDir("databases", 0).getPath());
        settings.setGeolocationDatabasePath(this.getContext().getDir("geolocation", 0).getPath());
    }

}
