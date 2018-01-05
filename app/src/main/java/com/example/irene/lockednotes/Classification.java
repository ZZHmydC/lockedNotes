package com.example.irene.lockednotes;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.irene.lockednotes.db.Folder;
import com.example.irene.lockednotes.db.Note;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irene on 2018/1/3.
 */

public class Classification extends Fragment {

    public static final int LEVEL_FOLDER = 0;

    public static final int LEVEL_NOTE = 1;

    private TextView titleText;

    private Button backButton;

    private ListView listView;

    //private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<>();

    private List<Folder> folderList;    //文件夹列表

    //private List<Note> noteList;    //便签列表

    private Folder selectedFolder;  //选中的分类

    private int currentLevel;  //选中的级别

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classification, container, false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        backButton = (Button) view.findViewById(R.id.back_button);
        listView = (ListView) view.findViewById(R.id.list_view);
        //adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_FOLDER) {
                    selectedFolder = folderList.get(position);
                    queryNotes();
                } else if (currentLevel == LEVEL_NOTE) {
                    //String noteCode = noteList.get(position).getNoteName();
                    Intent intent = new Intent(getActivity(), NoteEdit.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_NOTE) {
                    queryFolders();
                }
            }
        });
        queryFolders();
    }

    private ListViewAdapter adapter;
    private List<Note> noteList = new ArrayList<>();

    private void queryFolders() {
        titleText.setText("My Folder");
        backButton.setVisibility(View.GONE);
        /*folderList = DataSupport.findAll(Folder.class);
        if (folderList.size() > 0) {
            dataList.clear();
            for (Folder folder : folderList) {
                dataList.add(folder.getFolderName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_FOLDER;
        } else {
            Folder folder1 = new Folder();
            folder1.setLock(true);
            folder1.setFolderCode(0);
            folder1.setFolderName("Folder 1");
            folderList.add(folder1.getFolderName());
            for (int i = 0; i < 3; i++) {
                Folder folder2 = new Folder();
                folder2.setLock(false);
                folder2.setFolderCode(i);
                folder2.setFolderName("Folder" + (i+1));
                folderList.add(folder2);
            }
        }*/

    }

    private void queryNotes() {  //查询选中的市内所有的县，优先从数据库中查询，如果没有查询到再去服务器上查询
        titleText.setText(selectedFolder.getFolderName());
        backButton.setVisibility(View.VISIBLE);
        noteList = DataSupport.where("noteid = ?", String.valueOf(selectedFolder.getId())).find(Note.class);
        if (noteList.size() > 0) {
            dataList.clear();
            for (Note note : noteList) {
                dataList.add(note.getNoteName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel = LEVEL_NOTE;
        } else {
            int folderCode = selectedFolder.getFolderCode();
            if (folderCode == 0) {
                Note note1 = new Note();
                note1.setLock(true);
                note1.setNoteName("2018-1-3 19:56");
                note1.setContent("37.3");
                note1.setPublishDate("2018-1-3 19:56");
                noteList.add(note1);
            } else {
                Note note2 = new Note();
                note2.setLock(false);
                note2.setNoteName("2018-1-3 19:56");
                note2.setContent("发烧真难受");
                note2.setPublishDate("2018-1-3 19:56");
                noteList.add(note2);
            }
        }
        /*final ListViewAdapter adapter = new ListViewAdapter(MainActivity.this, R.layout.cell, noteList);
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
        });*/
    }
/*
    private ProgressDialog progressDialog;

    private void showProgressDialog() {     //显示进度对话框
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    private void closeProgressDialog() {        //关闭进度对话框
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
*/
}
