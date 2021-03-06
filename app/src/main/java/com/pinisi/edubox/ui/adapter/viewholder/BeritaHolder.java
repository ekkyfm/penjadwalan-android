package com.pinisi.edubox.ui.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.pinisi.edubox.R;
import com.pinisi.edubox.data.model.Berita;

import butterknife.Bind;
import net.derohimat.baseapp.ui.adapter.BaseRecyclerAdapter;
import net.derohimat.baseapp.ui.adapter.viewholder.BaseItemViewHolder;

/**
 * Created by derohimat on 05/03/16.
 */
public class BeritaHolder extends BaseItemViewHolder<Berita> {
    @Bind(R.id.text) TextView judul;

    public BeritaHolder(View itemView, BaseRecyclerAdapter.OnItemClickListener itemClickListener, BaseRecyclerAdapter.OnLongItemClickListener longItemClickListener) {
        super(itemView, itemClickListener, longItemClickListener);
    }

    @Override
    public void bind(Berita berita) {
        judul.setText(berita.getJudul());
    }
}
