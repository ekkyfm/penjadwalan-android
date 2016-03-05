package id.derohimat.baseapp.presenter;

import android.os.Bundle;

import timber.log.Timber;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public abstract class BasePresenter<V extends BasePresenter.View> {
    protected V view;

    public BasePresenter(V view) {
        this.view = view;
        Timber.tag(getClass().getSimpleName());
    }

    public abstract void saveState(Bundle bundle);

    public abstract void loadState(Bundle bundle);

    public interface View {
        void showError(Throwable error);

        void showLoading();

        void dismissLoading();
    }
}
