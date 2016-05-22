package com.zunars.www.adapter;

import android.content.Context;
import android.graphics.ImageFormat;
import android.media.Image;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zunars.www.base.BaseItemFragment;
import com.zunars.www.net.bean.RoomListItem;
import com.zunars.www.util.BitmapManager;
import com.zunars.www.util.StringUtils;
import com.zunars.www.zunars.R;
import com.zunars.www.zunars.Zunars;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;


/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * 
 * TODO: Replace the implementation with code for your data type.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_HEAD = 2;
    private static final int TYPE_RECYCLE = 3; 
    private  List<RoomListItem> mValues;
    private BitmapManager bmpManager;
    private final BaseItemFragment.OnListFragmentInteractionListener mListener;
private View.OnClickListener footerListener;
Context context;
    public View.OnClickListener getFooterListener() {
        return footerListener;
    }

    public void setFooterListener(View.OnClickListener footerListener) {
        this.footerListener = footerListener;
    }

    
    public MainRecyclerViewAdapter(Context context, List<RoomListItem> commentList, BaseItemFragment.OnListFragmentInteractionListener listener) {
        bmpManager=new BitmapManager();
        this.context=context;
      // commentList.add(new RoomListItem());
        
        //mValues.add(commentList.get(1));

        //mValues.addAll(commentList);
         mValues=commentList;
        mListener = listener; 
    }
 
   

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(holder instanceof Header){
            Log.i("miao","foooterFooterFooterFooterFooterFooter");
            return;
        } if(holder instanceof Banner){
            Log.i("miao","Recycle+sdfjkjlksdfsdf;+Recycle");
            Banner ban= (Banner)  holder;
         
            ban.banner.setTransitionEffect(BGABanner.TransitionEffect.Rotate);
            // banner.setPageTransformer(new RotatePageTransformer());
            // 设置page切换时长
            ban.  banner.setPageChangeDuration(1000);
            List<View> views = new ArrayList<>();
            views.add(getPageView(R.drawable.qia));
            views.add(getPageView(R.drawable.qia));
            views.add(getPageView(R.drawable.qia));
            ban.  banner.setViews(views);
           // re.recyclerView.setAdapter(adapter);  re.recyclerView.setLayoutManager(new LinearLayoutManager(context));
            return;
        }
    
      final  RoomListItem roomitem = mValues.get(position-1);
        String ImageURL = roomitem.getImage();
        if(ImageURL.endsWith("portrait.gif") || StringUtils.isEmpty(ImageURL)){
            holder.image.setImageResource(R.drawable.home);
        }else{


           bmpManager.loadBitmap(ImageURL, holder.image);
            
        }
        holder.image.setTag(roomitem);//设置隐藏参数(实体类)
       // holder.face.setOnClickListener(faceClickListener);
        Log.i("miao","ViewHolder");
        holder.title.setText(roomitem.getDistrict_name()+"."+roomitem.getArea_name()+"."+getRoomType(roomitem));
        holder.subTitle.setText(roomitem.getCommunity_name()+"."+roomitem.getType_name()+"."+roomitem.getSquare()+"平");
       // Log.i("miao","price"+roomitem.getPayment());
           holder.price.setText(roomitem.getPayment()+"/月");
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(roomitem);
                    Log.i("miao","hhh"+roomitem.getId());
                }
            }
        });

      
        
        

       
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
        else if (viewType == TYPE_HEAD) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.main_head, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT));
            return  new Header(view);
        }else if(viewType==TYPE_RECYCLE){
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.main_banner, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.WRAP_CONTENT));
            return  new Banner(view);
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return mValues.size()+1;
    }
   
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title,subTitle,price;
        public ImageView image;
        LinearLayout relies,refers;

        public ViewHolder(View view) {
         
            super(view); 
            Log.i("miao","ViewHolder");
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
        
        if(position==0) {
            Log.i("miao","getItemview type"+"head");
            return TYPE_HEAD;
        }else  if(position==1){
            return TYPE_RECYCLE;
        }
        else {
                    Log.i("miao","getItemview type"+"item");
            return TYPE_ITEM;
        }
    }
    public class Banner extends MainRecyclerViewAdapter.ViewHolder {
       // @Bind(R.id.main_banner)
        public BGABanner banner;
        public Banner(View view) {
            super(view);
            Log.i("miao","banner oncreat");
            banner=(BGABanner)view.findViewById(R.id.main_banner);
           // ButterKnife.bind(this,view);
            
        }
    }
        public class Header extends MainRecyclerViewAdapter.ViewHolder {
        
        public final View mView;
//        public final TextView  title;
//        public final View  flag;
        @Bind(R.id.area_button)
        Button button;
        private View getPageView(@DrawableRes int resid) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(resid);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        public Header(View view) {
            
            super(view);
            mView = view;    ButterKnife.bind(this,view);
            BGABanner banner = (BGABanner)view.findViewById(R.id.banner_splash_pager);
            // 用Java代码方式设置切换动画
            banner.setTransitionEffect(BGABanner.TransitionEffect.Rotate);
            // banner.setPageTransformer(new RotatePageTransformer());
            // 设置page切换时长
            banner.setPageChangeDuration(1000);
            List<View> views = new ArrayList<>();
            views.add(getPageView(R.drawable.qia));
            views.add(getPageView(R.drawable.qia));
            views.add(getPageView(R.drawable.qia));
            banner.setViews(views);
        
            Log.i("miao","foooterFooterFooterFooterFooterFooter");
//            title=(TextView)mView.findViewById(R.id.listview_foot_more);
//            flag=mView.findViewById(R.id.listview_foot_progress);
            //  mView.setOnClickListener(footerListener);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("miao","onbindclick");
                }
            });
        }
    
}
    private View getPageView(  @DrawableRes int resid) {
        ImageView imageView=new ImageView(context);
        imageView.setImageResource(resid);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
   }
