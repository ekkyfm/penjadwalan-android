package net.derohimat.baseapp.util;

import rx.Observable;
import rx.Subscriber;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public enum BaseWorker {
    WORKER;

    public static BaseWorker pluck() {
        return WORKER;
    }

    public Observable<Object> doInComputation(final Runnable runnable) {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                runnable.run();
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("We are done!");
                    subscriber.onCompleted();
                }
            }
        }).compose(BaseScheduler.pluck().applySchedulers(BaseScheduler.Type.COMPUTATION));
    }

    public Observable<Object> doInIO(final Runnable runnable) {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                runnable.run();
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("We are done!");
                    subscriber.onCompleted();
                }
            }
        }).compose(BaseScheduler.pluck().applySchedulers(BaseScheduler.Type.IO));
    }

    public Observable<Object> doInNewThread(final Runnable runnable) {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                runnable.run();
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("We are done!");
                    subscriber.onCompleted();
                }
            }
        }).compose(BaseScheduler.pluck().applySchedulers(BaseScheduler.Type.NEW_THREAD));
    }
}
