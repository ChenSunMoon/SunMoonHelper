package com.sunmoon.helper.activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.hwangjr.rxbus.RxBus;
import com.sunmoon.helper.R;
import com.sunmoon.helper.adapter.ListAdapter;
import com.sunmoon.helper.databinding.ActivityNoteBinding;
import com.sunmoon.helper.databinding.ItemNoteBinding;
import com.sunmoon.helper.model.NoteInfo;
import com.sunmoon.helper.presenter.NotePresenter;
import com.sunmoon.helper.view.NoteView;

import java.util.List;

/**
 * Created by SunMoon on 2016/12/31.
 */

public class NoteActivity extends BaseActivity implements NoteView {
    ActivityNoteBinding b;
    private ListAdapter<NoteInfo,ItemNoteBinding> adapter;
    private NotePresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=DataBindingUtil.setContentView(this, R.layout.activity_note);
        presenter =new NotePresenter(this);
        presenter.setNoteView(this);
        adapter = new ListAdapter<>(this,R.layout.item_note);
        adapter.setOnBindViewHolder(new ListAdapter.BindView<ItemNoteBinding, NoteInfo>() {
            @Override
            public void onBindViewHolder(ItemNoteBinding b, NoteInfo item, int i) {
                b.setNote(item);
                b.setPresenter(presenter);
            }
        });

        RxBus.get().register(presenter);
        b.setNotePresenter(presenter);

        // 初始化日记列表
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        b.recyclerView.setLayoutManager(staggeredGridLayoutManager);
        b.recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.initNotes();
    }
    @Override
    public void initNotes(List<NoteInfo> notes) {
        adapter.initList(notes);
    }

    @Override
    public void addNote(NoteInfo note) {
      adapter.add(note);
    }

    @Override
    public void updateNote(int i,NoteInfo note) {
      adapter.update(i,note);
    }
    @Override
    public void removeNote(int i) {
      adapter.remove(i);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(presenter);
    }
}


