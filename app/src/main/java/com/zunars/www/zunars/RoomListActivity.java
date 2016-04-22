package com.zunars.www.zunars;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.Text;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.internal.spdy.FrameReader;
import com.umeng.analytics.MobclickAgent;
import com.zunars.www.adapter.RoomListAdapter;
import com.zunars.www.adapter.SectionsPagerAdapter;
import com.zunars.www.base.BaseItemFragment;
import com.zunars.www.expand_tab.ExpandTabView;
import com.zunars.www.expand_tab.ViewLeft;
import com.zunars.www.expand_tab.ViewMiddle;
import com.zunars.www.expand_tab.ViewRight;
import com.zunars.www.fragment.FavoriteFragment;
import com.zunars.www.fragment.HomeFragment;
import com.zunars.www.fragment.HomeListFragment;
import com.zunars.www.fragment.MessageFragment;
import com.zunars.www.fragment.ReservationFragment;
import com.zunars.www.fragment.UserFragment;
import com.zunars.www.model.RoomItem;
import com.zunars.www.model.RoomListItem;
import com.zunars.www.net.bean.Entity;
import com.zunars.www.net.bean.RoomItemList;
import com.zunars.www.net.core.ApiHttp;
import com.zunars.www.presenter.impl.SetuoPresenterImpl;
import com.zunars.www.presenter.interfaces.SetupTabPresenter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Handler;

import javax.net.ssl.HttpsURLConnection;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RoomListActivity extends AppCompatActivity implements BaseItemFragment.OnListFragmentInteractionListener {
    public android.os.Handler handler = new android.os.Handler() {
        public void handleMessage(Message mes) {
            Log.i("chinadog", "handlemessage");
        }
    };
    //  private MapView mapView;
    private AMap aMap;
    private String res;
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private ViewLeft viewLeft;
    private ViewMiddle viewMiddle;
    private ViewRight viewRight;
    @Bind(R.id.room_list_view_pager)
    ViewPager mViewPager;
    @Bind(R.id.tab_area)
    ExpandTabView tab_area;
    HomeListFragment homeListFragment;
//    @Bind(R.id.tab_price)
//    ExpandTabView tab_price;
//    @Bind(R.id.tab_type)
//    ExpandTabView tab_type;
//    @Bind(R.id.tab_choice)
   // ExpandTabView tab_choice;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        ButterKnife.bind(this);
        Log.i("miao", "oncreate   +Roomlistativity");
        //设置Toolbar栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  menu_area=(ExpandTabView)findViewById(R.id.menu_area); 
      
//        set.SetupTabPresenter(tab_price,null,new ArrayList<View>());
//     //   mViewArray.add(new TextView(this));
//        set.SetupTabPresenter(tab_type,null,new ArrayList<View>());
//        set.SetupTabPresenter(tab_choice,null,new ArrayList<View>());
        //异步更新房源列表

        AsyncFetchRoomListTask fetchRoomList = new AsyncFetchRoomListTask();
        //  fetchRoomList.execute();


        //初始化高德地图
        //  mapView = (MapView) findViewById(R.id.map);
        //   mapView.onCreate(savedInstanceState);
        initMap();
        List<Fragment> lf = new ArrayList<>();
        homeListFragment=new HomeListFragment();
        lf.add(homeListFragment);


        mSectionsPagerAdapter = new SectionsPagerAdapter(lf, getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter); 
        setSupportActionBar(toolbar);
        SetupTabPresenter set=new SetuoPresenterImpl(this);
        set.SetupTabPresenter(tab_area,homeListFragment,new ArrayList<View>());
//        try {
//
//
//            ApiHttp.getRoomList(this, new Callback() {
//                @Override
//                public void onFailure(Request request, IOException e) {
//                    Log.i("miao", "  onfailure request" + request.toString());
//                    Log.i("miao", "  onfailure e" + e.toString());
//                }
//
//                @Override
//                public void onResponse(Response response) throws IOException {
//
//                    Log.i("miao", "   onResponse" + res);
//                    res = response.body().string();
//
//
//                    Gson gson = new Gson();
//                    Log.i("miao", "Gson");
//                    final RoomItemList d = RoomItemList.parse(res);
//                    Message message = Message.obtain(handler, new Runnable() {
//                        @Override
//                        public void run() {
//
//                            //  ListView mListView = (ListView) findViewById(R.id.room_list_view);
//                            //  RoomListAdapter adapter = new RoomListAdapter(RoomListActivity.this, d.getRoomList());
//                            //   mListView.setAdapter(adapter);
//                        }
//                    });
//                    message.sendToTarget();
//                    //  Log.i("miao",((RoomListItem)d.getRoomList().get(1)).getArea_name());
//
//                }
//            });
//        } catch (Exception e) {
//
//        }
        //设置返回事件
        TextView mBackBtn = (TextView) findViewById(R.id.back_btn);
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RoomListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //切换成列表模式
        TextView mSwitchBtn = (TextView) findViewById(R.id.switch_show_type_btn);
        mSwitchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_room_list);

                //设置Toolbar栏
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                //设置返回事件
                TextView mBackBtn = (TextView) findViewById(R.id.back_btn);
                mBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(RoomListActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }

    /**
     * 初始化AMap对象
     */
    private void initMap() {
        if (aMap == null) {
            //  aMap = mapView.getMap();
        }
    }

    @Override
    public void onListFragmentInteraction(Entity item) {

    }


    //获取房源列表
    public class AsyncFetchRoomListTask extends AsyncTask<Integer, Integer, ArrayList<RoomListItem>> {
        @Override
        protected ArrayList<RoomListItem> doInBackground(Integer... params) {

            URL url = null;
            HttpURLConnection connection = null;
            BufferedReader buffer = null;
            ArrayList<RoomListItem> arrayList = new ArrayList<RoomListItem>();

            try {
                url = new URL("https://www.zunars.com/room/fetch_room_list");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                if (connection.getResponseCode() == 200) {
//                   // Log.v("TAG", "200");
//                    buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                    StringBuilder responseStrBuilder = new StringBuilder();
//                    String line;
//                    while((line =  buffer.readLine()) != null){
//                        responseStrBuilder.append(line);
//                    }
//
//                    JSONObject result = new JSONObject(responseStrBuilder.toString());
//                   // Log.v("TAG", result.get("rooms").toString());
//
//                    JSONArray roomList =  result.getJSONArray("rooms");
//
//                    String resDomain = result.getString("RESOURCE_DOMAIN");
//                    //Log.v("TAG", resDomain);
//
//                    for (int i = 0; i < roomList.length(); i++) {
//                        RoomListItem tmpRoomItem = new RoomListItem();
////                        JSONObject tmpRoomObject = roomList.getJSONObject(i);
////                        tmpRoomItem.roomName = tmpRoomObject.getString("room_name");            //房间名字
////                        tmpRoomItem.districtName = tmpRoomObject.getString("district_name");            //区域名字
////                        tmpRoomItem.areaName = tmpRoomObject.getString("area_name");            //区域名字
////                        tmpRoomItem.communityName = tmpRoomObject.getString("district_name");            //区域名字
////                        tmpRoomItem.rentType = tmpRoomObject.getString("type_name");            //区域名字
////                        tmpRoomItem.price = tmpRoomObject.getInt("payment");            //区域名字
////                        tmpRoomItem.square = tmpRoomObject.getDouble("square");            //区域名字
////                        tmpRoomItem.roomId = tmpRoomObject.getInt("room_id");            //区域名字
////                        tmpRoomItem.bedRoom = tmpRoomObject.getInt("bed_room");            //区域名字
////                        tmpRoomItem.hallRoom = tmpRoomObject.getInt("hall_room");            //区域名字
////                        tmpRoomItem.bathRoom = tmpRoomObject.getInt("bath_room");            //区域名字
////                        tmpRoomItem.areaName = tmpRoomObject.getString("district_name");            //区域名字
////                        tmpRoomItem.curFloor = tmpRoomObject.getJSONArray("floor").getInt(0);
////                        tmpRoomItem.totalFloor = tmpRoomObject.getJSONArray("floor").getInt(1);
////
////                        tmpRoomItem.image = resDomain + tmpRoomObject.getJSONObject("cover_pictures").getString("url").toString() + "!small";            //区域名字
//
//                        arrayList.add(tmpRoomItem);
                    //  }

                    return arrayList;
                }

            } catch (Exception e) {
                Log.v("TAG", e.toString());
            }

            return null;

        }

        @Override
        protected void onPostExecute(ArrayList<RoomListItem> o) {
            super.onPostExecute(o);
            Log.v("TAG", "房源信息请求加载完毕");

            //初始化列表
            //  ListView mListView = (ListView) findViewById(R.id.room_list_view);
            // RoomListAdapter adapter = new RoomListAdapter(RoomListActivity.this, o);
            // mListView.setAdapter(adapter);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            //Log.v("TAG", "Process=================");
        }
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

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mapView.onDestroy();
    }


    
}
