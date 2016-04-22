package com.zunars.www.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.Callback;
import com.zunars.www.adapter.HomeRecyclerViewAdapter;
import com.zunars.www.adapter.RoomListRecyclerViewAdapter;
import com.zunars.www.base.BaseItemFragment;
import com.zunars.www.net.bean.RoomItemList;
import com.zunars.www.net.bean.interfaces.PageList;
import com.zunars.www.net.core.ApiHttp;
import com.zunars.www.zunars.R;
import com.zunars.www.zunars.Zunars;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lizhug on 2016/4/12.
 */
public class HomeFragment extends BaseItemFragment {
    public  RecyclerView.Adapter getAdapter(List list, OnListFragmentInteractionListener no){
        RoomListRecyclerViewAdapter adapter=  new RoomListRecyclerViewAdapter(((Zunars)getActivity().getApplication()),list,no);
       // adapter.setFooterListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Map map=  new HashMap<String ,Object>();
//                map.put("p",2);
//                params.putAll(map);
//                loadList(params,getCallback());
//            }
//        });
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

    public  PageList parse(String response){
        return RoomItemList.parse(response);
    }
}
