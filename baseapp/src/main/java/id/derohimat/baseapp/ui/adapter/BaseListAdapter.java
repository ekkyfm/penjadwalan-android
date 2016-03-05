package id.derohimat.baseapp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import id.derohimat.baseapp.ui.adapter.viewholder.BaseListViewHolder;
import id.derohimat.baseapp.util.BaseWorker;
import rx.functions.Action1;
import timber.log.Timber;

/**
 * Created on : 05-03-2016
 * Author     : derohimat
 * Name       : Deni Rohimat
 * Email      : rohimatdeni@gmail.com
 * GitHub     : https://github.com/derohimat
 * LinkedIn   : https://www.linkedin.com/in/derohimat
 */
public abstract class BaseListAdapter<Data, Holder extends BaseListViewHolder> extends
        BaseAdapter {
    public static String PAGE = "BaseListAdapter.Page";
    protected Context context;
    protected List<Data> data;

    public BaseListAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
        Timber.tag(getClass().getSimpleName());
    }

    public BaseListAdapter(Context context, List<Data> data) {
        this.context = context;
        this.data = data;
        Timber.tag(getClass().getSimpleName());
    }

    @Override
    public int getCount() {
        try {
            return data.size();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Data getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        Holder holder;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(getItemView(), parent, false);
            holder = onCreateViewHolder(itemView);
            itemView.setTag(holder);
        } else {
            holder = (Holder) itemView.getTag();
        }

        holder.bind(data.get(position));

        return itemView;
    }

    protected abstract int getItemView();

    public abstract Holder onCreateViewHolder(View itemView);

    public List<Data> getData() {
        return data;
    }

    public void add(Data item) {
        data.add(item);
        notifyDataSetChanged();
    }

    public void add(Data item, int position) {
        data.add(position, item);
        notifyDataSetChanged();
    }

    public void add(final List<Data> items) {
        final int size = items.size();
        BaseWorker.pluck()
                .doInComputation(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < size; i++) {
                            data.add(items.get(i));
                        }
                    }
                }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                notifyDataSetChanged();
            }
        });
    }

    public void addOrUpdate(Data item) {
        int i = data.indexOf(item);
        if (i >= 0) {
            data.set(i, item);
            notifyDataSetChanged();
        } else {
            add(item);
        }
    }

    public void addOrUpdate(final List<Data> items) {
        final int size = items.size();
        BaseWorker.pluck()
                .doInComputation(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < size; i++) {
                            Data item = items.get(i);
                            int x = data.indexOf(item);
                            if (x >= 0) {
                                data.set(x, item);
                            } else {
                                add(item);
                            }
                        }
                    }
                }).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                notifyDataSetChanged();
            }
        });
    }

    public void remove(int position) {
        if (position >= 0 && position < data.size()) {
            data.remove(position);
            notifyDataSetChanged();
        }
    }

    public void remove(Data item) {
        int position = data.indexOf(item);
        remove(position);
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }
}
