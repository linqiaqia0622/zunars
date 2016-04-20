package com.zunars.www.presenter.interfaces;

import android.view.View;

import com.zunars.www.expand_tab.ExpandTabView;

import java.util.ArrayList;

/**
 * Created by 洽洽 on 2016/4/20.
 */
public interface SetupTabPresenter {
    void SetupTabPresenter(ExpandTabView expandTabView , View.OnClickListener onClickListener);

    void SetupTabPresenter(ExpandTabView expandTabView, View.OnClickListener onClickListener,ArrayList<View> mViewArray);
}
