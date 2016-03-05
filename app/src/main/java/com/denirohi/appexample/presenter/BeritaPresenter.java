package com.denirohi.appexample.presenter;

import android.os.Bundle;

import com.denirohi.appexample.data.api.TaniPediaService;
import com.denirohi.appexample.data.model.Berita;

import java.util.ArrayList;
import java.util.List;

import id.derohimat.baseapp.presenter.BasePresenter;
import id.derohimat.baseapp.util.BaseScheduler;
import id.derohimat.baseapp.util.BaseWorker;
import timber.log.Timber;

/**
 * Created by derohimat on 05/03/16.
 */
public class BeritaPresenter extends BasePresenter<BeritaPresenter.View> {
    private List<Berita> listBerita;
    private Berita berita;

    public BeritaPresenter(View view) {
        super(view);
        Timber.d("BeritaPresenter created");
    }

    public void loadListBerita() {
        TaniPediaService.pluck()
                .getApi()
                .getAllBerita()
                .compose(BaseScheduler.pluck().applySchedulers(BaseScheduler.Type.IO))
                .subscribe(listBerita -> {
                    this.listBerita = listBerita;
                    view.showListBerita(listBerita);
                }, throwable -> {
                    Timber.d(throwable.getMessage());
                    view.showError(throwable);
                });
    }

    public void loadBerita(String url) {
        TaniPediaService.pluck()
                .getApi()
                .getBerita(url)
                .compose(BaseScheduler.pluck().applySchedulers(BaseScheduler.Type.IO))
                .subscribe(berita -> {
                    this.berita = berita;
                    view.showBerita(berita);
                }, throwable -> {
                    Timber.d(throwable.getMessage());
                    view.showError(throwable);
                });
    }

    private void doThing() {
        for (int i = 0; i < 1000000000; i++) {
            int a = i;
            int b = a + 1;
            int c = b * 2;
            a = a * c / b;
        }
    }

    public void doSomeThing() {
        BaseWorker.pluck()
                .doInComputation(this::doThing)
                .subscribe(o -> {
                    Timber.d(o.toString());
                    view.showSomeThing();
                }, throwable -> Timber.d(throwable.getMessage()));
    }

    @Override
    public void saveState(Bundle bundle) {
        bundle.putParcelableArrayList("listBerita", (ArrayList<Berita>) listBerita);
        bundle.putParcelable("berita", berita);
    }

    @Override
    public void loadState(Bundle bundle) {
        listBerita = bundle.getParcelableArrayList("listBerita");
        if (listBerita != null) {
            view.showListBerita(listBerita);
        } else {
            view.showError(new Throwable("Error"));
        }

        berita = bundle.getParcelable("berita");
        if (berita != null) {
            view.showBerita(berita);
        } else {
            view.showError(new Throwable("Error"));
        }
    }

    public interface View extends BasePresenter.View {
        void showListBerita(List<Berita> listBerita);

        void showBerita(Berita berita);

        void showSomeThing();
    }
}
