package id.derohimat.baseapp.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.derohimat.baseapp.ui.adapter.viewholder.BaseHeaderViewHolder;
import id.derohimat.baseapp.ui.adapter.viewholder.BaseItemViewHolder;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public abstract class BaseHeaderAdapter<Data, ViewHolder extends BaseItemViewHolder<Data>,
        Header extends BaseHeaderViewHolder> extends
        BaseRecyclerAdapter<Data, BaseItemViewHolder> {
    protected static final int TYPE_HEADER = 0;
    protected static final int TYPE_ITEM = 1;
    protected boolean hasHeader = true;
    protected Header header;
    protected Bundle bundle;

    public BaseHeaderAdapter(Context context, Bundle bundle) {
        super(context);
        this.bundle = bundle;
        if (hasHeader) {
            data.add(null);
        }
    }

    @Override
    protected int getItemResourceLayout(int viewType) {
        if (viewType == TYPE_HEADER) {
            return getHeaderLayout();
        } else {
            return getItemLayout(viewType);
        }
    }

    protected abstract int getHeaderLayout();

    protected abstract int getItemLayout(int viewType);

    @Override
    public BaseItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (hasHeader && viewType == TYPE_HEADER) {
            header = onCreateHeaderViewHolder(viewGroup, viewType);
            return header;
        }

        return onCreateItemViewHolder(viewGroup, viewType);
    }

    protected abstract Header onCreateHeaderViewHolder(ViewGroup viewGroup, int viewType);

    public abstract ViewHolder onCreateItemViewHolder(ViewGroup viewGroup, int viewType);

    @Override
    public void onBindViewHolder(BaseItemViewHolder holder, int position) {
        if (hasHeader && position == 0) {
            header.show();
            return;
        }
        holder.setHasHeader(hasHeader);
        super.onBindViewHolder(holder, position);
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0 && hasHeader) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void showHeader() {
        if (!hasHeader) {
            hasHeader = true;
            data.add(0, null);
        }
    }

    public void hideHeader() {
        if (hasHeader) {
            hasHeader = false;
            data.remove(0);
        }
    }

    public boolean isHasHeader() {
        return hasHeader;
    }

    @Override
    public void clear() {
        super.clear();
        if (hasHeader) {
            data.add(null);
        }
    }

    @Override
    public List<Data> getData() {
        return hasHeader ? new ArrayList<>(data.subList(1, data.size())) : super.getData();
    }

    public Header getHeader() {
        return header;
    }

    @Override
    public void add(Data item, int position) {
        if (hasHeader) {
            data.add(position + 1, item);
            notifyItemInserted(position + 1);
        } else {
            super.add(item, position);
        }
    }

    @Override
    public void remove(int position) {
        if (hasHeader) {
            data.remove(position + 1);
            notifyItemRemoved(position + 1);
        } else {
            super.remove(position);
        }
    }
}
