package com.zunars.www.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.zunars.www.adapter.RoomListAdapter;
import com.zunars.www.adapter.RoomListRecyclerViewAdapter;
import com.zunars.www.base.BaseItemFragment;
import com.zunars.www.model.RoomListItem;
import com.zunars.www.net.core.ApiHttp;
import com.zunars.www.zunars.Zunars;

import java.util.List;

/**
 * Created by 洽洽 on 2016/4/19.
 */
public class HomeListFragment extends BaseItemFragment{

    public  RecyclerView.Adapter getAdapter(List list, OnListFragmentInteractionListener no){
    return  new RoomListRecyclerViewAdapter(((Zunars)getActivity().getApplication()),list,no);
        
    } ;
    public  void loadList( int pageIndex, boolean isRefresh, Callback callback){
        try{
            Log.i("chinamiao","loadlist" +
                    "jkhsdfhjlkfsdghjlkfsdgjk");
            ApiHttp.getRoomList(getContext(), callback);



        }catch ( Exception e){
            Log.i("chinamiao","newloadListException");
        }
    };
}

