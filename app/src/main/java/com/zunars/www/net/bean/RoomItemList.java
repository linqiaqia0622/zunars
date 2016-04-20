package com.zunars.www.net.bean;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.zunars.www.net.bean.interfaces.PageList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 洽洽 on 2016/4/19.
 */
public class RoomItemList extends Entity  implements  PageList{
   
    private Center mCenter;
    private String code;
    private String  message;
    private String RESOURCE_DOMAIN;
    private int catalog=0;
    private int pageSize=0;
    private int newsCount=0;

    @Override
    public int getCount() {
        return newsCount;
    }

    @Override
    public List getList() {
        return getRoomList();
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRESOURCE_DOMAIN() {
        return RESOURCE_DOMAIN;
    }

    public void setRESOURCE_DOMAIN(String RESOURCE_DOMAIN) {
        this.RESOURCE_DOMAIN = RESOURCE_DOMAIN;
    }

    public String getCode() {
    
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Center getmCenter() {
        return mCenter;
    }

    public void setmCenter(Center mCenter) {
        this.mCenter = mCenter;
    }

    public List<RoomListItem> getRoomList() {
        return RoomList;
    }

    public void setRoomList(List<RoomListItem> roomList) {
        RoomList = roomList;
    }

    public RoomListPage getRoomListPage() {
        return roomListPage;
    }

    public void setRoomListPage(RoomListPage roomListPage) {
        
        this.roomListPage = roomListPage;
    }

    private RoomListPage roomListPage;
    private List<RoomListItem> RoomList = new ArrayList<RoomListItem>();
    public static RoomItemList  parse(String string){
        Log.i("miao","parse");
        RoomItemList roomItemList=new RoomItemList();
        Gson gson=new Gson();
        Log.i("miao","gson+try");
        JSONObject result=null;
        try{
            
        
        result = new JSONObject(string);
            List<RoomListItem> d;
            Log.i("miao","JSONObject");
       
             String centerString =  result.getString("center");
            Log.i("miao","JSdfdfdfdfdONObject");
            String resDomain = result.getString("RESOURCE_DOMAIN");

            JSONArray roomList=result.getJSONArray("rooms");
            Log.i("miao","JSOweweweweweweNObject");


            for (int i = 0; i < roomList.length(); i++) {
           //     RoomListItem tmpRoomItem = new RoomListItem();
                roomItemList.getRoomList().add(RoomListItem.parse(roomList.get(i).toString(),resDomain));    
              //  Log.i("miao","JSONObjdfdfdfdfdect"+roomList.get(i).toString());
            }
//                        JSONObject tmpRoomObject = roomList.getJSONObject(i);
            
            
            
             String pageString =  result.getString("page");
        Log.i("miao","rooms"+roomList );
           Type type=   new TypeToken<List<RoomListItem>>(){}.getType();
           Log.i("miao","getType"+type.toString());
         
            roomItemList.mCenter=  gson.fromJson(centerString,Center.class);
            roomItemList.setRoomListPage(  gson.fromJson(pageString,RoomListPage.class));
      //  d = gson.fromJson(miao.toString(),type);
   // Log.i("miao","gson.fromJson   gesdfgdfgsfdfdgtArea_name id"+d.get(1).getBed_room());

           
    } 
       catch (Exception e){
           Log.i("miao","Exception"+e.toString());

       }
//roomItemList.
      
        return  roomItemList;
    }
  
}
