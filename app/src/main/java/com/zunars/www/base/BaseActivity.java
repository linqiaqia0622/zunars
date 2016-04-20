package com.zunars.www.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.zunars.www.net.core.ApiHttp;

import butterknife.ButterKnife;

/**
 * Created by 洽洽 on 2016/4/18.
 */
public  abstract  class BaseActivity extends AppCompatActivity  {
    /**
     * 初始化布局
     */
    public abstract void initContentView();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化控制中心
     */
    public abstract void initPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
      
        initContentView();
        // 初始化View注入
        ButterKnife.bind(this);
        initPresenter();
        initView();
    }

   
}
