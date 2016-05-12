package com.pinisi.edubox.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pinisi.edubox.R;
import com.pinisi.edubox.data.api.PinisiService;
import com.pinisi.edubox.data.model.ApiResponse;
import com.pinisi.edubox.data.model.Quiz;
import com.pinisi.edubox.presenter.QuizPresenter;
import com.pinisi.edubox.ui.adapter.QuizRecyclerAdapter;
import com.trello.rxlifecycle.FragmentEvent;

import net.derohimat.baseapp.ui.fragment.BaseFragment;
import net.derohimat.baseapp.ui.view.BaseRecyclerView;
import net.derohimat.baseapp.util.BaseBus;
import net.derohimat.baseapp.util.BaseScheduler;

import java.util.List;

import butterknife.Bind;
import timber.log.Timber;

/**
 * Created by derohimat on 05/03/16.
 */
public class QuizMainFragment extends BaseFragment<Quiz> implements QuizPresenter.View {
    private QuizPresenter quizPresenter;
    @Bind(R.id.rv_ujian)
    BaseRecyclerView mRvUjian;
    ApiResponse<List<Quiz>> apiResponse;

    QuizRecyclerAdapter adapter;

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_ujian_main;
    }

    @Override
    protected void onViewReady(@Nullable Bundle savedInstanceState) {
        BaseBus.pluck()
                .receive()
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(o -> Timber.d("from BacaFragment : " + o.toString()));

        PinisiService.pluck()
                .getApi()
                .getAllQuiz()
                .compose(BaseScheduler.pluck().applySchedulers(BaseScheduler.Type.COMPUTATION))
                .subscribe(apiResponse -> {
                            this.apiResponse = apiResponse;
                            Timber.d(apiResponse.getData().getResult().get(0).toString());
                        }, throwable -> {
                            Timber.d(throwable.getMessage());
                            Timber.d("Error");
                        }
                );
    }

    public void setUpController(Bundle savedInstanceState){
        if(quizPresenter==null){
            quizPresenter = new QuizPresenter(this);
        }
        if(savedInstanceState==null){
            quizPresenter.loadListQuiz();

        }else{
            quizPresenter.loadState(savedInstanceState);
        }
    }


    public void setUpRecycler(){

    }

    @Override
    public void showListQuiz(List<Quiz> quizs) {

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
