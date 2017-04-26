package com.sunmoon.helper.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunmoon.helper.R;
import com.sunmoon.helper.activity.NoteActivity;
import com.sunmoon.helper.adapter.ListAdapter;
import com.sunmoon.helper.databinding.ItemActionBinding;
import com.sunmoon.helper.model.ActionInfo;
import com.sunmoon.helper.databinding.FragmentMessageBinding;

import java.util.ArrayList;
import java.util.List;
public class MessageFragment extends Fragment{
    private FragmentMessageBinding b;
    private List<ActionInfo> actions;
    private ListAdapter<ActionInfo,ItemActionBinding> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actions =new ArrayList<>();
        actions.add(new ActionInfo("便签", NoteActivity.class,R.drawable.ic_action_note,"activity"));

        adapter=new ListAdapter<>(getContext(),R.layout.item_action);
        adapter.setOnBindViewHolder(new ListAdapter.BindView<ItemActionBinding, ActionInfo>() {
            @Override
            public void onBindViewHolder(ItemActionBinding b, ActionInfo item, int i) {
                b.setAction(item);
            }
        });
        adapter.initList(actions);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b= DataBindingUtil.inflate(inflater, R.layout.fragment_message,container,false);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        b.lvMessages.setLayoutManager(layoutManager);
        b.lvMessages.setAdapter(adapter);
        b.lvMessages.addItemDecoration(new DividerItemDecoration(getContext(),layoutManager.getOrientation()));
        return b.getRoot();
    }

    public static Fragment newInstance(){
        return new MessageFragment();
    }
}
