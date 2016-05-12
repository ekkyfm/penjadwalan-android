package com.pinisi.edubox.data.api;

import com.pinisi.edubox.data.model.ApiResponse;
import com.pinisi.edubox.data.model.Auth;
import com.pinisi.edubox.data.model.Quiz;
import com.pinisi.edubox.data.model.School;

import net.derohimat.baseapp.util.BaseServiceGenerator;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by derohimat on 05/03/16.
 */
public enum PinisiService {
    SERVICE;
    private final Api api;

    PinisiService() {
        api = BaseServiceGenerator.createService(Api.class, Api.BASE_URL);
    }

    public static PinisiService pluck() {
        return SERVICE;
    }

    public Api getApi() {
        return api;
    }

    public interface Api {
        String BASE_URL = "http://api.pinisi.io/";

        @GET("api/list/lesson?token=qpW9Hn3CKCfLrm0BcoiZNIuoqNO8dQPsJ1J8JdkomD37zQAFrc&model=quiz")
        Observable<ApiResponse<List<Quiz>>> getAllQuiz();

        @FormUrlEncoded
        @POST("api/post/?model=login&type=android")
        Observable<Auth> getAuth(@Field("username") String username, @Field("password") String password);


        @GET("api/schoollist")
        Observable<ApiResponse<List<List<School>>>> getAllSchool();

    }
}
