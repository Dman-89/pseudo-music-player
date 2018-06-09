package com.example.android.musicplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AlbumAdapter extends ArrayAdapter<Song> {

    public AlbumAdapter(Context context, ArrayList<Song> songList) {
        super(context, 0, songList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.album_item, parent, false);
        }

        Song currentSong = getItem(position);

        TextView album = (TextView) convertView.findViewById(R.id.album_title_textview);
        RelativeLayout albumCover = (RelativeLayout) convertView.findViewById(R.id.album_background);

        album.setSelected(true); // needed for enabling AUTOMATIC scrolling

        album.setText(currentSong.getAlbum());
        if(currentSong.getImage()!= -1){
            albumCover.setBackgroundResource(currentSong.getImage());
        }
        else albumCover.setBackgroundResource(R.drawable.common);

        return convertView;
    }
}
