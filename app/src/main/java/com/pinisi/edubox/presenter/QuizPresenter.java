package com.pinisi.edubox.presenter;

import android.os.Bundle;
import android.os.Parcelable;

import com.pinisi.edubox.data.api.PinisiService;
import com.pinisi.edubox.data.model.ApiResponse;
import com.pinisi.edubox.data.model.Quiz;

import net.derohimat.baseapp.presenter.BasePresenter;
import net.derohimat.baseapp.util.BaseScheduler;
import net.derohimat.baseapp.util.BaseWorker;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by derohimat on 05/03/16.
 */
public class QuizPresenter extends BasePresenter<QuizPresenter.View> {
    private ApiResponse<List<Quiz>> apiResponse;
    private Quiz quiz;

    public QuizPresenter(View view) {
        super(view);
        Timber.d("Quiz Presenter created");
    }

    public void loadListQuiz() {
        PinisiService.pluck()
                .getApi()
                .getAllQuiz()
                .compose(BaseScheduler.pluck().applySchedulers(BaseScheduler.Type.COMPUTATION))
                .subscribe(apiResponse -> {
                            this.apiResponse = apiResponse;
                            mView.showListQuiz(apiResponse.getData().getResult());
                        }, throwable -> {
                            Timber.d(throwable.getMessage());
                            mView.showError(throwable);
                        }
                );

    }


    private void doThing() {
        for (int i = 0; i < 1000000000; i++) {
            int a = i;
            int b = a + 1;
            int c = b * 2;
            a = a * c / b;
        }
    }

    public void doSomeThing() {
        BaseWorker.pluck()
                .doInComputation(this::doThing)
                .subscribe(o -> {
                    Timber.d(o.toString());
                    mView.showSomeThing();
                }, throwable -> Timber.d(throwable.getMessage()));
    }

    @Override
    public void saveState(Bundle bundle) {
        bundle.putParcelableArrayList("listQuiz", (ArrayList<? extends Parcelable>) apiResponse.getData().getResult());
        bundle.putParcelable("quiz", quiz);
        bundle.putParcelable("apiResponse", apiResponse);
    }

    @Override
    public void loadState(Bundle bundle) {
        apiResponse = bundle.getParcelable("apiResponse");
        if (apiResponse != null) {
            mView.showListQuiz(apiResponse.getData().getResult());
        } else {
            mView.showError(new Throwable("Error"));
        }

    }

    public interface View extends BasePresenter.View {

        void showListQuiz(List<Quiz> quizs);

        //        void showQuiz(Quiz quiz);
        void showSomeThing();


    }
}
