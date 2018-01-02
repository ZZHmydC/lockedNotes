package com.example.irene.lockednotes.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Irene on 2018/1/2.
 */

public class Note extends DataSupport {

    private int id;

    private String noteName;

    private int noteCode;

    private int folderId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public int getNoteCode() {
        return noteCode;
    }

    public void setNoteCode(int noteCode) {
        this.noteCode = noteCode;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

}
