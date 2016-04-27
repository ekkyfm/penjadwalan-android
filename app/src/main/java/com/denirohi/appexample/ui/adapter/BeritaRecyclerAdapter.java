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

package com.denirohi.appexample.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.denirohi.appexample.R;
import com.denirohi.appexample.data.model.Berita;
import com.denirohi.appexample.ui.adapter.viewholder.BeritaHolder;

import net.derohimat.baseapp.ui.adapter.BaseRecyclerAdapter;

/**
 * Created by derohimat on 05/03/16.
 */
public class BeritaRecyclerAdapter extends BaseRecyclerAdapter<Berita, BeritaHolder> {
    public BeritaRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        return R.layout.item_berita;
    }

    @Override
    public BeritaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BeritaHolder(getView(parent, viewType), mItemClickListener, mLongItemClickListener);
    }
}
