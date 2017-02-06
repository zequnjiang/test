package com.ifoji.common.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


import com.ifoji.R;
import com.ifoji.common.bll.ActivityManager;
import com.ifoji.common.ui.interfaces.OnBackClickListener;
import com.ifoji.common.ui.interfaces.OnLoadClickListener;
import com.ifoji.common.ui.view.LoadFrameLayout;
import com.ifoji.common.ui.view.SystemBarHelper;
import com.ifoji.common.ui.view.ToolBarHelper;
import com.ifoji.common.util.AnimUtil;
import com.ifoji.common.util.ViewFindUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by Wen
 * Email: wenpeng@qianshengqian.com
 * Date: 2016/12/20
 * Description：
 */

public abstract class KFQBaseActivity extends AppCompatActivity {

    protected Activity mActivity;
    protected FragmentManager mFragmentManager;
    protected Bundle mSavedInstanceState;

    protected boolean mPageIsShow;

    protected View mRootView;
    private ToolBarHelper mToolBarHelper;
    protected Toolbar mToolbar;

    protected LoadFrameLayout mLoadFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
        AnimUtil.activityAnimIn(this);
        mActivity = this;
        mFragmentManager = getSupportFragmentManager();
        setContentView(initLayout());
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        bindIOC();
        registEventBus();
        initVariable();
        initView();
        initData();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        initLoadingView(layoutResID);
        if(isShowToolBar()) {
            setContentView(mRootView);
            initToolBar();
        } else {
            super.setContentView(layoutResID);
        }
    }

    /**
     * Init - Toolbar
     * @param
     */
    private void initToolBar() {
        initStatusBar(R.color.color_DBDBDB);

        /*把 toolbar 设置到Activity 中*/
        setSupportActionBar(mToolbar);
        /*自定义的一些操作*/
        onCreateCustomToolBar(mToolbar);
    }

    /**
     * 状态栏处理
     */
    private void initStatusBar(int resId) {
        SystemBarHelper.tintStatusBar(this, getResources().getColor(resId));
        SystemBarHelper.setStatusBarDarkMode(this);
    }

    /**
     * Init Loading
     */
    private void initLoadingView(int layoutResID) {
        mToolBarHelper = new ToolBarHelper(this, layoutResID);
        mToolbar = mToolBarHelper.getToolBar();
        mRootView = mToolBarHelper.getContentView();

        if (isShowLoading()) {
            mLoadFrameLayout = ViewFindUtils.findView(mRootView, R.id.load_root_view);
            mLoadFrameLayout.showLoadingView();
            mLoadFrameLayout.setOnLoadClickListener(new OnLoadClickListener() {
                @Override
                public void retryLoad() {
                    mLoadFrameLayout.showLoadingView();
                    onRetryLoading();
                }
            });
        }

    }

    public void onCreateCustomToolBar(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.mipmap.app_back_material_black);
        toolbar.setContentInsetsRelative(0, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager.registShowing();
        mPageIsShow = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityManager.unRegistShowing();
        mPageIsShow = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindIOC();
        unRegistEventBus();
    }

    @Override
    public void finish() {
        super.finish();
        AnimUtil.activityAnimFinish(this);
    }

    private void bindIOC() {
        ButterKnife.bind(this);
    }

    private void unBindIOC() {
        ButterKnife.unbind(this);
    }

    private void registEventBus() {
        if(useEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    private void unRegistEventBus() {
        if(useEventBus()) {
            if(EventBus.getDefault().isRegistered(this)){
                EventBus.getDefault().unregister(this);
            }
        }
    }

    protected String getResStr(int strId) {
        return getResources().getString(strId);
    }

    protected void onRetryLoading() {

    }

    /**
     * 是否使用 EventBus - 默认不处理
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }

    /**
     * 是否显示Toolbar - 默认是显示的
     * @return
     */
    protected boolean isShowToolBar() {
        return true;
    }

    /**
     * 是否显示 Loading - 默认不现实
     * @return
     */
    protected boolean isShowLoading() {
        return false;
    }

    public abstract int initLayout();
    public abstract void initVariable();
    public abstract void initView();
    public abstract void initData();


    private OnBackClickListener mOnBackClickListener;

    public void setBackClick(OnBackClickListener pOnBackClickListener) {
        mOnBackClickListener = pOnBackClickListener;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mOnBackClickListener != null) {
                mOnBackClickListener.doBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
