package com.didikee.demos.ui.act;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.didikee.demos.R;
import com.didikee.demos.dao.js.JSHandler;

public class WebTestActivity extends AppCompatActivity {

    private WebView webView;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_test);


        webView = ((WebView) findViewById(R.id.webView));
        tv_title = ((TextView) findViewById(R.id.tv_title));


        initWebViewParams();

    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void initWebViewParams() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);







        webView.addJavascriptInterface(new JSHandler(this){
            @JavascriptInterface
            @Override
            public void doTitle(final String str) {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_title.setText(str);
                    }
                });
            }
        },"mgxz");//JSHandler
        webView.loadUrl("file:///android_asset/testjava.html");
    }
}
