package com.zunars.www.presenter.impl;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zunars.www.expand_tab.ExpandTabView;
import com.zunars.www.expand_tab.RoomChoiceTab;
import com.zunars.www.expand_tab.RoomPriceTab;
import com.zunars.www.expand_tab.RoomTypeView;

import com.zunars.www.expand_tab.ViewMiddle;

import com.zunars.www.model.impl.GetParamsModelIMpl;
import com.zunars.www.model.interfaces.GetParamsModel;
import com.zunars.www.presenter.interfaces.SetupTabPresenter;
import com.zunars.www.presenter.interfaces.UpdateView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 洽洽 on 2016/4/20.
 */
public class SetuoPresenterImpl implements SetupTabPresenter {
    Context mContext;
    UpdateView mUpdateView;
    private RoomPriceTab viewLeft;
    private RoomTypeView viewMiddleLeft;
    private ViewMiddle viewMiddle;
    private RoomChoiceTab viewRight;
    private ArrayList<View> mViewArray = new ArrayList<View>();
    private ExpandTabView mExpandTabView;

    public SetuoPresenterImpl(Context context) {
        mContext = context;
    }

    @Override
    public void SetupTabPresenter(ExpandTabView expandTabView, UpdateView updateView) {
        mExpandTabView = expandTabView;
        initView();
        mUpdateView = updateView;
        initVaule();
        initListener();
    }

    @Override
    public void SetupTabPresenter(ExpandTabView expandTabView, UpdateView updateView, ArrayList<View> mViewArray) {
        SetupTabPresenter(expandTabView, updateView);

        this.mViewArray = mViewArray;
    }

    private void initView() {


        viewLeft = new RoomPriceTab(mContext);
        viewMiddleLeft = new RoomTypeView(mContext);
        viewMiddle = new ViewMiddle(mContext);
        viewRight = new RoomChoiceTab(mContext);
//        viewRight.setVisibility(View.GONE);

    }

    private void initVaule() {
        mViewArray.add(viewMiddle);
        mViewArray.add(viewLeft);
        mViewArray.add(viewMiddleLeft);

        mViewArray.add(viewRight);
        ArrayList<String> mTextArray = new ArrayList<String>();

        mTextArray.add("区域");
        mTextArray.add("租金");
        mTextArray.add("户型");
        mTextArray.add("筛选");
        mExpandTabView.setValue(mTextArray, mViewArray);
//		expandTabView.setTitle(viewLeft.getShowText(), 0);
//		expandTabView.setTitle(viewMiddle.getShowText(), 1);
//		expandTabView.setTitle(viewRight.getShowText(), 2);

    }

    private void initListener() {

        viewLeft.setOnSelectListener(new RoomPriceTab.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
              GetParamsModel getParamsModel=new GetParamsModelIMpl();
                
                mUpdateView.updateView(getParamsModel.getParamsByTAb("pl",showText));
                onRefresh(viewLeft, showText);
            }
        });

        viewMiddle.setOnSelectListener(new ViewMiddle.OnSelectListener() {

            @Override
            public void getValue(String showText) {

                onRefresh(viewMiddle, showText);

            }
        });
        viewMiddleLeft.setOnSelectListener(new RoomTypeView.OnSelectListener() {

            @Override
            public void getValue(String distance, String showText) {
                GetParamsModel getParamsModel=new GetParamsModelIMpl();
                mUpdateView.updateView( getParamsModel.getParamsByTAb("be",showText));
                onRefresh(viewMiddleLeft, showText);
            }
        });
        viewRight.setOnSelectListener(new RoomChoiceTab.OnSelectListener() {

            @Override
            public void getValue(String distance, Map map) {
                onRefresh(viewRight,"");
                mUpdateView.updateView( map);
            }
        });

    }

    private void onRefresh(View view, String showText) {

        mExpandTabView.onPressBack();
        int position = getPositon(view);
        if (position >= 0 && !mExpandTabView.getTitle(position).equals(showText)) {
            mExpandTabView.setTitle(showText, position);
        }
        Log.i("miao", "dlkddjklkj" + " mexpangtabView");
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
