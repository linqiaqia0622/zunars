package com.zunars.www.zunars;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zunars.www.adapter.SectionsPagerAdapter;
import com.zunars.www.fragment.FavoriteFragment;
import com.zunars.www.fragment.HomeFragment;
import com.zunars.www.fragment.MessageFragment;
import com.zunars.www.fragment.ReservationFragment;
import com.zunars.www.fragment.UserFragment;
import com.zunars.www.model.RoomListItem;
import com.zunars.www.net.bean.RoomItemList;
import com.zunars.www.net.core.ApiHttp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

import butterknife.BindString;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //Tab选项卡的文字
    private String mTextviewArray[] = {"首页", "消息", "好友", "广场", "更多"};
    BottomNavigationBar bottomNavigationBar;
    ViewPager mViewPager;
    String res=null;
    @BindString(R.string.action_settings)
    public String miao;
    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.home, R.drawable.home, R.drawable.home,
            R.drawable.home, R.drawable.home};
private SectionsPagerAdapter mSectionsPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            
       
        ApiHttp.getRoomList(this, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.i("miao","  onfailure request"+request.toString());
                Log.i("miao","  onfailure e"+e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
              
                Log.i("miao","   onResponse"+res);
             res=response.body().string();
                   
              
                Gson gson = new Gson(); 
                Log.i("miao","Gson");
                RoomItemList d = RoomItemList.parse(res);
              //  Log.i("miao",((RoomListItem)d.getRoomList().get(1)).getArea_name());
            
            }
        }); }catch (Exception e){
            
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        Log.i("miao","dd"+miao);
        mViewPager=(ViewPager)findViewById(R.id.container);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
            List<Fragment> lf = new ArrayList<>();
        // lf.add( new view1_fragment());
        lf.add( new HomeFragment());
        lf.add( new MessageFragment());
        lf.add( new UserFragment());
        lf.add( new FavoriteFragment());
        lf.add( new ReservationFragment());

     

        mSectionsPagerAdapter = new SectionsPagerAdapter(lf, getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        initBottomNavigationBar();
        
    }

    public void initBottomNavigationBar() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        for (int i = 0; i < 5; i++) {
            bottomNavigationBar.addItem(new BottomNavigationItem(mImageViewArray[i], mTextviewArray[i]));
        }
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                mViewPager.setCurrentItem(position);
            }
            @Override
            public void onTabUnselected(int position) {
            }
            @Override
            public void onTabReselected(int position) {
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                
            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_CLASSIC);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar
                .setActiveColor(R.color.colorGreen)
                .setInActiveColor("#FFFFFF")
                .setBarBackgroundColor("#ECECEC"); 
        
          bottomNavigationBar.initialise();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
