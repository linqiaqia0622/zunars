package com.zunars.www.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zunars.www.base.BaseItemFragment;
import com.zunars.www.net.bean.RoomListItem;
import com.zunars.www.net.bean.RoomListPage;
import com.zunars.www.util.BitmapManager;
import com.zunars.www.util.StringUtils;
import com.zunars.www.zunars.R;
import com.zunars.www.zunars.Zunars;

import java.util.List;


/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * 
 * TODO: Replace the implementation with code for your data type.
 */
public class RoomListRecyclerViewAdapter extends RecyclerView.Adapter<RoomListRecyclerViewAdapter.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1; 
    private final List<RoomListItem> mValues;
    private BitmapManager bmpManager;
    private final BaseItemFragment.OnListFragmentInteractionListener mListener;
private View.OnClickListener footerListener;
Zunars context;
    public View.OnClickListener getFooterListener() {
        return footerListener;
    }

    public void setFooterListener(View.OnClickListener footerListener) {
        this.footerListener = footerListener;
    }

    
    public RoomListRecyclerViewAdapter(Zunars context, List<RoomListItem> commentList, BaseItemFragment.OnListFragmentInteractionListener listener) {
        bmpManager=new BitmapManager();
        mValues = commentList;
        mListener = listener;
    }

   

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(holder instanceof Footer){
            return;
        }
    
        RoomListItem roomitem = mValues.get(position);
        String ImageURL = roomitem.getImage();
        Log.i("miaopicture","   Imageurl    "+ImageURL);
        if(ImageURL.endsWith("portrait.gif") || StringUtils.isEmpty(ImageURL)){
            holder.image.setImageResource(R.drawable.home);
        }else{


           bmpManager.loadBitmap(ImageURL, holder.image);
            
        }
        holder.image.setTag(roomitem);//设置隐藏参数(实体类)
       // holder.face.setOnClickListener(faceClickListener);
        holder.title.setText(roomitem.getDistrict_name()+"."+roomitem.getArea_name()+"."+getRoomType(roomitem));
        holder.subTitle.setText(roomitem.getCommunity_name()+"."+roomitem.getType_name()+"."+roomitem.getSquare()+"平");
        Log.i("miao","price"+roomitem.getPayment());
           holder.price.setText(roomitem.getPayment()+"/月");
     

      
        
        

       
    }
    public String getRoomType(RoomListItem roomListItem){
        String s="";
        s=s+roomListItem.getBed_room()+"室"+roomListItem.getHall_room()+"厅"+roomListItem.getBath_room()+"卫";
        return s ;
    }
    public String getTap(RoomListItem roomListItem){
        String s="";
        
        return s ;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.room_list_item, parent, false);
            return new ViewHolder(view);
        }
        // type == TYPE_FOOTER 返回footerView  
        else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.listview_footer, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT));
            return  new Footer(view);
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
   
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title,subTitle,price;
        public ImageView image;
        LinearLayout relies,refers;

        public ViewHolder(View view) {
            super(view);
            mView = view;
       
         image = (ImageView) mView.findViewById(R.id.image_view);
            title = (TextView) mView.findViewById(R.id.back_title);
           subTitle = (TextView) mView.findViewById(R.id.GreyTitle);
           price = (TextView) mView.findViewById(R.id.payment);
         //  tap = (TextView) mView.findViewById(R.id.tap);
         
          
        }

     
       
        
    }
    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView  
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }
    public class Footer extends RoomListRecyclerViewAdapter.ViewHolder {
        public final View mView;
        public final TextView  title;
        public final View  flag;
        
    

        public Footer(View view) {
            super(view);
            mView = view;
            title=(TextView)mView.findViewById(R.id.listview_foot_more);
            flag=mView.findViewById(R.id.listview_foot_progress);
            mView.setOnClickListener(footerListener);
        
        }

        @Override
        public String toString() {
            return "im footer"+ super.toString() + " '" +title.getText()+ "'";
        }
    }
    
}
