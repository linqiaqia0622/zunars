package com.zunars.www.expand_tab;



import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zunars.www.adapter.TextAdapter;
import com.zunars.www.zunars.R;


public class RoomPriceTab extends RelativeLayout implements ViewBaseAction{

	private ListView mListView;
	private final String[] items = new String[] { "不限", "300~600", "600~1000", "1000~1500", "1500~2000"
			, "2000~3000","3000~4000","4000~5000","5000~7000","7000~10000","10000以上"};//显示字段
	private final String[] itemsVaule = new String[] { "1", "2", "3", "4", "5", "6","7" ,"8" ,"9" ,"10" ,"11" };//隐藏id
	private OnSelectListener mOnSelectListener;
	private TextAdapter adapter;
	private String mDistance;
	private String showText = "item1";
	private Context mContext;

	public String getShowText() {
		return showText;
	}

	public RoomPriceTab(Context context) {
		super(context);
		init(context);
	}

	public RoomPriceTab(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public RoomPriceTab(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_distance, this, true);
		setBackgroundDrawable(getResources().getDrawable(R.drawable.choosearea_bg_mid));
		mListView = (ListView) findViewById(R.id.listView);
		adapter = new TextAdapter(context, items, R.drawable.choose_item_right, R.drawable.choose_eara_item_selector);
		adapter.setTextSize(17);
		if (mDistance != null) {
			for (int i = 0; i < itemsVaule.length; i++) {
				if (itemsVaule[i].equals(mDistance)) {
					adapter.setSelectedPositionNoNotify(i);
					showText = items[i];
					break;
				}
			}
		}
		mListView.setAdapter(adapter);
		adapter.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

			@Override
			public void onItemClick(View view, int position) {

				if (mOnSelectListener != null) {
					showText = items[position];
					mOnSelectListener.getValue(itemsVaule[position], items[position]);
				}
			}
		});
	}

	public void setOnSelectListener(OnSelectListener onSelectListener) {
		mOnSelectListener = onSelectListener;
	}

	public interface OnSelectListener {
		public void getValue(String distance, String showText);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void show() {
		
	}

}
