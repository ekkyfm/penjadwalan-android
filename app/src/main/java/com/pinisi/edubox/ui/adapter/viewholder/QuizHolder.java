package com.pinisi.edubox.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.pinisi.edubox.R;
import com.pinisi.edubox.data.model.Quiz;

import net.derohimat.baseapp.ui.adapter.BaseRecyclerAdapter;
import net.derohimat.baseapp.ui.adapter.viewholder.BaseItemViewHolder;

import butterknife.Bind;

/**
 * Created by derohimat on 05/03/16.
 */
public class QuizHolder extends BaseItemViewHolder<Quiz> {

    @Bind(R.id.tv_ujianName)
    TextView mTvUjianName;
    @Bind(R.id.tv_day)
    TextView mTvDay;
    @Bind(R.id.tv_time)
    TextView mTvTime;

    public QuizHolder(View itemView, BaseRecyclerAdapter.OnItemClickListener itemClickListener, BaseRecyclerAdapter.OnLongItemClickListener longItemClickListener) {
        super(itemView, itemClickListener, longItemClickListener);
    }

    @Override
    public void bind(Quiz quiz) {
//        mTvDay.setText(quiz.get.toString());
        mTvTime.setText(quiz.getTimeInMinute());
        mTvUjianName.setText(quiz.getLesson());
    }
}
