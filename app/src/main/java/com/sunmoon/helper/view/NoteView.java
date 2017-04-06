package com.sunmoon.helper.view;

import com.sunmoon.helper.model.NoteInfo;

import java.util.List;

/**
 * Created by SunMoon on 2017/1/2.
 */

public interface NoteView {
    void initNotes(List<NoteInfo> notes);
    void addNote(NoteInfo note);
    void updateNote(int i,NoteInfo note);
    void removeNote(int note);
}
