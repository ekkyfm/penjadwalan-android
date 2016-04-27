package net.derohimat.baseapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import net.derohimat.baseapp.ui.BaseActivity;
import timber.log.Timber;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public abstract class BaseFragment<Data extends Parcelable> extends RxFragment {
    protected Context mContext;
    protected Data mData;
    protected LayoutInflater mInflater;

    public BaseFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.tag(getClass().getSimpleName());
        mContext = getActivity();
        mInflater = LayoutInflater.from(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = mInflater.inflate(getResourceLayout(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            mData = savedInstanceState.getParcelable("mDatas");
        }
        onViewReady(savedInstanceState);
    }

    public void setData(Data data) {
        this.mData = data;
    }

    public Data getData() {
        return mData;
    }

    protected abstract int getResourceLayout();

    protected abstract void onViewReady(@Nullable Bundle savedInstanceState);

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("mDatas", mData);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        mData = null;
        super.onDestroy();
    }

    public void replace(int containerId, BaseFragment fragment, boolean addToBackStack) {
        if (addToBackStack) {
            getFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(null)
                    .commit();
        } else {
            getFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    public void replace(int containerId, BaseFragment fragment, boolean addToBackStack, int transitionStyle) {
        if (addToBackStack) {
            getFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName())
                    .setTransitionStyle(transitionStyle)
                    .commit();
        } else {
            getFragmentManager().beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName())
                    .setTransitionStyle(transitionStyle)
                    .commit();
        }
    }

    protected void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    protected ActionBar getSupportActionBar() {
        return ((BaseActivity) getActivity()).getSupportActionBar();
    }

    protected void setSupportActionBar(Toolbar toolbar) {
        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
    }
}
