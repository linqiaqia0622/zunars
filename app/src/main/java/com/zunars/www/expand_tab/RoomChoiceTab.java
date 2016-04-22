package com.zunars.www.expand_tab;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.RelativeLayout;

import com.zunars.www.adapter.TextAdapter;
import com.zunars.www.zunars.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RoomChoiceTab extends RelativeLayout implements ViewBaseAction,View.OnClickListener{

		private OnSelectListener mOnSelectListener;
	private Map map;
	private String mDistance;
	private String showText = "item1";
	//是否实名认证
	private int i=0;
	//整租=1合租=2
	private int r=0;
	//绑定微信筛选
	private int w=0;
	//房源列表排序方式 时间=0；价格高到低=1，第到高=2
	private int o=0;
	
	private Context mContext;
private Button  sure_button,reset_button,low_top_button,top_low_button,
		default_button,confirm_button,bind_wechat_button,co_rent,all_rent;
	public String getShowText() {
		return showText;
	}

	public RoomChoiceTab(Context context) {
	
		super(context);
		init(context);	
		ButterKnife.bind((Activity)context);
	}

	public RoomChoiceTab(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	
		init(context);
	}

	public RoomChoiceTab(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View view=	inflater.inflate(R.layout.room_choice_tab, this, true);
		map=new HashMap<String ,Object>();
		i=0;
		confirm_button=(Button)view.findViewById(R.id.confirm_button);
		
		sure_button=(Button)view.findViewById(R.id.sure_button);
		reset_button=(Button)view.findViewById(R.id.reset_button);
		low_top_button=(Button)view.findViewById(R.id.low_top_button);
		top_low_button=(Button)view.findViewById(R.id.top_low_button);
		default_button=(Button)view.findViewById(R.id.default_button);
		bind_wechat_button=(Button)view.findViewById(R.id.bind_wechat_button);
		co_rent=(Button)view.findViewById(R.id.co_rent);
		all_rent=(Button)view.findViewById(R.id.all_rent);
		confirm_button.setOnClickListener(this);
		sure_button.setOnClickListener(this);
		reset_button.setOnClickListener(this);
		low_top_button.setOnClickListener(this);
		top_low_button.setOnClickListener(this);
		default_button.setOnClickListener(this);
		bind_wechat_button.setOnClickListener(this);
		co_rent.setOnClickListener(this);
		all_rent.setOnClickListener(this);
		
		setBackgroundDrawable(getResources().getDrawable(R.drawable.choosearea_bg_right));
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.confirm_button:{
				if(i==1){
					i=0
				;}else i=1;
				map.put("i",i);
				
			}case R.id.sure_button:{
				map.put("i",i);
				map.put("w",w);
				map.put("o",o);
				map.put("r",r);
			mOnSelectListener.getValue("",map);
			}case R.id.reset_button:{
				 i=0;
				//整租=1合租=2
				 r=0;
				//绑定微信筛选
				 w=0;
				//房源列表排序方式 时间=0；价格高到低=1，第到高=2
				o=0;
				
			}case R.id.low_top_button:{
				o=1;
				
			}case R.id.top_low_button:{
				o=2;
			}case R.id.default_button:{
				o=0;
			}case R.id.bind_wechat_button:{
				w=1;
			}case R.id.co_rent:{
				r=1;
			}case R.id.all_rent:{
				r=2;
			}

		}

	}
	public void setOnSelectListener(OnSelectListener onSelectListener) {
		mOnSelectListener = onSelectListener;
	}

	

	public interface OnSelectListener {
		public void getValue(String distance, Map map);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void show() {
		
	}
	
	

}
