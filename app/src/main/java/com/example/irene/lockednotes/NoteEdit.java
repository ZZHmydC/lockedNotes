package com.example.irene.lockednotes;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.irene.lockednotes.db.Note;

import org.litepal.tablemanager.Connector;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteEdit extends AppCompatActivity {

    private ScrollView noteLayout;

    private TextView titleTime;

    private EditText noteDetailEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //实现背景图和状态栏融合
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        //防止标题栏和背景图被软键盘顶上去
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        setContentView(R.layout.note_edit);

        noteLayout = (ScrollView) findViewById(R.id.note_layout);

        titleTime = (TextView) findViewById(R.id.title_time);
        titleTime.setText(getTime());

        noteDetailEdit = (EditText) findViewById(R.id.note_detail_edit);
        if (noteDetailEdit.getContext() != null) {
            Note note = new Note();
            setNoteDetail(note.getContent());
        }
        noteDetailEdit.requestFocus();
        InputMethodManager imm = (InputMethodManager) noteDetailEdit.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

        //新建表项目
        //Connector.getDatabase();

        //返回键存储内容并结束当前进程
        Button backBt = (Button) findViewById(R.id.bk_button);
        backBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editNote();
                hintKeyboard();
                finish();
            }
        });
    }

    //获取当前时间作为title
    private String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date();
        String str = format.format(curDate);
        return str;
    }

    //存储数据
    private void editNote() {
        Note note = new Note();
        note.setNoteName(titleTime.toString());
        note.setContent(noteDetailEdit.getText().toString());
        if (note.isSaved() == false) {
            note.setPublishDate(titleTime.toString());
            note.save();
        } else {
            note.updateAll("publishDate = ?", note.getPublishDate());
        }
    }

    public void setNoteDetail(String content) {
        noteDetailEdit.setText(content);
    }

    private void hintKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&getCurrentFocus()!=null){
            if (getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

}
