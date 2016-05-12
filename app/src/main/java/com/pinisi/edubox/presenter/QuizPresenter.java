package com.pinisi.edubox.presenter;

import android.os.Bundle;
import android.os.Parcelable;

import com.pinisi.edubox.data.api.PinisiService;
import com.pinisi.edubox.data.model.Quiz;

import net.derohimat.baseapp.presenter.BasePresenter;
import net.derohimat.baseapp.util.BaseScheduler;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by derohimat on 05/03/16.
 */
public class QuizPresenter extends BasePresenter<QuizPresenter.View> {
    private Quiz mData;
    private List<Quiz> mList;

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
                            this.mList = apiResponse.getData().getResult();
                            mView.showListQuiz(apiResponse.getData().getResult());
                        }, throwable -> {
                            Timber.d(throwable.getMessage());
                            mView.showError(throwable);
                        }
                );
    }

    @Override
    public void saveState(Bundle bundle) {
        bundle.putParcelableArrayList("mList", (ArrayList<? extends Parcelable>) mList);
        bundle.putParcelable("mData", mData);
    }

    @Override
    public void loadState(Bundle bundle) {
        mList = bundle.getParcelable("mList");
        if (mList != null) {
            mView.showListQuiz(mList);
        } else {
            mView.showError(new Throwable("Error"));
        }
    }

    public interface View extends BasePresenter.View {

        void showListQuiz(List<Quiz> quizs);

        void showQuiz(Quiz quiz);
    }
}
