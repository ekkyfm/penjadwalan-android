package com.pinisi.edubox.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.pinisi.edubox.R;
import com.pinisi.edubox.data.model.Quiz;
import com.pinisi.edubox.presenter.QuizPresenter;
import com.pinisi.edubox.ui.adapter.QuizRecyclerAdapter;
import com.trello.rxlifecycle.FragmentEvent;

import net.derohimat.baseapp.ui.fragment.BaseFragment;
import net.derohimat.baseapp.ui.view.BaseRecyclerView;
import net.derohimat.baseapp.util.BaseBus;

import java.util.List;

import butterknife.Bind;
import timber.log.Timber;

/**
 * Created by derohimat on 05/03/16.
 */
public class QuizMainFragment extends BaseFragment implements QuizPresenter.View {

    @Bind(R.id.rv_ujian)
    BaseRecyclerView mRecyclerView;

    private QuizPresenter mPresenter;
    private QuizRecyclerAdapter mAdapter;

    @Override
    protected int getResourceLayout() {
        return R.layout.fragment_ujian_main;
    }

    @Override
    protected void onViewReady(@Nullable Bundle savedInstanceState) {
        BaseBus.pluck()
                .receive()
                .compose(bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(o -> Timber.d("from QuizMainFragment : " + o.toString()));

        setUpAdapter();
        setUpRecycler();
        setUpController(savedInstanceState);
    }

    public void setUpController(Bundle savedInstanceState) {
        if (mPresenter == null) {
            mPresenter = new QuizPresenter(this);
        }

        if (savedInstanceState == null) {
            mPresenter.loadListQuiz();
        } else {
            mPresenter.loadState(savedInstanceState);
        }
    }

    public void setUpRecycler() {
        mRecyclerView.setUpAsList();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadListQuiz();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    private void setUpAdapter() {
        mAdapter = new QuizRecyclerAdapter(mContext);

        mAdapter.setOnItemClickListener((view, position) -> {
            Quiz quiz = mAdapter.getDatas().get(position - 1);
            //TODO : pindah ke activity detail Quiz
        });
    }

    @Override
    public void showListQuiz(List<Quiz> quizs) {
        mAdapter.addAll(quizs);

        mRecyclerView.refreshComplete();
    }

    @Override
    public void showQuiz(Quiz quiz) {

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
