package com.zunars.www.net.core;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;


import java.io.File;

/**
 * Created by 洽洽 on 2016/4/18.
 */
public class contextNet extends Application {
    private String saveImagePath;//保存图片路径
    /**
     * 检测网络是否可用
     * @return
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }
    @Override
    public void onCreate() {

        super.onCreate();
        initOkHttp();
        //注册App异常崩溃处理器


      
    }
    private void  initOkHttp(){
        File sdcache =this.getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        //   Cache cache = new Cache(sdcache, cacheSize);
        ApiHttp.mOkHttpClient=new OkHttpClient();
        ApiHttp.mOkHttpClient.setCache(new Cache(sdcache.getAbsoluteFile(), cacheSize));


        // ApiHttp. mOkHttpClient.setCache(cache);



    }
    /**
     * 初始化
     */
   
}
