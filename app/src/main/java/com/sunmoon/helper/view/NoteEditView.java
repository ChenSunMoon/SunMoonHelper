package com.sunmoon.helper.view;

/**
 * Created by SunMoon on 2017/1/5.
 */

public interface NoteEditView extends View {
    void undo();
    void redo();
    void setBold();
    void setItalic();
    void setSubscript();
    void setSuperscript();
    void setStrikeThrough();
    void setUnderline();
    void setHeading(int heading);
    void setTextBackgroundColor(int color);
    void setIndent();
    void setOutdent();
    void setAlignLeft();
    void setAlignCenter();
    void setAlignRight();
    void setBlockquote();
    void setBullets();
    void setNumbers();
    void insertImage(String url, String alt);
    void insertLink(String href, String title);
    void insertTodo();

    void setHtml(String content);
    String getHtml();
}
