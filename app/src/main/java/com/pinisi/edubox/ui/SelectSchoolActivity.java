package com.pinisi.edubox.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.pinisi.edubox.R;
import com.pinisi.edubox.data.api.PinisiService;
import com.pinisi.edubox.data.model.ApiResponse;
import com.pinisi.edubox.data.model.School;
import com.pinisi.edubox.presenter.SchoolPresenter;
import com.trello.rxlifecycle.ActivityEvent;

import net.derohimat.baseapp.ui.BaseActivity;
import net.derohimat.baseapp.util.BaseBus;
import net.derohimat.baseapp.util.BaseScheduler;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by derohimat on 05/03/16.
 */
public class SelectSchoolActivity extends BaseActivity implements AdapterView.OnItemClickListener, SchoolPresenter.View {
    SchoolPresenter schoolPresenter;
    @Bind(R.id.sp_sekolah)
    Spinner mSpSekolah;
    ArrayAdapter<String> dataAdapter;

    private ApiResponse<List<List<School>>> apiResponse;


    @Override
    protected int getResourceLayout() {
        return R.layout.activity_select_school;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        BaseBus.pluck()
                .receive()
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(o -> Timber.d(o.toString()), throwable -> Timber.d(throwable.getMessage()));

        PinisiService.pluck()
                .getApi()
                .getAllSchool()
                .compose(BaseScheduler.pluck().applySchedulers(BaseScheduler.Type.COMPUTATION))
                .subscribe(apiResponse -> {
                            this.apiResponse = apiResponse;
                            setAdapter(apiResponse.getData().getResult().get(0));
                                                                                                                  Timber.d(apiResponse.getData().getResult().get(0).toString());
                        }, throwable -> {
                            Timber.d(throwable.getMessage());
                            Timber.d("Error");
                        }
                );

       setUpController(savedInstanceState);

    }

    @OnClick(R.id.btn_login)
    public void getLogin() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void setUpController(Bundle savedInstanceState) {
        if (schoolPresenter == null) {
            schoolPresenter = new SchoolPresenter(this);
        }


        if (savedInstanceState != null) {
            schoolPresenter.loadState(savedInstanceState);
        } else {
            schoolPresenter.loadList();
        }
    }

    public void setAdapter(List<School> schools) {
        List<String> schoolName = new ArrayList<>();
        Timber.d("");
        for(School school :schools){
            schoolName.add(school.getSchoolName());
        }

        dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, schoolName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpSekolah.setAdapter(dataAdapter);
        Timber.d("set adapter");


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void showList(List<School> schools) {

    }

    @Override
    public void showSomeThing() {

    }

    @Override
    public void showError(Throwable error) {

    }

    @Override
    public void showLoading(boolean isRefresh) {

    }

    @Override
    public void dismissLoading() {

    }
}
