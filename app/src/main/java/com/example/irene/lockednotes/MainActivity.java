package com.example.irene.lockednotes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.irene.lockednotes.db.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Note> noteList = new ArrayList<>();

    private TextView appTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appTitle = (TextView) findViewById(R.id.app_title);
        appTitle.setText("Locked Notes");

        initNotes();    //初始化便签数据
        final ListViewAdapter adapter = new ListViewAdapter(MainActivity.this, R.layout.cell, noteList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = noteList.get(position);
                if (note.getLock() == true) {
                    //Toast.makeText(MainActivity.this, "locked", Toast.LENGTH_SHORT).show();
                    showUnlockDialog(view);
                } else {
                    Intent intent = new Intent(MainActivity.this, NoteEdit.class);
                    startActivity(intent);
                }
            }
        });

        Button addBt = (Button) findViewById(R.id.add_button);
        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteEdit.class);
                startActivity(intent);
            }
        });
    }

    private void initNotes() {
        for (int i=0; i<5; i++) {
            Note note1 = new Note();
            note1.setLock(true);
            note1.setNoteName("2018-1-3 18:07");
            note1.setContent("发烧真难受");
            note1.setPublishDate("2018-1-3 18:07");
            noteList.add(note1);

            Note note2 = new Note();
            note2.setLock(false);
            note2.setNoteName("2018-1-3 18:07");
            note2.setContent("发烧真难受");
            note2.setPublishDate("2018-1-3 18:07");
            noteList.add(note2);
        }
    }


    /*设置弹出对话框*/

    private UnlockedDialog dialog;

    public void showUnlockDialog(View view) {
        dialog = new UnlockedDialog(this, R.style.dialog, onClickListener);
        dialog.show();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.confirm_button:
                    if (dialog.password.getText().toString().equals("15081201")){
                        Intent intent = new Intent(MainActivity.this, NoteEdit.class);
                        startActivity(intent);
                        dialog.cancel();
                    } else {
                        Toast.makeText(MainActivity.this, "Password error!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private void hintKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&getCurrentFocus()!=null){
            if (getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

}
