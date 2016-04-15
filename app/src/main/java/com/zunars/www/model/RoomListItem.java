package com.zunars.www.model;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lizhug on 2016/4/13.
 */
public class RoomListItem {
    public int roomId;         //房间ID
    public String image;        //房屋图片资源地址
    public String roomName;
    public String rentType;
    public String districtName;
    public String areaName;
    public String communityName;
    public int bedRoom;
    public int hallRoom;
    public int bathRoom;
    public double square;
    public int curFloor;
    public int totalFloor;
    public int price;     //房屋价格
    public String[] tag;          //房屋亮点
}
