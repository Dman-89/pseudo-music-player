package com.example.android.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class AlbumsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        ArrayList<Song> songList = MusicDataHolder.getInstance().getDataList();

        // filter duplicate album names
        ArrayList<Song> noRepeat = new ArrayList<Song>();
        for (Song song : songList) {
            boolean isFound = false;
            // check if the song album exists in noRepeat
            for (Song s : noRepeat) {
                if (s.getAlbum().equals(song.getAlbum()) || (s.equals(song))) {
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {noRepeat.add(song);}
        }


        // sorting list of songs ascending by song names
        Collections.sort(noRepeat, new Comparator<Song>(){
            public int compare(Song obj1, Song obj2) {
                // ## Ascending order
                return obj1.getAlbum().compareToIgnoreCase(obj2.getAlbum());
            }
        });

        // launching adapter for gridView of albums
        AlbumAdapter adapter = new AlbumAdapter(this, noRepeat);
        GridView gridView = (GridView) findViewById(R.id.albums_list);
        gridView.setAdapter(adapter);

        //intents to songs' and artists' activities
        TextView songs = (TextView) findViewById(R.id.albums_songs_header);
        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent songsIntent = new Intent(AlbumsActivity.this, MainActivity.class);
                startActivity(songsIntent);
                Toast.makeText(view.getContext(), "Open list of songs",Toast.LENGTH_LONG).show();
            }
        });

        TextView artists = (TextView) findViewById(R.id.albums_artists_header);
        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent artistsIntent = new Intent(AlbumsActivity.this, ArtistsActivity.class);
                startActivity(artistsIntent);
                Toast.makeText(view.getContext(), "Open list of artists",Toast.LENGTH_LONG).show();
            }
        });
    }
}
