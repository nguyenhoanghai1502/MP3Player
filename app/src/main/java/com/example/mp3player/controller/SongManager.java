package com.example.mp3player.controller;

import com.example.mp3player.model.SongModel;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class SongManager {
    final String MEDIA_PATH= "/sdcard/Download/";
    private final ArrayList<SongModel> songlist=new ArrayList<SongModel>();

    public ArrayList<SongModel> getSonglist(){
        File home=new File(MEDIA_PATH);
        if(home.listFiles(new FileExtensionFilter()).length>0){
            for(File file:home.listFiles(new FileExtensionFilter())) {
                SongModel model = new SongModel();
                //lay ten bai hat va duong dan
                model.title = file.getName().substring(0, (file.getName().length() - 4));//tru duoi mp3 ra
                model.path = file.getPath();

                //them bai hat vao danh sach
                songlist.add(model);
            }
        }
        return songlist;
    }
    class FileExtensionFilter implements FilenameFilter{
        public boolean accept(File dir, String name){
            return (name.endsWith(".m4a")||name.endsWith(".M4A"));
        }
    }
}

