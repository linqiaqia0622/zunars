package com.zunars.www.base;


import android.support.v4.app.Fragment;


import com.zunars.www.zunars.Zunars;

/**
 * Created by 洽洽 on 2016/1/23.
 */
public class BaseFragment extends Fragment {
    public Zunars getZunars() {
        return (Zunars) getActivity().getApplication();
    }
}
