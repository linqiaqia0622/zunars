package com.zunars.www.net.bean;

/**
 * Created by 洽洽 on 2016/4/19.
 */
public class Center {
    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Center(String id, String lat, String lng) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
    }

    private String lng;
    private String        lat;
    private String      id;
}
