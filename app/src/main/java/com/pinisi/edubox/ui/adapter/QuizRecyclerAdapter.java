package com.pinisi.edubox.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.pinisi.edubox.R;
import com.pinisi.edubox.data.model.Quiz;
import com.pinisi.edubox.ui.adapter.viewholder.QuizHolder;

import net.derohimat.baseapp.ui.adapter.BaseRecyclerAdapter;

/**
 * Created by derohimat on 05/03/16.
 */
public class QuizRecyclerAdapter extends BaseRecyclerAdapter<Quiz, QuizHolder> {
    public QuizRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_ujian;
    }

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuizHolder(getView(parent, viewType), mItemClickListener, mLongItemClickListener);
    }
}
