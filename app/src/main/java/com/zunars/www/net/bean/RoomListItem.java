package com.zunars.www.net.bean;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by lizhug on 2016/4/13.
 */
public class RoomListItem extends Entity {
    private int room_id;         //房间ID
    private String image;        //房屋图片资源地址
    private String room_name;
    private String type_name ;//rentType
    private String district_name;
    private String area_name;
    private String community;
    private int bed_room;
    private int hall_room;
    private int bath_room;
    private double square;
    private int curFloor;
    private int totalFloor;
    private int payment;     //房屋价格
    private String[] tag;          //房屋亮点

//    public Subway getSub() {
//        return sub;
//    }
//
//    public void setSub(Subway subway) {
//        this.sub = subway;
//    }
private String Sub;

    public String getSub() {
        return Sub;
    }

    public void setSub(String subway) {
        this.Sub = subway;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

  //  private  Subway sub;
    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public int getBath_room() {
        return bath_room;
    }

    public void setBath_room(int bath_room) {
        this.bath_room = bath_room;
    }

    public int getBed_room() {
        return bed_room;
    }

    public void setBed_room(int bed_room) {
        this.bed_room = bed_room;
    }

 

    public int getCurFloor() {
        return curFloor;
    }

    public void setCurFloor(int curFloor) {
        this.curFloor = curFloor;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public int getHall_room() {
        return hall_room;
    }

    public void setHall_room(int hall_room) {
        this.hall_room = hall_room;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

 
    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public String[] getTag() {
        return tag;
    }

    public void setTag(String[] tag) {
        this.tag = tag;
    }

    public int getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(int totalFloor) {
        this.totalFloor = totalFloor;
    }

    public String getCommunity_name() {
        return community;
    }

    public void setCommunity_name(String community_name) {
        this.community = community_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    @Override
    public String toString() {
        return "RoomListItem{" +
                "area_name='" + area_name + '\'' +
                ", room_id=" + room_id +
                ", image='" + image + '\'' +
                ", room_name='" + room_name + '\'' +
                ", rentType='" + type_name + '\'' +
                ", district_name='" + district_name + '\'' +
                ", communityName='" + community + '\'' +
                ", bed_room=" + bed_room +
                ", hall_room=" + hall_room +
                ", bath_room=" + bath_room +
                ", square=" + square +
               // ",subway"+subway+
                ", curFloor=" + curFloor +
                ", totalFloor=" + totalFloor +
                ", payment=" + payment +
                ", tag=" + Arrays.toString(tag) +
                '}';
    }

    public  static RoomListItem parse(String string,String resDomain){
       try{
           
      
      JSONObject result = new JSONObject(string);
        Gson gson=new Gson();
        Log.i("miao","begin gson");
        RoomListItem roomListItem=   gson.fromJson(string,RoomListItem.class);
       Log.i("miao","           subway"+result.getString("subway"));
   //  roomListItem.setSub(result.getJSONObject("subway").getString("subway"));
    //       Log.i("miao","           subway"+roomListItem.getSub());

           roomListItem.curFloor = result.getJSONArray("floor").getInt(0);
       roomListItem.totalFloor = result.getJSONArray("floor").getInt(1);
           roomListItem.image = resDomain + result.getJSONObject("cover_pictures").getString("url").toString() + "!small" ; 
          Log.i("miao",roomListItem.toString());
           roomListItem.id=roomListItem.room_id;
           return  roomListItem;
//
   } catch(Exception e){
           return  null;
       }
    
       
    }
//    public  class Subway {
//        private int   id;
//        private String   name;
//        private String  pid;
//        private String  subway;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getPid() {
//            return pid;
//        }
//
//        public void setPid(String pid) {
//            this.pid = pid;
//        }
//
//        public String getSubway() {
//            return subway;
//        }
//
//        public void setSubway(String subway) {
//            this.subway = subway;
//        }
//    }
}
