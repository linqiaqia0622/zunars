package com.zunars.www.model;

import android.util.Log;

import com.google.gson.Gson;
import com.zunars.www.net.bean.RoomItemList;

import java.util.List;

/**
 * Created by lizhug on 2016/4/13.
 */
public class RoomListItem {
    private int room_id;         //房间ID
    private String community;        //房屋图片资源地址
    private String district;        //房屋图片资源地址
    private String area;
    private String community_id;
    private String payment;
    private String bed_room;
    private String hall_room;
    private String bath_room;
    private List<Picture> pictures;
    private String room_number;
    private String room_name;
    private String ctime;
    //private String floor;
    private String square;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBath_room() {
        return bath_room;
    }

    public void setBath_room(String bath_room) {
        this.bath_room = bath_room;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    private String flsquareoor;
    private String direction;
    private String longitude;
    private String latitude;
    private String pledge_type;
    private String rent_type;
    private String identity;
    private String wechat_bind;
    private String direction_name;
    private String type_name;
    private String pledge_type_name;
    private String area_name;
    private String district_name;

 

    private Subway subway;
private Picture cover_pictures;
   


    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getBed_room() {
        return bed_room;
    }

    public void setBed_room(String bed_room) {
        this.bed_room = bed_room;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(String community_id) {
        this.community_id = community_id;
    }

    public Picture getCover_pictures() {
        return cover_pictures;
    }

    public void setCover_pictures(Picture cover_pictures) {
        this.cover_pictures = cover_pictures;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection_name() {
        return direction_name;
    }

    public void setDirection_name(String direction_name) {
        this.direction_name = direction_name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }
//
//    public String getFloor() {
//        return floor;
//    }
//
//    public void setFloor(String floor) {
//        this.floor = floor;
//    }

    public String getFlsquareoor() {
        return flsquareoor;
    }

    public void setFlsquareoor(String flsquareoor) {
        this.flsquareoor = flsquareoor;
    }

    public String getHall_room() {
        return hall_room;
    }

    public void setHall_room(String hall_room) {
        this.hall_room = hall_room;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public String getPledge_type() {
        return pledge_type;
    }

    public void setPledge_type(String pledge_type) {
        this.pledge_type = pledge_type;
    }

    public String getPledge_type_name() {
        return pledge_type_name;
    }

    public void setPledge_type_name(String pledge_type_name) {
        this.pledge_type_name = pledge_type_name;
    }

    public String getRent_type() {
        return rent_type;
    }

    public void setRent_type(String rent_type) {
        this.rent_type = rent_type;
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

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public Subway getSubway() {
        return subway;
    }

    public void setSubway(Subway subway) {
        this.subway = subway;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getWechat_bind() {
        return wechat_bind;
    }

    public void setWechat_bind(String wechat_bind) {
        this.wechat_bind = wechat_bind;
    }  public static class Subway {
        private int   id;
        private String   name;
        private String  pid;
        private String  subway;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getSubway() {
            return subway;
        }

        public void setSubway(String subway) {
            this.subway = subway;
        }
    }public static class Picture {
        private String url;
        private String hash;
        private String name;
        private String index;

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
