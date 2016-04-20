package com.zunars.www.presenter.impl;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.zunars.www.expand_tab.ExpandTabView;
import com.zunars.www.expand_tab.ViewLeft;
import com.zunars.www.expand_tab.ViewMiddle;
import com.zunars.www.expand_tab.ViewRight;
import com.zunars.www.presenter.interfaces.SetupTabPresenter;
import com.zunars.www.zunars.R;

import java.util.ArrayList;

/**
 * Created by 洽洽 on 2016/4/20.
 */
public class SetuoPresenterImpl implements SetupTabPresenter {
     Context mContext ;
    private ViewLeft viewLeft;
    private ViewMiddle viewMiddle;
    private ViewRight viewRight;
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private ExpandTabView mExpandTabView;
    public SetuoPresenterImpl(Context context) {
        mContext=context;
    }

    @Override
    public void SetupTabPresenter(ExpandTabView expandTabView, View.OnClickListener onClickListener) {
        mExpandTabView=expandTabView;
        initView();
        initVaule();
        initListener();
    }

    @Override
    public void SetupTabPresenter(ExpandTabView expandTabView, View.OnClickListener onClickListener, ArrayList<View> mViewArray) {
        SetupTabPresenter(expandTabView,onClickListener);
        this.mViewArray=mViewArray;
    }

    private void initView() {

      
        viewLeft = new ViewLeft(mContext);
        viewMiddle = new ViewMiddle(mContext);
        viewRight = new ViewRight(mContext);
//        viewRight.setVisibility(View.GONE);

    }

    private void initVaule() {

//		mViewArray.add(viewLeft);
        mViewArray.add(viewMiddle);
//		mViewArray.add(viewRight);
        ArrayList<String> mTextArray = new ArrayList<String>();
//		mTextArray.add("距离");
        mTextArray.add("区域");
//		mTextArray.add("距离");
        mExpandTabView.setValue(mTextArray, mViewArray);
//		expandTabView.setTitle(viewLeft.getShowText(), 0);
//		expandTabView.setTitle(viewMiddle.getShowText(), 1);
//		expandTabView.setTitle(viewRight.getShowText(), 2);

    }

    private void initListener() {

        viewLeft.setOnSelectListener(new ViewLeft.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewLeft, showText);
            }
        });

        viewMiddle.setOnSelectListener(new ViewMiddle.OnSelectListener() {

            @Override
            public void getValue(String showText) {

                onRefresh(viewMiddle,showText);

            }
        });

        viewRight.setOnSelectListener(new ViewRight.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                onRefresh(viewRight, showText);
            }
        });

    }

    private void onRefresh(View view, String showText) {

        mExpandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !mExpandTabView.getTitle(position).equals(showText)) {
            mExpandTabView.setTitle(showText, position);
        }
        Toast.makeText(mContext, showText, Toast.LENGTH_SHORT).show();

    }

    private int getPositon(View tView) {
        for (int i = 0; i < mViewArray.size(); i++) {
            if (mViewArray.get(i) == tView) {
                return i;
            }
        }
        return -1;
    }
}
