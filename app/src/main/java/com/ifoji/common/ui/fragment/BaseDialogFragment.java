package com.ifoji.common.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.ifoji.common.ui.interfaces.OnDialogClickListener;
import com.ifoji.common.util.Logs;
import com.ifoji.common.util.MyToast;

/**
 * 利用DialogFragment创建Dlg的基类
 * Created by sqq on 16/8/26.
 */
public abstract class BaseDialogFragment extends DialogFragment {

    protected boolean isCancelableOnBack = true;
    //    protected boolean isCancelableOnTouchOutside = false;
    protected FragmentManager fragmentManager;
    protected String tag;
    protected FragmentActivity activity;
    private boolean isDlgShowing = true;//dlg是否正在显示，保存生命周期变化后，原dlg的显示状态
    protected boolean isRestoreDlg = true;//标记是否使用DialogFragment的恢复dlg显示的功能（即当Fragment存在但是dlg已消失，假如由于home锁屏等操作后，生命周期发生变化，在OnStart中会恢复Dlg的显示）

    protected CharSequence dlgTitle, dlgBtnPositive, dlgBtnNegative;
    protected OnDialogClickListener mOnDialogClickListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    @Deprecated
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = createDialog(savedInstanceState);
        if (!isCancelableOnBack) {
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    return keyCode == KeyEvent.KEYCODE_BACK;
                }
            });
        }
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logs.e("onCreate: DlgFragment is Created");
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!isRestoreDlg && !isDlgShowing) {
            getDialog().dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logs.e("onDestroy: DlgFragmnet is Destroied");
    }

    @Override
    public void onStop() {
        if (getDialog() != null) {
            isDlgShowing = getDialog().isShowing();
        }
        super.onStop();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (isAdded()) {
            manager.beginTransaction().remove(this).commitAllowingStateLoss();
        }
        if (manager == null) {
            MyToast.show("FragmentManager is null");
            Logs.e("show: FragmentManager is null");
            return;
        }
        if (TextUtils.isEmpty(tag)) tag = BaseDialogFragment.class.getSimpleName();
        setFragmentManager(manager);
        setTag(tag);
        super.show(manager, tag);
    }

    /**
     * 按back键是否可以取消dialogFragment，
     */
    public BaseDialogFragment setCancelableOnBack(boolean isCancelable) {
        this.isCancelableOnBack = isCancelable;
        return this;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public BaseDialogFragment setDlgTitle(CharSequence dlgTitle) {
        this.dlgTitle = dlgTitle;
        return this;
    }

    public BaseDialogFragment setDlgBtnPositive(CharSequence dlgBtnPositive) {
        this.dlgBtnPositive = dlgBtnPositive;
        return this;
    }

    public BaseDialogFragment setDlgBtnNegative(CharSequence dlgBtnNegative) {
        this.dlgBtnNegative = dlgBtnNegative;
        return this;
    }

    public BaseDialogFragment setOnDialogClickListener(OnDialogClickListener dialogClickListener) {
        this.mOnDialogClickListener = dialogClickListener;
        return this;
    }

    public abstract Dialog createDialog(Bundle savedInstanceState);
}
