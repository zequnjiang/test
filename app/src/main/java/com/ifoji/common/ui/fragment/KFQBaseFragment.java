package com.ifoji.common.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ifoji.common.ui.fragment.base.LazyFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by Wen
 * Email: wenpeng@qianshengqian.com
 * Date: 2016/12/20
 * Description：
 */
public abstract class KFQBaseFragment extends LazyFragment {

    protected View baseView;
    protected Activity mActivity;
    protected FragmentManager mFragmentManager;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        mActivity = getActivity();
        mFragmentManager = ((AppCompatActivity)mActivity).getSupportFragmentManager();
        baseView = inflater.inflate(initLayout(), null);
        setContentView(baseView);
        init();
    }

    private void init() {
        bindIOC();
        registEventBus();
        initVariable();
        initView();
        initData();
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        unBindIOC();
        unRegistEventBus();
    }

    protected String getResStr(int strId) {
        return getResources().getString(strId);
    }

    private void bindIOC() {
        ButterKnife.bind(this, baseView);
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

    /**
     * 是否使用 EventBus - 默认不处理
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }

    public abstract int initLayout();
    public abstract void initVariable();
    public abstract void initView();
    public abstract void initData();
}
