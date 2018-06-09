package com.example.android.musicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(Context context, ArrayList<Song> songsList) {
        super(context, 0, songsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.song_item, parent, false);
        }

        Song currentSong = getItem(position);

        TextView songName = (TextView) convertView.findViewById(R.id.title_textview);
        TextView artist = (TextView) convertView.findViewById(R.id.artist_textview);
        ImageView cover = (ImageView) convertView.findViewById(R.id.cover_imageview);

        songName.setText(currentSong.getSongName());
        artist.setText(currentSong.getArtist());
        if(currentSong.getImage()!= -1){
            cover.setImageResource(currentSong.getImage());
        }
        else cover.setImageResource(0); // this prevents from non-proper inflating rows by pics which belong to other songs

        return convertView;
    }
}
