package com.zunars.www.widget;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CompoundButton;


import com.kyleduo.switchbutton.SwitchButton;
import com.zunars.www.expand_tab.ExpandTabView;
import com.zunars.www.presenter.impl.SetuoPresenterImpl;
import com.zunars.www.presenter.interfaces.SetupTabPresenter;
import com.zunars.www.zunars.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class textActivity extends ActionBarActivity {
    @Bind(R.id.expandtab_view)
    ExpandTabView tab_area;
    int count = 0;
    @Override
    @TargetApi(21)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        final ToggleExpandLayout layout = (ToggleExpandLayout) findViewById(R.id.toogleLayout);
        SwitchButton switchButton = (SwitchButton) findViewById(R.id.switch_button);
        ButterKnife.bind(this);
        layout.setOnToggleTouchListener(new ToggleExpandLayout.OnToggleTouchListener() {
            @Override
            public void onStartOpen() {
            }

            @Override
            public void onOpen() {
               int childCount = layout.getChildCount();
               for(int i = 0; i < childCount; i++) {
                   View view = layout.getChildAt(i);
                   view.setElevation(dp2px(1));
               }
            }

            @Override
            public void onStartClose() {
                int childCount = layout.getChildCount();
                for(int i = 0; i < childCount; i++) {
                    View view = layout.getChildAt(i);
                    view.setElevation(dp2px(i));
                }
            }

            @Override
            public void onClosed() {

            }
            
        });

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    layout.open();
                } else {
                    layout.close();
                }
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (count % 2 == 0) {
                    layout.open();
                } else {
                    layout.close();
                }
                count++;
            }
        });
        SetupTabPresenter set=new SetuoPresenterImpl(this);
        set.SetupTabPresenter(tab_area,null,new ArrayList<View>());

    }

    public float dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }
}
