package com.zunars.www.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.zunars.www.model.RoomListItem;
import com.zunars.www.zunars.R;
import com.zunars.www.zunars.RoomDetailActivity;
import com.zunars.www.zunars.RoomListActivity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by lizhug on 2016/4/13.
 */
public class RoomListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<RoomListItem> data;
    DisplayImageOptions options;

    //viewholder优化
    static class ViewHolder {
        LinearLayout layout;
        ImageView image;
        TextView title;
        TextView subTitle;
        TextView price;
        String roomId;
    }

    //实例化
    public RoomListAdapter(Context context, ArrayList<RoomListItem> roomList) {
        this.context = context;
        this.data = roomList;

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(context);

        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "imageloader/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .memoryCacheExtraOptions(480, 800) // maxwidth, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY -2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2* 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100) //缓存的文件数量
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for releaseapp
                .build();//开始构建

       options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.test)            //加载图片时的图片
                .showImageForEmptyUri(R.drawable.test)         //没有图片资源时的默认图片
                .showImageOnFail(R.drawable.test)              //加载失败时的图片
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .considerExifParams(true)                          //启用EXIF和JPEG图像格式
                .displayer(new RoundedBitmapDisplayer(20))         //设置显示风格这里是圆角矩形
                .build();

        ImageLoader.getInstance().init(config);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        //Log.v("TAG", position + "");

        if (convertView == null) {
           convertView = LayoutInflater.from(context).inflate(R.layout.item_room_list, null);
            holder = new ViewHolder();
            holder.layout = (LinearLayout) convertView.findViewById(R.id.layout);
            holder.image = (ImageView) convertView.findViewById(R.id.image_view);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.subTitle = (TextView) convertView.findViewById(R.id.sub_title);
            holder.price = (TextView) convertView.findViewById(R.id.price);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RoomListItem item = (RoomListItem) getItem(position);

        holder.title.setText(item.districtName + " · " + item.areaName + " · " + item.roomName);
        holder.subTitle.setText(item.communityName + " · " + item.rentType + " · " + item.square + "㎡");
        holder.price.setText(item.price + "");
        holder.roomId = item.roomId + "";

        ImageLoader.getInstance().displayImage(item.image, holder.image, options);

        //设置列表点击事件，单击跳转到房源详情页面
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, RoomDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("room_id",  ((RoomListItem) getItem(position)).roomId + "");     //传递房源ID
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


        return convertView;
    }
}
