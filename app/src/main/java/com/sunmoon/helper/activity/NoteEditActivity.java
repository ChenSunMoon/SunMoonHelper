package com.sunmoon.helper.activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.hwangjr.rxbus.RxBus;
import com.sunmoon.helper.R;
import com.sunmoon.helper.databinding.ActivityNoteEditBinding;
import com.sunmoon.helper.presenter.NoteEditPresenter;
import com.sunmoon.helper.view.NoteEditView;

import jp.wasabeef.richeditor.RichEditor;

/**
 * Created by SunMoon on 2017/1/2.
 */

public class NoteEditActivity extends BaseActivity implements NoteEditView {
    private ActivityNoteEditBinding b;
    NoteEditPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=DataBindingUtil.setContentView(this, R.layout.activity_note_edit);
        presenter=new NoteEditPresenter(this);
        presenter.setView(this);
        presenter.initNote();
        RxBus.get().register(presenter);
        b.setPresenter(presenter);
        b.noteEdit.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String s) {

            }
        });
        b.noteEdit.setEnabled(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.saveNote(b.noteEdit.getHtml());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(presenter);
    }

    @Override
    public void undo() {
    b.noteEdit.undo();
    }

    @Override
    public void redo() {
       b.noteEdit.redo();
    }

    @Override
    public void setBold() {
        b.noteEdit.setBold();
    }

    @Override
    public void setItalic() {
       b.noteEdit.setItalic();
    }

    @Override
    public void setSubscript() {
        b.noteEdit.setSubscript();
    }

    @Override
    public void setSuperscript() {
        b.noteEdit.setSuperscript();
    }

    @Override
    public void setStrikeThrough() {
        b.noteEdit.setStrikeThrough();
    }

    @Override
    public void setUnderline() {
        b.noteEdit.setUnderline();
    }

    @Override
    public void setHeading(int heading) {
        b.noteEdit.setHeading(heading);
    }

    @Override
    public void setTextBackgroundColor(int color) {
        b.noteEdit.setTextBackgroundColor(color);
    }

    @Override
    public void setIndent() {
        b.noteEdit.setIndent();
    }

    @Override
    public void setOutdent() {
        b.noteEdit.setOutdent();
    }

    @Override
    public void setAlignLeft() {
        b.noteEdit.setAlignLeft();
    }

    @Override
    public void setAlignCenter() {
        b.noteEdit.setAlignCenter();
    }

    @Override
    public void setAlignRight() {
        b.noteEdit.setAlignRight();
    }

    @Override
    public void setBlockquote() {
        b.noteEdit.setBlockquote();
    }

    @Override
    public void setBullets() {
        b.noteEdit.setBullets();
    }

    @Override
    public void setNumbers() {
        b.noteEdit.setNumbers();
    }

    @Override
    public void insertImage(String url, String alt) {
        b.noteEdit.insertImage(url,alt);
    }

    @Override
    public void insertLink(String href, String title) {
        b.noteEdit.insertLink(href,title);
    }


    @Override
    public void insertTodo() {
        b.noteEdit.insertTodo();
    }

    @Override
    public String getHtml() {
        return b.noteEdit.getHtml();
    }

    @Override
    public void setHtml(String content) {
        b.noteEdit.setHtml(content);
    }
}
