package com.zunars.www.adapter;

import android.content.Context;
import android.media.Image;
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
public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewAdapter.ViewHolder> {
  
    private final List<Integer> mValues;

 

    
    public ImageViewAdapter(Context context, List<Integer> commentList) {
      
        mValues = commentList;
      
    }

   

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

Log.i("miao","设置隐藏参数+onBindViewHolder");
        Integer image = mValues.get(position);

        holder.image.setImageResource(image);
        holder.image  .setScaleType(ImageView.ScaleType.CENTER_CROP);
       //设置隐藏参数(实体类)
       
     

      
        
        

       
    }
   
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
     
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.image, parent, false);
            return new ViewHolder(view);
     

      
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
   
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
       
        public ImageView image;


        public ViewHolder(View view) {
            super(view);
            mView = view;
       
         image = (ImageView) mView.findViewById(R.id.main_image);
        
         
          
        }

     
       
        
    }
    @Override
    public int getItemViewType(int position) {
      return 1;
    }
  


  
    
}
