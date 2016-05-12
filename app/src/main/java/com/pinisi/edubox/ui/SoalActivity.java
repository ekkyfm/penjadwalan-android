package com.pinisi.edubox.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.pinisi.edubox.R;
import com.trello.rxlifecycle.ActivityEvent;

import net.derohimat.baseapp.ui.BaseActivity;
import net.derohimat.baseapp.util.BaseBus;

import butterknife.Bind;
import timber.log.Timber;

/**
 * Created by derohimat on 05/03/16.
 */
public class SoalActivity extends BaseActivity{
    @Bind(R.id.reveal_items)
    LinearLayout mRevealView;
//    @Bind(R.id.gv_nomor)
//    GridView mGvNomor;

    Boolean hidden = true;

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_soal;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        BaseBus.pluck()
                .receive()
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(o -> Timber.d("from MainActivity : " + o.toString()));
//        gridViewConfig();
        mRevealView.setVisibility(View.GONE);
        hidden = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        BaseBus.pluck().send("onCreateOptionMenu()");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        BaseBus.pluck().send("onOptionsMenuSelected");
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                if (hidden == true){
                    mRevealView.setVisibility(View.VISIBLE);
                    hidden=false;
                }else if(hidden==false){
                    mRevealView.setVisibility(View.GONE);
                    hidden=true;
                }
                break;
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
