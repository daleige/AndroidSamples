package com.cyq.tbs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * auth:ChenYangQi
 */
public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private WebView webView;
    private Button mBtnFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressbar);
        webView = findViewById(R.id.webview);
        mBtnFile = findViewById(R.id.btn_chow_file);

        mBtnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReadFileActivity.class));
            }
        });


        initWebView();
    }

    public void showCustomFileReaderView(View view) {
        startActivity(new Intent(this, CustomActivity.class));
    }

    private void initWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setSupportMultipleWindows(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
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
        //播放视频
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