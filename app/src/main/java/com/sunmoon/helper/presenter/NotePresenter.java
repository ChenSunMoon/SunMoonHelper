package com.sunmoon.helper.presenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.sunmoon.helper.App;
import com.sunmoon.helper.activity.NoteEditActivity;
import com.sunmoon.helper.model.NoteInfo;
import com.sunmoon.helper.model.NoteInfoDao;
import com.sunmoon.helper.view.NoteView;

import java.util.List;

/**
 * Created by SunMoon on 2017/1/2.
 */

public class NotePresenter extends Presenter {
    private NoteView noteView;
    private NoteInfoDao dao;
    private Activity activity;
    private List<NoteInfo> notes;
    public NotePresenter(Activity activity){
        this.activity=activity;
        dao=App.getDaosession().getNoteInfoDao();
        
    }
     @Subscribe(
        thread = EventThread.MAIN_THREAD,
        tags = {@Tag(NoteEditPresenter.TAG_UPDATE_NOTE)}
    )
   public void updateNote(NoteInfo note){
         dao.update(note);
         for (int i =0; i<notes.size(); i++){
             if (note.getId() == notes.get(i).getId()){
                 noteView.updateNote(i,note);
                 break;
             }
         }

     }
    public void initNotes(){
        notes= dao.loadAll();
        noteView.initNotes(notes);
    }
    public void editNote(View view,NoteInfo note){
        Intent intent=new Intent(view.getContext(),NoteEditActivity.class);
        Gson gson=new Gson();
        intent.putExtra("note", gson.toJson(note,NoteInfo.class));
        view.getContext().startActivity(intent);
    }
    public boolean removeNote(View view, final NoteInfo note){
        AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                .setTitle("提醒")
                .setMessage("确认删除？")
                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.delete(note);
                        noteView.removeNote(notes.indexOf(note));
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();


        return true;
    }

    public NoteView getNoteView() {
        return noteView;
    }

    public void setNoteView(NoteView noteView) {
        this.noteView = noteView;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
    public void addNewNote(View v){
        Intent intent=new Intent(v.getContext(), NoteEditActivity.class);
        v.getContext().startActivity(intent);
    }

}
