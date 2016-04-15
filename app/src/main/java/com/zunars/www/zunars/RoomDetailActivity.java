package com.zunars.www.zunars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.amap.api.maps2d.model.Text;
import com.umeng.analytics.MobclickAgent;
import com.zunars.www.model.RoomItem;

public class RoomDetailActivity extends AppCompatActivity {

    private String roomId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        //获取roomId的值
//        Intent intent = this.getIntent();
//        Bundle bundle = intent.getExtras();
//        roomId = bundle.getString("room_id");

        Log.v("TAG", "调用的房间ID" + roomId);

        //初始化房源详情数据
        asyncFetchRoomDetail();

        //返回按钮
        TextView mBackBtn = (TextView) findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("TAG", "finish1");
                RoomDetailActivity.this.finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.v("TAG", "finish2");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    //初始化房源数据
    private void asyncFetchRoomDetail() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        //mapView.onPause();
        MobclickAgent.onPause(this);
    }
}
