package id.derohimat.baseapp.util;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public enum BaseBus {
    BASE_APP;
    private final Subject<Object, Object> bus;

    BaseBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    public static BaseBus pluck() {
        return BASE_APP;
    }

    public void send(Object o) {
        bus.onNext(o);
    }

    public Observable<Object> receive() {
        return bus.compose(BaseScheduler.pluck().applySchedulers(BaseScheduler.Type.NEW_THREAD));
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }
}
