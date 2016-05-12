package com.pinisi.edubox.data.api;

import com.pinisi.edubox.data.model.Berita;

import java.util.List;

import net.derohimat.baseapp.util.BaseServiceGenerator;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by derohimat on 05/03/16.
 */
public enum TaniPediaService {
    SERVICE;
    private final Api api;

    TaniPediaService() {
        api = BaseServiceGenerator.createService(Api.class, Api.BASE_URL);
    }

    public static TaniPediaService pluck() {
        return SERVICE;
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
