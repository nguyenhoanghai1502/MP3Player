package com.example.mp3player.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.mp3player.R;
import com.example.mp3player.controller.SongManager;
import com.example.mp3player.model.SongModel;
import com.example.mp3player.util.ChuyenDoiTime;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, MediaPlayer.OnCompletionListener {
    public static final int SELECT_SONG_REQUEST =0;
    private Toolbar toolbar;
    private SongModel playingsong;
    public static ArrayList<SongModel> arrSongs=new ArrayList<>();
    private int seekForwardTime=5000;//5000 miliseconds
    private int seekBackwardTime=5000;//5000 miliseconds
    private int currentsongindex=0;

    private MediaPlayer mp;

    private Handler mHandler=new Handler();
    private ImageView btnPlay;
    private ImageView btnForward;
    private ImageView btnBackward;
    private ImageView btnNext;
    private ImageView btnPrevious;
    private SeekBar songProgressbar;
    private TextView lblCurrentDuration;
    private TextView lblTotalDuration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        toolbar=(Toolbar) findViewById(R.id.menusong);
        toolbar.setTitle("T-pame Mp3 Player");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.songicon);
        setSupportActionBar(toolbar);

        //lay bai hat tu sdcard
        SongManager songManager =new SongManager();
        arrSongs=songManager.getSonglist();

        //tao moi mediaplayer
        mp=new MediaPlayer();

        //Anh xa theo view
        btnPlay=(ImageView) findViewById(R.id.btnplay);
        btnBackward=(ImageView) findViewById(R.id.btnbackward);
        btnForward=(ImageView) findViewById(R.id.btnfoward);
        btnNext=(ImageView) findViewById(R.id.btnnextsong);
        btnPrevious=(ImageView) findViewById(R.id.btnbacksong);
        lblCurrentDuration=(TextView) findViewById(R.id.lbl_currentTime);
        lblTotalDuration=(TextView) findViewById(R.id.lbl_totalTime);
        songProgressbar=(SeekBar) findViewById(R.id.seekbartime);


        btnPlay.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnForward.setOnClickListener(this);
        btnBackward.setOnClickListener(this);


        songProgressbar.setOnSeekBarChangeListener(this);
        mp.setOnCompletionListener(this);

        playSong(currentsongindex);
    }

    private void playSong(int songindex) {
        try {
            mp.reset();
            mp.setDataSource(arrSongs.get(songindex).path);
            mp.prepare();
            mp.start();
            // thay doi tieu de menu thanh ten bai hat
            toolbar.setTitle(arrSongs.get(songindex).title);
            //thay doi nut thanh play
            btnPlay.setImageResource(R.drawable.btnpause);
            // reset lai seekbar
            songProgressbar.setProgress(0);
            songProgressbar.setMax(100);

            UpdateProgressbar();

            buildNotification();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildNotification() {
        Intent intent=new Intent(getApplicationContext(), PlayerActivity.class);
        PendingIntent pendingIntent=PendingIntent.getService(getApplicationContext(), 1, intent,0);
        Notification.Builder builder=new Notification.Builder(this).setSmallIcon(R.drawable.songicon).setContentTitle("MP3 Player").setContentText(arrSongs.get(currentsongindex).title).setDeleteIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }

    private void UpdateProgressbar() {
        mHandler.postDelayed(mUpdateTimeTask,100);
    }
    private Runnable mUpdateTimeTask=new Runnable() {
        @Override
        public void run() {
            try {
                long totalDuration=mp.getDuration();
                long currentDuration=mp.getCurrentPosition();

                //update tong thoi gian bai nhac
                lblTotalDuration.setText(""+ ChuyenDoiTime.miliSecondToTimer(totalDuration));

                //update thoi gian hien tai cua bai hat
                lblCurrentDuration.setText(""+ChuyenDoiTime.miliSecondToTimer(currentDuration));

                int progress=(int) (ChuyenDoiTime.getProgressPercentage(currentDuration,totalDuration));

                //update phan tram cua seekbar
                songProgressbar.setProgress(progress);
                //cu 100s thi chay 1 lan
                mHandler.postDelayed(this, 100);
            }catch (Exception ex){}
        }
    };

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        //neu an nut pause
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}