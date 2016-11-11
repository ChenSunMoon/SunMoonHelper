package sunmoon.com.helper.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sunmoon.com.helper.R;
import sunmoon.com.helper.activity.VoiceActivity;
import sunmoon.com.helper.bean.MessageInfo;
import sunmoon.com.helper.databinding.ItemMessageBinding;

/**
 * Created by SunMoon on 2016/11/4.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<MessageInfo> messages;
    private Context context;
    private LayoutInflater inflater;
    public MessageAdapter(Context context){
        this.context=context;
        this.inflater=LayoutInflater.from(context);
    }
    public void setMessages(List<MessageInfo> messages){
           this.messages=messages;
    }
    @Override
    public MessageAdapter.MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MessageViewHolder holder=new MessageViewHolder(inflater.inflate(R.layout.item_message,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MessageAdapter.MessageViewHolder holder, int position) {
        holder.getBind().setMessage(messages.get(position));
        holder.getBind().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, VoiceActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder{
        private ItemMessageBinding b;
        public MessageViewHolder(View itemView) {
            super(itemView);
            b= DataBindingUtil.bind(itemView);
        }
        public ItemMessageBinding getBind(){
            return b;
        }
    }
}
