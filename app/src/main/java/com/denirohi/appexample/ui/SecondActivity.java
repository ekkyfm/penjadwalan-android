package com.denirohi.appexample.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.denirohi.appexample.R;
import com.denirohi.appexample.ui.adapter.MyPagerAdapter;
import com.denirohi.appexample.ui.fragment.MyFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import net.derohimat.baseapp.ui.BaseActivity;

/**
 * Created by derohimat on 05/03/16.
 */
public class SecondActivity extends BaseActivity {
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_second;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        List<MyFragment> fragments = Arrays.asList(new MyFragment(), new MyFragment());
        List<String> titles = Arrays.asList("Fragment 1", "Fragment 2");
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
