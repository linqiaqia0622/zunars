package com.zunars.www.widget;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;


import com.kyleduo.switchbutton.SwitchButton;
import com.zunars.www.expand_tab.ExpandTabView;
import com.zunars.www.presenter.impl.SetuoPresenterImpl;
import com.zunars.www.presenter.interfaces.SetupTabPresenter;
import com.zunars.www.zunars.R;
import com.zunars.www.zunars.SplashActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;


public class textActivity extends ActionBarActivity {
  
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_head);
        BGABanner banner = (BGABanner)findViewById(R.id.banner_splash_pager);
        // 用Java代码方式设置切换动画
        banner.setTransitionEffect(BGABanner.TransitionEffect.Rotate);
        // banner.setPageTransformer(new RotatePageTransformer());
        // 设置page切换时长
        banner.setPageChangeDuration(1000);
        List<View> views = new ArrayList<>();
        views.add(getPageView(R.drawable.qia));
        views.add(getPageView(R.drawable.qia));
        views.add(getPageView(R.drawable.qia));

      //  View lastView = getLayoutInflater().inflate(R.layout.view_last, null);
//        views.add(lastView);
//        lastView.findViewById(R.id.btn_last_main).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                startActivity(new Intent(SplashActivity.this, MainActivity.class));
////                finish();
//            }
//        });
        banner.setViews(views);
        // banner.setCurrentItem(1);
    }

    private View getPageView(@DrawableRes int resid) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(resid);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
}
