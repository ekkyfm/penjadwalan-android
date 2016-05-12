package com.pinisi.edubox.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by ekky on 09/05/16.
 */
public class NomorAdapter extends BaseAdapter {
    private Context mContext;
    private Integer[] mNomor;

    public NomorAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public NomorAdapter(Context mContext, Integer[] mNomor) {
        this.mContext = mContext;
        this.mNomor = mNomor;
    }

    @Override
    public int getCount() {
        return mNomor.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView nomor = null;
        nomor.setText(mNomor[position]);
        return nomor;
    }




}
