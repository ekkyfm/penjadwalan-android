package com.denirohi.appexample.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.denirohi.appexample.R;
import com.denirohi.appexample.data.model.Berita;
import com.denirohi.appexample.presenter.BeritaPresenter;
import com.trello.rxlifecycle.FragmentEvent;

import java.util.List;

import butterknife.Bind;
import id.derohimat.baseapp.ui.fragment.BaseFragment;
import id.derohimat.baseapp.util.BaseBus;
import timber.log.Timber;

/**
 * Created by derohimat on 05/03/16.
 */
public class BacaFragment extends BaseFragment<Berita> implements BeritaPresenter.View {
    private BeritaPresenter beritaPresenter;
    @Bind(R.id.text) TextView textView;

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void onViewReady(@Nullable Bundle savedInstanceState) {
        BaseBus.pluck()
                .receive()
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(o -> Timber.d("from BacaFragment : " + o.toString()));

        setUpController(savedInstanceState);
    }

    private void setUpController(Bundle savedInstanceState) {
        if (beritaPresenter == null) {
            beritaPresenter = new BeritaPresenter(this);
        }

        if (savedInstanceState == null) {
            beritaPresenter.loadBerita(data.getAlamat());
        } else {
            beritaPresenter.loadState(savedInstanceState);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            getSupportActionBar().setTitle(data.getJudul());
        }
    }

    @Override
    public void showListBerita(List<Berita> listBerita) {

    }

    @Override
    public void showBerita(Berita berita) {
        BaseBus.pluck().send("Show " + berita.getJudul());
        textView.setText(berita.getIsi());
    }

    @Override
    public void showSomeThing() {

    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        beritaPresenter.saveState(outState);
        super.onSaveInstanceState(outState);
    }
}
