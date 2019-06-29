package com.cyq.tbs;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.ProgressBar;

import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressbar);
        webView = findViewById(R.id.webview);
        initWebView();
    }

    private void initWebView() {
        WebSettings settings = webView.getSettings();           //和系统webview一样
        settings.setJavaScriptEnabled(true);                    //支持Javascript 与js交互
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        settings.setAllowFileAccess(true);                      //设置可以访问文件
        settings.setSupportZoom(true);                          //支持缩放
        settings.setBuiltInZoomControls(true);                  //设置内置的缩放控件
        settings.setUseWideViewPort(true);                      //自适应屏幕
        settings.setSupportMultipleWindows(true);               //多窗口
        settings.setDefaultTextEncodingName("utf-8");            //设置编码格式
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);       //缓存模式
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
//                super.onReceivedSslError(webView, sslErrorHandler, sslError);
                sslErrorHandler.proceed();//忽略SSL证书错误
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                return super.shouldInterceptRequest(webView, webResourceRequest);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
                                       @Override
                                       public boolean onJsAlert(WebView webView, String s, String s1, JsResult jsResult) {
                                           return super.onJsAlert(webView, s, s1, jsResult);
                                       }

                                       @Override
                                       public void onReceivedTitle(WebView webView, String s) {
                                           super.onReceivedTitle(webView, s);
                                       }

                                       @Override
                                       public void onProgressChanged(WebView webView, int progress) {
                                           super.onProgressChanged(webView, progress);
                                           progressBar.setProgress(progress); //设置进度条

                                       }
                                   }
        );
        webView.loadUrl("http://www.baidu.com");
//        webView.loadUrl("https://quan.qq.com/video/1098_bb0279e62a0e493732a65f8223b6da92");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView != null && webView.canGoBack()) {
            if ((keyCode == KeyEvent.KEYCODE_BACK) && webView != null && webView.canGoBack()) {
                webView.goBack();
                return true;
            } else {
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}