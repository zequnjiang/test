package com.ifoji.common.ui.interfaces;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by Wen
 * Email: wenpeng@qianshengqian.com
 * Date: 2016/12/22
 * Description：
 */
public interface OnUpdateListener {

    void onPositiveClicked(MaterialDialog dialog);

    void onNegativeClicked(MaterialDialog dialog, boolean isChecked);

}
