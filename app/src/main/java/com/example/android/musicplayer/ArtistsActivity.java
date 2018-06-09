package com.example.android.musicplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArtistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        // get artists' data
        ArrayList<String> artists = new ArrayList<>();
        ArrayList<Song> songList = MusicDataHolder.getInstance().getDataList();
        for (int i=0; i < songList.size(); i++) {
            artists.add(songList.get(i).getArtist());
        }

        // filter duplicate artists' names
        ArrayList<String> noRepeat = new ArrayList<String>();
        for (String artist : artists) {
            boolean isFound = false;
            // check if artist exists in noRepeat
            for (String a : noRepeat) {
                if (a.equals(artist)) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {noRepeat.add(artist);}
        }


        // sorting list of songs ascending by song names
        Collections.sort(noRepeat, new Comparator<String>(){
            public int compare(String obj1, String obj2) {
                // ## Ascending order
                return obj1.compareToIgnoreCase(obj2); // To compare string values
            }
        });

        // launching adapter for listView of artists
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noRepeat);
        ListView listView = (ListView) findViewById(R.id.artists_list);
        listView.setAdapter(adapter);

        //intents to songs' and albums' activities
        TextView songs = (TextView) findViewById(R.id.artists_songs_header);
        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(ArtistsActivity.this, MainActivity.class);
                startActivity(songsIntent);
                Toast.makeText(view.getContext(), "Open list of songs",Toast.LENGTH_LONG).show();
            }
        });

        TextView albums = (TextView) findViewById(R.id.artists_albums_header);
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent albumsIntent = new Intent(ArtistsActivity.this, AlbumsActivity.class);
                startActivity(albumsIntent);
                Toast.makeText(view.getContext(), "Open list of albums",Toast.LENGTH_LONG).show();
            }
        });
    }

}
