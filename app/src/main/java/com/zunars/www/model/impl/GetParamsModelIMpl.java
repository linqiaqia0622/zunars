package com.zunars.www.model.impl;

import android.util.Log;

import com.zunars.www.model.interfaces.GetParamsModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 洽洽 on 2016/4/21.
 */
public class GetParamsModelIMpl implements GetParamsModel {
    @Override
    public Map<String, Object> getParamsByTAb(String type, String showText) {
        switch (type){
            case"be" :   return getBeParam(showText);
            case"pl":return  getPlPh(showText);
        }
        
         
       
        
        return null;
        
    }
   Map<String ,Object> getBeParam(String showText){
    int be = 0;
    switch (showText) {
        case "一居":
            be = 1;
            break;
        case "不限":
            be = 0;
            break;
        case "二居":
            be = 2;
            break;
        case "三居":
            be = 3;
            break;
        case "四居":
            be = 4;
            break;
        case "四居以上":
            be = 5;
            break;
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("be", be + "");
    Log.i("miao", "dlkddjklkj" + " " + be);
       return  map;
}
    private Map<String ,Object> getPlPh(String showText){  int pl = 0, ph = 100;
        switch (showText) {
            case "不限":
                break;
            case "10000以上":
                pl = 10000;
                ph = 100000;
                break;
            case "300~600":
                pl = 300;
                ph = 600;
                break;
            case "600~1000":
                pl = 600;
                ph = 1000;
                break;
            case "1000~1500":
                pl = 1000;
                ph = 15000;
                break;
            case "1500~2000":
                pl = 1500;
                ph = 20000;
                break;
            case "2000~3000":
                pl = 2000;
                ph = 3000;
                break;
            case "3000~4000":
                pl = 3000;
                ph = 4000;
                break;
            case "4000~5000":
                pl = 4000;
                ph = 5000;
                break;
            case "5000~7000":
                pl = 5000;
                ph = 7000;
                break;
            case "7000~10000":
                pl= 7000;
                ph= 1000;
                break;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pl",  pl + "");
        map.put("ph",  ph + "");
        Log.i("miao", "dlkddjklkj" + " " +  pl);
        return map;
    }}