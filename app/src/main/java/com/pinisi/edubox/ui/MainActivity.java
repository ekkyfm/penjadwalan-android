package com.pinisi.edubox.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.pinisi.edubox.R;
import com.pinisi.edubox.ui.adapter.MainPagerAdapter;
import com.pinisi.edubox.ui.fragment.QuizMainFragment;
import com.trello.rxlifecycle.ActivityEvent;

import net.derohimat.baseapp.ui.BaseActivity;
import net.derohimat.baseapp.ui.fragment.BaseFragment;
import net.derohimat.baseapp.util.BaseBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import timber.log.Timber;

/**
 * Created by derohimat on 05/03/16.
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tabs)
    TabLayout mTabs;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        BaseBus.pluck()
                .receive()
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(o -> Timber.d("from MainActivity : " + o.toString()));

        setSupportActionBar(mToolbar);

        setUpViewPager(mViewpager);

        mTabs.setupWithViewPager(mViewpager);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpViewPager(ViewPager viewPager) {
        List<BaseFragment> fragments = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        fragments.add(new QuizMainFragment());
        fragments.add(new QuizMainFragment());

        strings.add("Ujian");
        strings.add("Materi");

        MainPagerAdapter adapter = new MainPagerAdapter(getBaseFragmentManager(), fragments, strings);
        viewPager.setAdapter(adapter);
    }
}
