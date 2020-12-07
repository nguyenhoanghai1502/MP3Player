package com.example.mp3player.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mp3player.R;
import com.example.mp3player.model.SongModel;

import java.util.ArrayList;

public class ListSongAdapter extends BaseAdapter {
    private ArrayList<SongModel> arr=new ArrayList<>();
    private Context context;
    public ListSongAdapter(Context context, ArrayList<SongModel> arr){
        this.context=context;
        this.arr=arr;
    }
    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int i) {
        return arr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View rowview=layoutInflater.inflate(R.layout.song_item, viewGroup, false);
        TextView lblName=(TextView) rowview.findViewById(R.id.lblSongname);
        lblName.setText(arr.get(i).title);
        return rowview;
    }
}
