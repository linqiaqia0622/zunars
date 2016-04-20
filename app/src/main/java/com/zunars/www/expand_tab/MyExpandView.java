package com.zunars.www.expand_tab;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.zunars.www.zunars.R;

import java.util.ArrayList;

/**
 * 菜单控件头部，封装了下拉动画，动态生成头部按钮个数
 */

public class MyExpandView extends ExpandTabView{

	private ToggleButton selectedButton;

	private Context mContext;
	private final int SMALL = 0;
	private int displayWidth;
	private int displayHeight;
	private PopupWindow popupWindow;
	//private int selectPosition;

	public MyExpandView(Context context) {
		super(context);
		init(context);
	}

	public MyExpandView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * 根据选择的位置设置tabitem显示的值
	 */
	public void setTitle(String valueText, int position) {
	
	}

	public void setTitle(String title){
		
	}
	/**
	 * 根据选择的位置获取tabitem显示的值
	 */
	public String getTitle(int position) {
		
		return "";
	}

	/**
	 * 设置tabitem的个数和初始值
	 */
	public void setValue(ArrayList<String> textArray, ArrayList<View> viewArray) {
		
	}

	private void startAnimation() {

//		if (popupWindow == null) {
//			popupWindow = new PopupWindow(mViewArray.get(selectPosition), displayWidth, displayHeight);
//			popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
//			popupWindow.setFocusable(false);
//			popupWindow.setOutsideTouchable(true);
//		}

		if (selectedButton.isChecked()) {
//			if (!popupWindow.isShowing()) {
//				showPopup(selectPosition);
//			} else {
				popupWindow.setOnDismissListener(this);
				popupWindow.dismiss();
				hideView();
			//}
		} else {
			if (popupWindow.isShowing()) {
				popupWindow.dismiss();
				hideView();
			}
		}
	}

	private void showPopup(int position) {
//		View tView = mViewArray.get(selectPosition).getChildAt(0);
//		if (tView instanceof ViewBaseAction) {
//			ViewBaseAction f = (ViewBaseAction) tView;
//			f.show();
//		}
//		if (popupWindow.getContentView() != mViewArray.get(position)) {
//			popupWindow.setContentView(mViewArray.get(position));
//		}
		popupWindow.showAsDropDown(this, 0, 0);
	}

	/**
	 * 如果菜单成展开状态，则让菜单收回去
	 */
	public boolean onPressBack() {
		if (popupWindow != null && popupWindow.isShowing()) {
			popupWindow.dismiss();
			hideView();
			if (selectedButton != null) {
				selectedButton.setChecked(false);
			}
			return true;
		} else {
			return false;
		}

	}

	private void hideView() {
		//View tView = mViewArray.get(selectPosition).getChildAt(0);
//		if (tView instanceof ViewBaseAction) {
//			ViewBaseAction f = (ViewBaseAction) tView;
//			f.hide();
//		}
	}

	private void init(Context context) {
		mContext = context;
		displayWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
		displayHeight = ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
		setOrientation(LinearLayout.HORIZONTAL);
	}

	@Override
	public void onDismiss() {
		//showPopup(selectPosition);
		popupWindow.setOnDismissListener(null);
	}

	private OnButtonClickListener mOnButtonClickListener;

	/**
	 * 设置tabitem的点击监听事件
	 */
	public void setOnButtonClickListener(OnButtonClickListener l) {
		mOnButtonClickListener = l;
	}

	/**
	 * 自定义tabitem点击回调接口
	 */
	public interface OnButtonClickListener {
		public void onClick(int selectPosition);
	}

}
