package id.derohimat.baseapp.ui.fragment.dialog;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import com.trello.rxlifecycle.components.RxDialogFragment;

import id.derohimat.baseapp.ui.BaseActivity;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public class BaseDialogFragment extends RxDialogFragment {

    protected Context mContext;
    protected LayoutInflater mInflater;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
        mInflater = LayoutInflater.from(mContext);
    }

    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

}
