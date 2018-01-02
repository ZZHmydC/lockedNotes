package com.example.irene.lockednotes.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Irene on 2018/1/2.
 */

public class Folder extends DataSupport {

    private int id;

    private String folderName;

    private int folderCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getFolderCode() {
        return folderCode;
    }

    public void setFolderCode(int folderCode) {
        this.folderCode = folderCode;
    }

}
