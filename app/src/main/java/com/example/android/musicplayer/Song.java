package com.example.android.musicplayer;

import java.io.Serializable;

public class Song implements Serializable { // Serializable here is for passing extra in intent to CurrentSongActivity

    private String mSongName;
    private String mArtist;
    private String mAlbum;
    private int mImageResourceId;

    public Song() {
        mSongName = "Unknown song";
        mArtist = "Unknown artist";
        mAlbum = "Unknown album";
        mImageResourceId = -1;
    }

    public Song(String songName, String artist) {
        mSongName = songName;
        mArtist = artist;
        mAlbum = "Unknown album";
        mImageResourceId = -1;
    }

    public Song(String songName, String artist, String album) {
        mSongName = songName;
        mArtist = artist;
        mAlbum = album;
        mImageResourceId = -1;
    }

    public Song(String songName, String artist, String album, int imageResourceId) {
        mSongName = songName;
        mArtist = artist;
        mAlbum = album;
        mImageResourceId = imageResourceId;
    }


    public String getSongName() {return mSongName;}
    public String getArtist() {return mArtist;}
    public String getAlbum() {return mAlbum;}
    public int getImage() {return mImageResourceId;}

}
