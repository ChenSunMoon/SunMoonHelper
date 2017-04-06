package com.sunmoon.helper.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sunmoon.helper.R;
import com.sunmoon.helper.model.Message;
import com.sunmoon.helper.databinding.ItemLeftMessageBinding;
import com.sunmoon.helper.databinding.ItemRightMessageBinding;

import java.util.ArrayList;
import java.util.List;

public class VoiceAdapter extends RecyclerView.Adapter<VoiceAdapter.MessageViewHolder> {
    private List<Message> messages;
    private LayoutInflater inflater;
    private final int LEFT_FLAG=0;
    private final int RIGHT_FLAG=1;
    public VoiceAdapter(Context context){
        inflater= LayoutInflater.from(context);
        messages=new ArrayList<>();
    }
    public void addMessage(Message message){
        messages.add(message);
        this.notifyItemInserted(getItemCount()-1);
    }
    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MessageViewHolder holder;
        if (viewType==LEFT_FLAG) {
            holder = new MessageViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_left_message, parent, false),LEFT_FLAG);
        }else{
            holder = new MessageViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_right_message, parent, false),RIGHT_FLAG);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.setMessage(messages.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
    class MessageViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding b;
        private int viewType=0;
        public MessageViewHolder(ViewDataBinding b,int viewType) {
            super(b.getRoot());
            this.b= b;
            this.viewType=viewType;
        }
        public void setMessage(Message message){
            if (viewType==LEFT_FLAG){
                ((ItemLeftMessageBinding)b).setMessage(message);
            }else{
                ((ItemRightMessageBinding)b).setMessage(message);
            }
        }
    }
}
