package com.sunmoon.helper.presenter;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Produce;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.sunmoon.helper.App;
import com.sunmoon.helper.model.NoteInfo;
import com.sunmoon.helper.model.NoteInfoDao;
import com.sunmoon.helper.utils.TimeUtil;
import com.sunmoon.helper.view.NoteEditView;
import com.sunmoon.helper.view.View;

import org.joda.time.DateTime;
import org.joda.time.JodaTimePermission;

/**
 * Created by SunMoon on 2017/1/5.
 */

public class NoteEditPresenter extends Presenter {
    private NoteEditView view;
    private NoteInfo note;
    NoteInfoDao dao;
    private Activity context;
    public final static String TAG_UPDATE_NOTE="TAG_UPDATE_NOTE";
    public NoteEditPresenter(Activity context) {
        this.context=context;
        dao=App.getDaosession().getNoteInfoDao();

    }
    public void initNote() {
        Gson gson=new Gson();
        note =gson.fromJson(context.getIntent().getStringExtra("note"),NoteInfo.class);
        if (note!=null){
            // 更改笔记
            view.setHtml(note.getContent());
        } else {
            // 新建笔记
            note =new NoteInfo();
            note.setId(System.currentTimeMillis());
            note.setCreateTime(TimeUtil.getNow());
            dao.insert(note);
        }
    }
    public void saveNote(String html){
        note.setContent(html);
        RxBus.get().post(TAG_UPDATE_NOTE, note);
    }
    @Override
    public void setView(View view) {
        this.view = (NoteEditView) view;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onResume() {

    }

    public void undo() {
        view.undo();
    }


    public void redo() {
        view.redo();
    }


    public void setBold() {
        view.setBold();
    }


    public void setItalic() {
        view.setItalic();
    }


    public void setSubscript() {
        view.setSubscript();
    }


    public void setSuperscript() {
        view.setSuperscript();
    }


    public void setStrikeThrough() {
        view.setStrikeThrough();
    }


    public void setUnderline() {
        view.setUnderline();
    }


    public void setHeading(int heading) {
        view.setHeading(heading);
    }

    public void setTextBackgroundColor(int color) {
        view.setTextBackgroundColor(color);
    }


    public void setIndent() {
        view.setIndent();
    }


    public void setOutdent() {
        view.setOutdent();
    }


    public void setAlignLeft() {
        view.setAlignLeft();
    }


    public void setAlignCenter() {
        view.setAlignCenter();
    }


    public void setAlignRight() {
        view.setAlignRight();
    }


    public void setBlockquote() {
        view.setBlockquote();
    }


    public void setBullets() {
        view.setBullets();
    }


    public void setNumbers() {
        view.setNumbers();
    }


    public void insertImage(String url, String alt) {
        view.insertImage(url,alt);
    }


    public void insertLink(String href, String title) {
        view.insertLink(href,title);
    }

    public void insertTodo() {
        view.insertTodo();
    }


}
