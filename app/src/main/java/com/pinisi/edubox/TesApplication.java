package com.pinisi.edubox;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by derohimat on 05/03/16.
 */
public class TesApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
