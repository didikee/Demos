package com.didikee.demos.dao.js;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by didik 
 * Created time 2016/12/19
 * Description: 
 */

public class JSHandler {

    private final Context context;

    public JSHandler(Context context) {
        this.context=context;
    }

    @JavascriptInterface
    public void doSomething(){
        Log.e("test","click no params!");
        Toast.makeText(context, "doSomething", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void doParams(String str){
        Log.e("test","click some params! params: " + str);
    }

    @JavascriptInterface
    public void doTitle(String str){
        Log.e("test","title: " + str);
    }
}
