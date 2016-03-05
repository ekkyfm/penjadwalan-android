package com.denirohi.appexample.ui.adapter;

import android.support.v4.app.FragmentManager;

import com.denirohi.appexample.ui.fragment.BacaFragment;

import java.util.List;

import id.derohimat.baseapp.ui.adapter.BasePagerAdapter;

/**
 * Created by derohimat on 05/03/16.
 */
public class BacaPagerAdapter extends BasePagerAdapter<BacaFragment> {
    public BacaPagerAdapter(FragmentManager fm, List<BacaFragment> bacaFragments) {
        super(fm, bacaFragments);
    }

    @Override
    public BacaFragment getItem(int position) {
        return fragments.get(position);
    }
}
