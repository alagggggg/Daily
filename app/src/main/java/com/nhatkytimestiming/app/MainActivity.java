package com.nhatkytimestiming.app;
import android.annotation.SuppressLint;import android.app.*;import android.content.*;import android.net.Uri;import android.os.Bundle;import android.util.Base64;import android.webkit.*;import java.io.*;
public class MainActivity extends Activity{
 private WebView web;private boolean pageReady=false;private ValueCallback<Uri[]> fileCallback;private byte[] pendingExcel;private static final int PICK=201,SAVE=202;
 @SuppressLint({"SetJavaScriptEnabled","AddJavascriptInterface"}) public void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);web=findViewById(R.id.webView);WebSettings s=web.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);web.addJavascriptInterface(new Bridge(),"AndroidBridge");web.setWebViewClient(new WebViewClient(){public void onPageFinished(WebView view,String url){pageReady=true;refreshFromNative();}});web.setWebChromeClient(new WebChromeClient(){public boolean onShowFileChooser(WebView v,ValueCallback<Uri[]> cb,FileChooserParams p){if(fileCallback!=null)fileCallback.onReceiveValue(null);fileCallback=cb;Intent i=new Intent(Intent.ACTION_OPEN_DOCUMENT).addCategory(Intent.CATEGORY_OPENABLE).setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");startActivityForResult(i,PICK);return true;}});web.loadUrl("file:///android_asset/index.html");}

 private void refreshFromNative(){if(web==null||!pageReady)return;web.post(()->web.evaluateJavascript("if(typeof refreshFromNativeState==='function'){refreshFromNativeState()}",null));}
 @Override protected void onResume(){super.onResume();refreshFromNative();}
 protected void onActivityResult(int request,int result,Intent data){super.onActivityResult(request,result,data);if(request==PICK){Uri u=result==RESULT_OK&&data!=null?data.getData():null;if(fileCallback!=null)fileCallback.onReceiveValue(u==null?null:new Uri[]{u});fileCallback=null;}else if(request==SAVE){if(result==RESULT_OK&&data!=null&&data.getData()!=null&&pendingExcel!=null)try(OutputStream o=getContentResolver().openOutputStream(data.getData(),"w")){o.write(pendingExcel);}catch(Exception ignored){}pendingExcel=null;}}
 public class Bridge{
  @JavascriptInterface public boolean syncState(String json){return ActivityWidgetProvider.commitStateIfNewer(MainActivity.this,json==null?"{}":json);}
  @JavascriptInterface public String loadState(){return getSharedPreferences(ActivityWidgetProvider.DATA_PREFS,MODE_PRIVATE).getString(ActivityWidgetProvider.STATE_KEY,"");}
  @JavascriptInterface public void saveExcel(String name,String data){pendingExcel=Base64.decode(data,Base64.DEFAULT);runOnUiThread(()->{Intent i=new Intent(Intent.ACTION_CREATE_DOCUMENT).addCategory(Intent.CATEGORY_OPENABLE).setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet").putExtra(Intent.EXTRA_TITLE,name.endsWith(".xlsx")?name:name+".xlsx");startActivityForResult(i,SAVE);});}
 }
}
