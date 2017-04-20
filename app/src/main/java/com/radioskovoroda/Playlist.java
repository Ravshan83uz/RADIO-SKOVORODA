package com.radioskovoroda;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Playlist extends MainActivity  {
    @SerializedName("playlist")
    @Expose
    private ArrayList<Playlist> playlists = new ArrayList<>();
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist>playlists) {
        this.playlists = playlists;
    }

    }



