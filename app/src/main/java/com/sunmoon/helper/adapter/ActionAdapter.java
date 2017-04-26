package com.sunmoon.helper.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.sunmoon.helper.R;
import com.sunmoon.helper.databinding.ItemActionBinding;
import com.sunmoon.helper.model.ActionInfo;
import java.util.List;

/**
 * Created by SunMoon on 2016/11/4.
 */

public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.ActionViewHolder> {
    private List<ActionInfo> actionInfos;
    private LayoutInflater inflater;
    public ActionAdapter(Context context){
        this.inflater=LayoutInflater.from(context);
    }

    public void setActionInfos(List<ActionInfo> actionInfos){
           this.actionInfos = actionInfos;
    }
    @Override
    public ActionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ActionViewHolder holder=new ActionViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_action,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ActionViewHolder holder, int position) {
        holder.getBind().setAction(actionInfos.get(position));

    }

    @Override
    public int getItemCount() {
        return actionInfos.size();
    }

    class ActionViewHolder extends RecyclerView.ViewHolder{
        private ItemActionBinding b;
        public ActionViewHolder(ViewDataBinding b) {
            super(b.getRoot());
            this.b= (ItemActionBinding) b;
        }

        public ItemActionBinding getBind(){
            return b;
        }
    }
}
