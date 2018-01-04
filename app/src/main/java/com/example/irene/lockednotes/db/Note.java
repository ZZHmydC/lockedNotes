package com.example.irene.lockednotes.db;

import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by Irene on 2018/1/2.
 */

public class Note extends DataSupport {

    private int id;

    private String noteName;

    private String content;

    private String publishDate;

    private boolean lock;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public boolean getLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

}
