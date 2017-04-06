package com.sunmoon.helper.adapter;


import android.content.Context;
import android.databinding.DataBindingUtil;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sunmoon.helper.R;
import com.sunmoon.helper.databinding.ItemNoteBinding;
import com.sunmoon.helper.model.NoteInfo;
import com.sunmoon.helper.presenter.NotePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SunMoon on 2016/12/31.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private LayoutInflater inflater;
    private List<NoteInfo> notes;
    private NotePresenter present;
    public NoteAdapter(Context context){
        inflater= LayoutInflater.from(context);
        notes =new ArrayList<>();
    }
    public void initNotes(List<NoteInfo> notes){
        this.notes =notes;
    }
    public void addNote(NoteInfo noteInfo){
        this.notes.add(0,noteInfo);
        this.notifyItemInserted(0);
    }
   public void updateNote(int i,NoteInfo note){
       this.notes.set(i,note);
       this.notifyItemChanged(i);
   }
    public void removeNote(int i){
        this.notes.remove(i);
        this.notifyItemRemoved(i);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemNoteBinding b=DataBindingUtil.inflate(inflater,R.layout.item_note,parent,false);
        return new NoteViewHolder(b);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.setNote(notes.get(position));
        holder.setPresent(present);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setPresent(NotePresenter present) {
        this.present = present;
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private  ItemNoteBinding b;
        public NoteViewHolder(ItemNoteBinding b) {
            super(b.getRoot());
            this.b=b;

        }
        public void setPresent(NotePresenter present){
            b.setPresenter(present);
        }
        public void setNote(NoteInfo note){
            if (b!=null) {
                b.setNote(note);
            }
        }

    }
}
