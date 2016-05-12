package com.pinisi.edubox.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.pinisi.edubox.R;
import com.pinisi.edubox.data.api.PinisiService;
import com.pinisi.edubox.data.model.Auth;
import com.trello.rxlifecycle.ActivityEvent;

import net.derohimat.baseapp.ui.BaseActivity;
import net.derohimat.baseapp.util.BaseBus;
import net.derohimat.baseapp.util.BaseScheduler;

import butterknife.Bind;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by derohimat on 05/03/16.
 */
public class LoginActivity extends BaseActivity {
    @Bind(R.id.tv_username)
    TextView mTvUsername;

    @Bind(R.id.tv_password)
    TextView mTvPassword;

    Auth auth;


    @Override
    protected int getResourceLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        BaseBus.pluck()
                .receive()
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(o -> Timber.d(o.toString()), throwable -> Timber.d(throwable.getMessage()));
    }

    @OnClick(R.id.btn_lanjut)
    public void lanjut(){
        Intent intent = new Intent(getApplicationContext(),SelectSchoolActivity.class);

        PinisiService.pluck()
                .getApi()
                .getAuth(mTvUsername.getText().toString(), mTvPassword.getText().toString())
                .compose(BaseScheduler.pluck().applySchedulers(BaseScheduler.Type.COMPUTATION))
                .subscribe(auth -> {
                    this.auth = auth;
                    loginCheck(auth);

                }, throwable -> {
                    Timber.d(throwable.getMessage());
                });

    }

    public void loginCheck(Auth auth){
        if(auth.getError().equals("FALSE")){
            Intent intent = new Intent(getApplicationContext(),SelectSchoolActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(getApplicationContext(), auth.getMsg(),Toast.LENGTH_SHORT);
        }
    }


}
