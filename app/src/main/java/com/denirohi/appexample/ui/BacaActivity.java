package com.denirohi.appexample.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.denirohi.appexample.R;
import com.denirohi.appexample.data.model.Berita;
import com.denirohi.appexample.ui.adapter.BacaPagerAdapter;
import com.denirohi.appexample.ui.fragment.BacaFragment;
import com.trello.rxlifecycle.ActivityEvent;

import net.derohimat.baseapp.ui.BaseActivity;
import net.derohimat.baseapp.util.BaseBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import timber.log.Timber;

/**
 * Created by derohimat on 05/03/16.
 */
public class BacaActivity extends BaseActivity {
    @Bind(R.id.view_pager)
    ViewPager pager;

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_baca;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        BaseBus.pluck()
                .receive()
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(o -> Timber.d("from BacaActivity : " + o.toString()));

        int pos = getIntent().getIntExtra("pos", 0);
        ArrayList<Berita> data = getIntent().getParcelableArrayListExtra("data");
        List<BacaFragment> fragments = new ArrayList<>();

        for (Berita berita : data) {
            BacaFragment fragment = new BacaFragment();
            fragment.setData(berita);

            fragments.add(fragment);
        }

        pager.setAdapter(new BacaPagerAdapter(getSupportFragmentManager(), fragments));
        pager.setCurrentItem(pos);
    }
}
