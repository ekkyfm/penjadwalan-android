package com.denirohi.appexample.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.denirohi.appexample.R;

import butterknife.Bind;
import net.derohimat.baseapp.ui.fragment.BaseFragment;

/**
 * Created by derohimat on 05/03/16.
 */
public class MyFragment extends BaseFragment {
    private String data;
    @Bind(R.id.text)
    TextView textView;

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_baca;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    protected void onViewReady(@Nullable Bundle savedInstanceState) {
        textView.setText(data);
    }

    @Override
    public void onDestroy() {
        data = null;
        super.onDestroy();
    }
}
