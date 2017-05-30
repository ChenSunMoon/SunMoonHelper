package com.sunmoon.helper.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.sunmoon.helper.R;
import com.sunmoon.helper.activity.FragmentActivity;
import com.sunmoon.helper.adapter.ListAdapter;
import com.sunmoon.helper.databinding.FragmentMyRemindBinding;
import com.sunmoon.helper.databinding.ItemRemindBinding;
import com.sunmoon.helper.model.Remind;
import com.sunmoon.helper.presenter.MyRemindPresenter;
import com.sunmoon.helper.view.MyRemindView;
import com.sunmoon.helper.widget.RecycleViewDivider;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by SunMoon on 2017/5/23.
 */
public class MyRemindFragment extends BaseFragment<FragmentMyRemindBinding,MyRemindPresenter> implements MyRemindView {

    public ListAdapter<Remind, ItemRemindBinding> adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        p = new MyRemindPresenter();
        p.setView(this);
        adapter =  new ListAdapter<>(getActivity(),R.layout.item_remind);
        adapter.setOnBindViewHolder(new ListAdapter.BindView<ItemRemindBinding, Remind>() {
            @Override
            public void onBindViewHolder(ItemRemindBinding b, final Remind item, final int i) {
                b.setRemind(item);
                b.setOpenRemind(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FragmentActivity.setFragment(RemindFragment.newInstance(item.getId()), "提醒");
                        Intent intent = new Intent(MyRemindFragment.this.getActivity(), FragmentActivity.class);
                        MyRemindFragment.this.startActivity(intent);
                    }
                });
                b.setDeleteRemind(new View.OnLongClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public boolean onLongClick(View view) {
                        new SweetAlertDialog(getContext(),SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("警告")
                                .setContentText("删除此纪录？")
                                .setConfirmText("确定")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        p.deleteRemind(item, i);
                                        sweetAlertDialog.dismiss();
                                    }
                                }).show();
                        return true;
                    }
                });
                b.swComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        item.setStatus(b);
                        p.updateRemind(item, i);
                    }
                });
            }
        });
        super.onCreate(savedInstanceState);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_my_remind,container,false);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        b.rvRemind.setLayoutManager(staggeredGridLayoutManager);
        b.rvRemind.setAdapter(adapter);
        return b.getRoot();
    }

    public static  MyRemindFragment newInstance() {
        MyRemindFragment fragment = new MyRemindFragment();
        return fragment;
    }

    @Override
    public void iniReminds(List<Remind> reminds) {
        adapter.initList(reminds);
    }

    @Override
    public void deleteRemind(int i) {
        adapter.remove(i);
    }

    @Override
    public void updateRemind(Remind item, int i) {
        adapter.update(i,  item);
    }
}
