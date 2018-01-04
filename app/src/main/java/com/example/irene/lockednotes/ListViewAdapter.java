package com.example.irene.lockednotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.irene.lockednotes.db.Note;

import java.util.List;

/**
 * Created by Irene on 2018/1/4.
 */

public class ListViewAdapter extends ArrayAdapter<Note> {

    private int resourceId;

    public ListViewAdapter( Context context, int textViewResourceId, List<Note> objects) {  //传入Adapter的参数，并创建Adapter
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note = getItem(position);  //获取当前Note实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView cellTitle = (TextView) view.findViewById(R.id.cell_title);
        TextView cellContent = (TextView) view.findViewById(R.id.cell_content);
        ImageView cellLock = (ImageView) view.findViewById(R.id.cell_lock);
        cellTitle.setText(note.getNoteName());
        cellContent.setText(note.getContent());
        cellLock.setImageResource(note.getId());
        return view;
    }

}
