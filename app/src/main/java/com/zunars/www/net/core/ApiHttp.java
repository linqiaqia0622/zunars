package com.zunars.www.net.core;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.zunars.www.net.bean.URLs;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 洽洽 on 2016/1/17.
 */
public class ApiHttp {
    public static final String UTF_8 = "UTF-8";
    public static final String DESC = "descend";
    public static final String ASC = "ascend";

    private final static int TIMEOUT_CONNECTION = 20000;
    private final static int TIMEOUT_SOCKET = 20000;
    private final static int RETRY_TIME = 3;

    private static String appCookie;
    private static String appUserAgent;
  public static  OkHttpClient mOkHttpClient ;
    public static void cleanCookie() {
        appCookie = "";
    }


    private static String _MakeURL(String p_url, Map<String, Object> params) {
        StringBuilder url = new StringBuilder(p_url);
        if (url.indexOf("?") < 0)
            url.append('?');

        for (String name : params.keySet()) {
            url.append('&');
            url.append(name);
            url.append('=');
            url.append(String.valueOf(params.get(name)));
            //不做URLEncoder处理
            //url.append(URLEncoder.encode(String.valueOf(params.get(name)), UTF_8));
        }
          Log.i("miao",url.toString().replace("?&", "?"));
        return url.toString().replace("?&", "?");
    }

    public static void getHttpClient(Context context,String url ,Callback callback) {
        getHttpClient(context, url, callback,false);
    }
    public static void getHttpClient(Context context,String url ,Callback callback,boolean isRefresh) {
        final Request request;
        Log.i("miao","res");
        //创建okHttpClient对象
    if(isRefresh){
        
   
//创建一个Request
       request = getRequest(url); }
        else request=getNetRequest(url);
        Log.i("miao",request.toString());

        Call call = mOkHttpClient.newCall(request);

//请求加入调度
        call.enqueue(callback);
       
        Log.i("miao","finally2");
        return ;
    }
    /**
     * 获取帖子列表
     * @param context
    // * @param 
    // * @param 
     * @return
     * @throws AppException
     */
    public static void getRoomList(Context context , Map<String, Object> params, Callback callback) throws AppException {
        String newUrl = _MakeURL(URLs.ROOM_LIST, params);
        Log.i("miao","Api http+ getRoomlist");
        getHttpClient(context,newUrl,callback);
    }
    private static Request getRequest(String url) {
        return new Request.Builder()
                     .header("Cache-Control", "max-stale=3600")
                    .url(url)
             .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
    }  private static Request getNetRequest(String url) {
        return new Request.Builder()
                     .header("Cache-Control", "max-stale=3600")
                    .url(url)
              //.cacheControl(CacheControl.FORCE_CACHE)
                    .build();
    }

   
public static Bitmap getNetBitmap(String url){
    Request request = new Request.Builder().url(url).build();
    Bitmap bitmap=null;
    InputStream inputStream=null;
    try{
      
 // bitmap=new Bitmap()
        inputStream= mOkHttpClient.newCall(request).execute().body().byteStream();
        bitmap= BitmapFactory.decodeStream(inputStream);
    }
    catch (Exception e){
        
    }return bitmap;
}
   
    
}
