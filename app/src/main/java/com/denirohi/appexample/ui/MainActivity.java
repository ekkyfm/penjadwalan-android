package com.denirohi.appexample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.denirohi.appexample.R;
import com.denirohi.appexample.data.model.Berita;
import com.denirohi.appexample.presenter.BeritaPresenter;
import com.denirohi.appexample.ui.adapter.BeritaRecyclerAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import id.derohimat.baseapp.ui.BaseActivity;
import id.derohimat.baseapp.ui.view.BaseRecyclerView;
import id.derohimat.baseapp.util.BaseBus;
import id.derohimat.baseapp.util.BaseWorker;
import timber.log.Timber;

/**
 * Created by derohimat on 05/03/16.
 */
public class MainActivity extends BaseActivity implements BeritaPresenter.View {

    private BeritaPresenter beritaPresenter;
    @Bind(R.id.recycler_view)
    BaseRecyclerView recyclerView;
    private BeritaRecyclerAdapter adapter;

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        BaseBus.pluck()
                .receive()
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(o -> Timber.d(o.toString()), throwable -> Timber.d(throwable.getMessage()));

        setUpAdapter();
        setUpRecyclerView();
        setUpController(savedInstanceState);

//        beritaPresenter.doSomeThing();
//        doSomeDummyThread();
    }

    private void setUpAdapter() {
        adapter = new BeritaRecyclerAdapter(this);
        adapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, BacaActivity.class);
            intent.putParcelableArrayListExtra("data", (ArrayList<Berita>) adapter.getDatas());
            intent.putExtra("pos", position);
            startActivity(intent);
        });
        adapter.setOnLongItemClickListener((view, position) -> adapter.remove(position));
    }

    private void setUpRecyclerView() {
        recyclerView.setUpAsList();
        recyclerView.setAdapter(adapter);

        //for disable load more
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                beritaPresenter.loadListBerita();
            }

            @Override
            public void onLoadMore() {
            }
        });
    }

    private void setUpController(Bundle bundle) {
        if (beritaPresenter == null) {
            beritaPresenter = new BeritaPresenter(this);
        }

        if (bundle != null) {
            beritaPresenter.loadState(bundle);
        } else {
            beritaPresenter.loadListBerita();
        }
    }

    private void doSomeDummyThread() {
        for (int i = 0; i < 10; i++) {
            final int thread = i;
            BaseWorker.pluck()
                    .doInComputation(() -> {
                        for (int j = 0; j < 10000; j++) {
                            for (int k = 0; k < j; k++) {
                                int a = j;
                                int b = k;
                                int c = a * k + b * j;
                                c = c / (j - k);
                            }
                        }
                    })
                    .compose(bindUntilEvent(ActivityEvent.DESTROY))
                    .subscribe(o -> Timber.d("Worker " + thread + " is done."), throwable -> Timber.d(throwable.getMessage()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        BaseBus.pluck().send("onCreateOptionMenu()");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        BaseBus.pluck().send("onOptionsMenuSelected");
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void showListBerita(List<Berita> listBerita) {
        recyclerView.refreshComplete();
        adapter.clear();
        adapter.add(listBerita);
    }

    @Override
    public void showBerita(Berita berita) {

    }

    @Override
    public void showSomeThing() {
        adapter.remove(3);
        Toast.makeText(this, "WOWWOWOWOWO", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(Throwable throwable) {
        Snackbar.make(recyclerView, "Something wrong!", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading(boolean isRefresh) {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        beritaPresenter.saveState(outState);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
