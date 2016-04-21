package com.denirohi.appexample.data.api;

import com.denirohi.appexample.data.model.Berita;

import java.util.List;

import id.derohimat.baseapp.util.BaseServiceGenerator;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by derohimat on 05/03/16.
 */
public enum TaniPediaService {
    HARVEST;
    private final Api api;

    TaniPediaService() {
        api = BaseServiceGenerator.createService(Api.class, Api.BASE_URL);
    }

    public static TaniPediaService pluck() {
        return HARVEST;
    }

    public Api getApi() {
        return api;
    }

    public interface Api {
        String BASE_URL = "http://apitanipedia.appspot.com";

        @GET("/berita")
        Observable<List<Berita>> getAllBerita();

        @GET("/berita")
        Observable<Berita> getBerita(@Query("url") String url);
    }
}
