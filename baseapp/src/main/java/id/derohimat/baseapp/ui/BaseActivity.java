package id.derohimat.baseapp.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import id.derohimat.baseapp.R;
import timber.log.Timber;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public abstract class BaseActivity extends RxAppCompatActivity {
    protected Context mContext = this;
    protected Toolbar mToolbar;
    protected LayoutInflater mInflater;

    protected ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        ButterKnife.bind(this);
        Timber.tag(getClass().getSimpleName());
        mInflater = LayoutInflater.from(mContext);
        onViewReady(savedInstanceState);
    }

    public FragmentManager getBaseFragmentManager() {
        return super.getSupportFragmentManager();
    }

    protected void setupToolbar(final Toolbar toolbar) {
        setupToolbar(toolbar, null);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void setupToolbar(final Toolbar toolbar, final View.OnClickListener onClickListener) {

        mToolbar = toolbar;
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        if (mActionBar != null)
            mActionBar.setHomeButtonEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (onClickListener != null)
            toolbar.setNavigationOnClickListener(onClickListener);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(int title) {
        super.setTitle(title);
        if (mActionBar != null)
            mActionBar.setTitle(getString(title));
    }

    public ActionBar getBaseActionBar() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        return actionBar;
    }

    protected void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    protected abstract int getResourceLayout();

    protected abstract void onViewReady(Bundle savedInstanceState);
}