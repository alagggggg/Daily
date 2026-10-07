package com.nhatkytimestiming.app;
import android.annotation.SuppressLint;import android.app.Activity;import android.os.Bundle;import android.webkit.*;
public class MainActivity extends Activity{
 @SuppressLint({"SetJavaScriptEnabled","AddJavascriptInterface"}) public void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);WebView web=findViewById(R.id.webView);WebSettings s=web.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);web.addJavascriptInterface(new Bridge(),"AndroidBridge");web.setWebViewClient(new WebViewClient());web.loadUrl("file:///android_asset/index.html");}
 public class Bridge{
  @JavascriptInterface public void syncState(String json){getSharedPreferences(ActivityWidgetProvider.DATA_PREFS,MODE_PRIVATE).edit().putString(ActivityWidgetProvider.STATE_KEY,json==null?"{}":json).commit();ActivityWidgetProvider.refreshAll(MainActivity.this);}
  @JavascriptInterface public String loadState(){return getSharedPreferences(ActivityWidgetProvider.DATA_PREFS,MODE_PRIVATE).getString(ActivityWidgetProvider.STATE_KEY,"");}
 }
}
