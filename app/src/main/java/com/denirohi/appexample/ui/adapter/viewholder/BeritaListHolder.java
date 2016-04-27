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

package com.denirohi.appexample.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.denirohi.appexample.R;
import com.denirohi.appexample.data.model.Berita;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.Bind;
import net.derohimat.baseapp.ui.adapter.viewholder.BaseListViewHolder;
import timber.log.Timber;

/**
 * Created by derohimat on 05/03/16.
 */
public class BeritaListHolder extends BaseListViewHolder<Berita> {
    @Bind(R.id.text) TextView judul;

    public BeritaListHolder(View itemView) {
        super(itemView);
        RxTextView.textChanges(judul)
                .subscribe(charSequence -> Timber.d(charSequence.toString()));
    }

    @Override
    public void bind(Berita berita) {
        judul.setText(berita.getJudul());
    }
}
