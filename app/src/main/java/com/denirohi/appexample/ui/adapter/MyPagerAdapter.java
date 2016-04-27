package com.denirohi.appexample.ui.adapter;

import android.support.v4.app.FragmentManager;

import com.denirohi.appexample.ui.fragment.MyFragment;

import java.util.List;

import net.derohimat.baseapp.ui.adapter.BasePagerAdapter;

/**
 * Created by derohimat on 05/03/16.
 */
public class MyPagerAdapter extends BasePagerAdapter<MyFragment> {
    public MyPagerAdapter(FragmentManager fm, List<MyFragment> myFragments, List<String> titles) {
        super(fm, myFragments, titles);
    }

    @Override
    public MyFragment getItem(int position) {
        mFragments.get(position).setData("This is fragment " + (position + 1));
        return mFragments.get(position);
    }
}
