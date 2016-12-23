package com.didikee.demos.dao.huaban;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;

import com.didikee.demos.R;

/**
 * Created by didik 
 * Created time 2016/12/23
 * Description: 
 */

public class HuaBanDialog {
    private final Activity activity;
    private Dialog dialog;

    public HuaBanDialog(Activity activity) {
        this.activity = activity;
        initDialog();
    }

    private void initDialog() {
        dialog = new Dialog(activity, R.style.popupDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_huaban);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
//        dialog.show();
    }

    public void show(){
        dialog.show();
    }
}
