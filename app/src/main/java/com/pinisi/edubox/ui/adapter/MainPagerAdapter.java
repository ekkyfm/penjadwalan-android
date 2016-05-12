package com.pinisi.edubox.ui.adapter;

import android.support.v4.app.FragmentManager;

import net.derohimat.baseapp.ui.adapter.BasePagerAdapter;
import net.derohimat.baseapp.ui.fragment.BaseFragment;

import java.util.List;

/**
 * Created by derohimat on 05/03/16.
 */
public class MainPagerAdapter extends BasePagerAdapter<BaseFragment> {
    public MainPagerAdapter(FragmentManager fm, List<BaseFragment> fragments, List<String> titles) {
        super(fm, fragments, titles);
    }

    @Override
    public BaseFragment getItem(int position) {
        return mFragments.get(position);
    }
}
