package com.example.android.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // music data creation
        final ArrayList<Song> songList = new ArrayList<>();
        songList.add(new Song("If Today Was Your Last Day", "Nickelback", "Dark Horse", R.drawable.nickelback_if_today));
        songList.add(new Song("One Day Too Late", "Skillet", "Awake"));
        songList.add(new Song("Hotel California (live)", "Eagles", "Hotel California", R.drawable.hotel_california));
        songList.add(new Song("Туда", "Михей и Джуманджи", "Сука Любовь"));
        songList.add(new Song("Miracle(Above & Beyond remix)", "Ocean Lab"));
        songList.add(new Song("Ryan", "Eyes Set To Kill", "Broken Frames", R.drawable.broken_frames));
        songList.add(new Song("Wish You Were Here", "Avril Lavigne", "Goodbye Lullaby", R.drawable.goodbyelullaby));
        songList.add(new Song("Flaming June", "BT", "ESCM", R.drawable.bt_escm));
        songList.add(new Song("Universal Universe", "Ilya Soloviev", "Trance Nation (Mixed By Above & Beyond)"));
        songList.add(new Song("Danza Kuduro", "Don Omar feat. Lucenzo", "Meet The Orphans", R.drawable.meet_the_orphans));
        songList.add(new Song("Game On (Ananda Shake remix)", "System Nipel feat. Electra"));
        songList.add(new Song("Key To Your Heart", "Rappers Against Racism", "The Message", R.drawable.rappers_against_racism_the_message));
        songList.add(new Song());
        MusicDataHolder.getInstance().setDataList(songList); //storing music data for further usage

        //intents to artists' and albums' activities
        TextView artists = (TextView) findViewById(R.id.songs_artists_header);
        artists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent artistsIntent = new Intent(MainActivity.this, ArtistsActivity.class);
                startActivity(artistsIntent);
                Toast.makeText(view.getContext(), "Open list of artists", Toast.LENGTH_LONG).show();
            }
        });

        TextView albums = (TextView) findViewById(R.id.songs_albums_header);
        albums.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent albumsIntent = new Intent(MainActivity.this, AlbumsActivity.class);
                startActivity(albumsIntent);
                Toast.makeText(view.getContext(), "Open list of albums", Toast.LENGTH_LONG).show();
            }
        });

        // sorting list of songs ascending by song names
        Collections.sort(songList, new Comparator<Song>(){
            public int compare(Song obj1, Song obj2) {
                // ## Ascending order
                return obj1.getSongName().compareToIgnoreCase(obj2.getSongName());
            }
        });

        // launching adapter for listView of songs
        SongAdapter adapter = new SongAdapter(this, songList);
        ListView listView = (ListView) findViewById(R.id.songs_list);
        listView.setAdapter(adapter);

        // listener for song items in listView, intent to currently "playing" song
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent currentSongIntent = new Intent(MainActivity.this, CurrentSongActivity.class);
                currentSongIntent.putExtra("songs", songList); // passing all data to next activity
                currentSongIntent.putExtra("position", position); // saving position of clicked item for representation on details activity
                startActivity(currentSongIntent);
                Toast.makeText(view.getContext(), "Open song details", Toast.LENGTH_LONG).show();
            }
        });

    }

}
