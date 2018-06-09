package com.example.android.musicplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CurrentSongActivity extends AppCompatActivity {

    private int playPauseFlag = 0;
    private int posPlusMinus; // "playing" position tracker in ArrayList songList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_playing);

        // getting passed from MainActivity music data and № of clicked item
        final ArrayList<Song> songList = (ArrayList<Song>) getIntent().getSerializableExtra("songs");
        int pos = getIntent().getIntExtra("position", 0);
        posPlusMinus = pos;

        final ImageView imageView = (ImageView) findViewById(R.id.album_pic);
        final TextView artist = (TextView) findViewById(R.id.details_artist);
        final TextView songAndAlbum = (TextView) findViewById(R.id.details_song_and_album);

        artist.setSelected(true);       // enable AUTOMATIC scrolling when text
        songAndAlbum.setSelected(true); // is bigger than available line space

        if(songList.get(pos).getImage() == -1) {
            imageView.setImageResource(R.drawable.yellow_metal_square);}
        else {
            imageView.setImageResource(songList.get(pos).getImage());
        }
        artist.setText(songList.get(pos).getArtist());
        songAndAlbum.setText(songList.get(pos).getSongName() + " [" + songList.get(pos).getAlbum() + "]");

        // manage player buttons functionality
        final Button playPause = (Button) findViewById(R.id.btn_play);
        Button trackFwd = (Button) findViewById(R.id.btn_track_fwd);
        Button trackBwd = (Button) findViewById(R.id.btn_track_back);

        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playPauseFlag == 0){
                    playPause.setBackgroundResource(R.drawable.player_pause);
                    playPauseFlag = 1;
                }
                else {
                    playPause.setBackgroundResource(R.drawable.player_play);
                    playPauseFlag = 0;
                }
            }
        });

        trackFwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posPlusMinus += 1;
                if (posPlusMinus == songList.size()) {
                    posPlusMinus = 0;
                }
                if(songList.get(posPlusMinus).getImage() == -1) {
                    imageView.setImageResource(R.drawable.yellow_metal_square);}
                else {
                    imageView.setImageResource(songList.get(posPlusMinus).getImage());
                }
                artist.setText(songList.get(posPlusMinus).getArtist());
                songAndAlbum.setText(songList.get(posPlusMinus).getSongName() + " [" + songList.get(posPlusMinus).getAlbum() + "]");
            }
        });

        trackBwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posPlusMinus-= 1;
                if (posPlusMinus == -1) {
                    posPlusMinus = songList.size() - 1;
                }
                if(songList.get(posPlusMinus).getImage() == -1) {
                    imageView.setImageResource(R.drawable.yellow_metal_square);}
                else {
                    imageView.setImageResource(songList.get(posPlusMinus).getImage());
                }
                artist.setText(songList.get(posPlusMinus).getArtist());
                songAndAlbum.setText(songList.get(posPlusMinus).getSongName() + " [" + songList.get(posPlusMinus).getAlbum() + "]");
            }
        });
    }
}
