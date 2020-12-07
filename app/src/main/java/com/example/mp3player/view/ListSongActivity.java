package com.example.mp3player.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mp3player.R;
import com.example.mp3player.controller.ListSongAdapter;
import com.example.mp3player.model.SongModel;

public class ListSongActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Toolbar toolbar;
    private ListView listSongs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_songs);
        listSongs=(ListView) findViewById(R.id.list_songs);
        toolbar=(Toolbar) findViewById(R.id.toolbarlistview);
        toolbar.setTitle("List Songs");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.btnsonglist);
        setSupportActionBar(toolbar);

        ListSongAdapter adapter= new ListSongAdapter(this, PlayerActivity.arrSongs);
        listSongs.setAdapter(adapter);
        listSongs.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SongModel song=PlayerActivity.arrSongs.get(i);
        Intent intent=new Intent();
        intent.putExtra("id", i);
        setResult(RESULT_OK, intent);
        finish();
    }
}
