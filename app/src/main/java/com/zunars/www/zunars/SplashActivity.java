package com.zunars.www.zunars;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplashActivity extends AppCompatActivity {

    //Splash页面停留时间
    private static final Integer SPLASH_DISPLAY_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (isFirstLaunch(SplashActivity.this, SplashActivity.this.getClass().getName())) {
                    //首次启动进入Guide页面
                    intent = new Intent(SplashActivity.this, GuideActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }

                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_TIME);
    }


    //****************************************************************
    //判断应用是否首次加载，读取sharedPreferences中的guide_activity字段
    //****************************************************************
    private static final String SHAREDPREFERENCES_NAME = "first_pref";
    private static final String KEY_GUIDE_ACTIVITY = "isFirstLaunch";
    private boolean isFirstLaunch(Context context, String className) {
        if (context == null || className == null || "".equalsIgnoreCase(className)) {
            return false;
        }

        SharedPreferences mPreferences = context.getSharedPreferences(SHAREDPREFERENCES_NAME, MODE_PRIVATE);
        return mPreferences.getBoolean(KEY_GUIDE_ACTIVITY, true);
    }
}
