package com.example.irene.lockednotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
        ListViewAdapter adapter = new ListViewAdapter(MainActivity.this, R.layout.cell, noteList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = noteList.get(position);
                Intent intent = new Intent(MainActivity.this, NoteEdit.class);
                startActivity(intent);
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
        for (int i=0; i<20; i++) {
            Note note1 = new Note();
            noteList.add(note1);
        }
    }
}
