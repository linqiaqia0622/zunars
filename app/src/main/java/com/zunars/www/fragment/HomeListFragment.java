package com.zunars.www.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Response;
import com.zunars.www.adapter.RoomListAdapter;
import com.zunars.www.adapter.RoomListRecyclerViewAdapter;
import com.zunars.www.base.BaseItemFragment;
import com.zunars.www.model.RoomListItem;
import com.zunars.www.net.bean.RoomItemList;
import com.zunars.www.net.bean.interfaces.PageList;
import com.zunars.www.net.core.ApiHttp;
import com.zunars.www.presenter.interfaces.UpdateView;
import com.zunars.www.zunars.Zunars;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by 洽洽 on 2016/4/19.
 */
public class HomeListFragment extends BaseItemFragment implements UpdateView{

    public  RecyclerView.Adapter getAdapter(List list, OnListFragmentInteractionListener no){
        RoomListRecyclerViewAdapter adapter=  new RoomListRecyclerViewAdapter(((Zunars)getActivity().getApplication()),list,no);
     adapter.setFooterListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           Map map=  new HashMap<String ,Object>();
             map.put("p",(int)map.get("p")+1);
             params.putAll(map);
             loadList(params,getCallback());
         }
     });
        return  adapter;   
    }

    @Override
    public void loadList(Map map, Callback callback) {
        try{


            ApiHttp.getRoomList(getContext(),map, callback);
        }catch(Exception e){

        }
    }

    @Override
    public void updateView(Map<String, Object> mapParams) {
        loadList(mapParams,getCallback()); 
    }
    @Override

    public  PageList parse(String response){
        return RoomItemList.parse(response);
    }
}

