package sunmoon.com.helper.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sunmoon.com.helper.R;
import sunmoon.com.helper.adapter.MessageAdapter;
import sunmoon.com.helper.bean.MessageInfo;
import sunmoon.com.helper.databinding.FragmentMessageBinding;

/**
 * Created by SunMoon on 2016/11/3.
 */

public class MessageFragment extends Fragment{
    private FragmentMessageBinding b;
    private MessageAdapter messageAdapter;
    private List<MessageInfo> messageInfos;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b= DataBindingUtil.inflate(inflater, R.layout.fragment_message,container,false);
        iniData();

        //
        messageAdapter=new MessageAdapter(getContext());
        messageAdapter.setMessages(messageInfos);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        b.lvMessages.setLayoutManager(layoutManager);
        b.lvMessages.setAdapter(messageAdapter);
        b.lvMessages.addItemDecoration(new DividerItemDecoration(getContext(),layoutManager.getOrientation()));
        return b.getRoot();

    }

    private void iniData() {
        messageInfos=new ArrayList<>();
        messageInfos.add(new MessageInfo("群助手","[3个群消息]","下午21：02"));
    }

    public static Fragment newInstance(){
        return new MessageFragment();
    }
}
