package id.derohimat.baseapp.util;


import org.json.JSONObject;

import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedByteArray;


/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public class BaseServiceGenerator {
    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setConverter(new GsonConverter(Bson.pluck().getParser()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Content-Type", "application/json");
                        request.addHeader("Accept", "application/json");
//                        request.addHeader("Authorization", "Basic NTZjMzRiYjQxMTM4MzNkZDAxNmU1ZDJlOlZrcFZSTXBKaWthMlFIRVo5OXBnTEN4ank5dWpxeDdE");
                    }
                })
                .setErrorHandler(new ErrorHandler() {
                    @Override
                    public Throwable handleError(RetrofitError cause) {
                        if (cause.getKind().equals(RetrofitError.Kind.HTTP)) {
                            String json = new String(((TypedByteArray) cause.getResponse().getBody()).getBytes());
                            try {
                                JSONObject object = new JSONObject(json);
                                return new Throwable(object.getJSONObject("data").getString("message"));
                            } catch (Exception e) {
                                return cause;
                            }
                        } else if (cause.getKind().equals(RetrofitError.Kind.NETWORK)) {
                            return new Throwable("Can't connect to server, please check your internet connection!");
                        }
                        return cause;
                    }
                })
                .setEndpoint(baseUrl);

        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }
}
