package com.sunmoon.helper.adapter;

/**
 * Created by admin on 2017/2/20.
 */

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunmoon.helper.model.NoteInfo;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter<T1, T2 extends ViewDataBinding> extends RecyclerView.Adapter<ListAdapter.BaseViewHolder> {
    private List<T1> list;
    private LayoutInflater inflater;
    private Context context;
    @LayoutRes
    private int layout;
    private BindView<T2, T1> bindView;

    public ListAdapter(Context context, @LayoutRes int layout) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.layout = layout;
        list = new ArrayList<>();
    }

    public void initList(List<T1> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    public void add(T1 item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
    }

    public void remove(int i) {
        list.remove(i);
        notifyItemRemoved(i);
    }
    public void updateNote(int i,T1 item){
        this.list.set(i,item);
        this.notifyItemChanged(i);
    }
    public void setOnBindViewHolder(BindView<T2, T1> bindView) {
        this.bindView = bindView;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(layout, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        bindView.onBindViewHolder((T2) holder.getBinding(), list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface BindView<T1, T2> {
        void onBindViewHolder(T1 b, T2 item, int i);
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding b;

        public BaseViewHolder(View itemView) {
            super(itemView);
            b = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return b;
        }
    }
}
