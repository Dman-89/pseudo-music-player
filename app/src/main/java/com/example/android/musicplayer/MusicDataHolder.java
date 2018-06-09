package com.example.android.musicplayer;

import java.util.ArrayList;
import java.util.List;

public class MusicDataHolder {

    private static MusicDataHolder dataHolder; // pseudo variable for getting ArrayList of Songs
                                               // in external classes like AlbumsActivity
    private ArrayList<Song> dataList;

    public void setDataList(ArrayList<Song> dataList) {
        this.dataList = dataList;
    }

    public ArrayList<Song> getDataList() {
        return dataList;
    }

    public synchronized static MusicDataHolder getInstance() {
        if (dataHolder == null) {
            dataHolder = new MusicDataHolder();
        }
        return dataHolder;
    }
}