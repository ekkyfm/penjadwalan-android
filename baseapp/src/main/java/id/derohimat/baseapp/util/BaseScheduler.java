package id.derohimat.baseapp.util;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public enum BaseScheduler {
    HARVEST;
    private final Observable.Transformer newThread;
    private final Observable.Transformer io;
    private final Observable.Transformer computation;

    BaseScheduler() {
        newThread = new Observable.Transformer() {
            @Override
            public Observable call(Object o) {
                return ((Observable) o).subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

        io = new Observable.Transformer() {
            @Override
            public Observable call(Object o) {
                return ((Observable) o).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

        computation = new Observable.Transformer() {
            @Override
            public Observable call(Object o) {
                return ((Observable) o).subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static BaseScheduler pluck() {
        return HARVEST;
    }

    @SuppressWarnings("unchecked")
    public <T> Observable.Transformer<T, T> applySchedulers(Type type) {
        switch (type) {
            case NEW_THREAD:
                return (Observable.Transformer<T, T>) newThread;
            case IO:
                return (Observable.Transformer<T, T>) io;
            case COMPUTATION:
                return (Observable.Transformer<T, T>) computation;
        }

        return (Observable.Transformer<T, T>) newThread;
    }

    public enum Type {
        NEW_THREAD, IO, COMPUTATION
    }
}
