/*
 * Copyright (c) 2015 Zetra.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.pinisi.edubox.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pinisi.edubox.R;
import com.pinisi.edubox.data.model.Quiz;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by derohimat on 05/03/16.
 */
public class QuizRecyclerAdapter extends RecyclerView.Adapter<QuizRecyclerAdapter.QuizHolder>  implements View.OnClickListener{
    private List<Quiz> list;
    private Context mContext;
    int position;

    public QuizRecyclerAdapter(List<Quiz> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ujian,parent,false);
        QuizHolder vh = new QuizHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(QuizHolder holder, int position) {
        QuizHolder viewHolder = (QuizHolder) holder;
        viewHolder.mTvUjianName.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void onClick(View v) {

    }

    public class QuizHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.tv_ujianName)
        TextView mTvUjianName;
        @Bind(R.id.tv_day) TextView mTvDay;
        @Bind(R.id.tv_time) TextView mTvTime;

        public QuizHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


        @Override
        public void onClick(View v) {

        }
    }

}
