package com.radioskovoroda;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentSong {

    @SerializedName("radio")
    @Expose
    private Radio radio;
    @SerializedName("music")
    @Expose
    private Music music;


    public CurrentSong(Radio radio, Music music) {
        super();
        this.radio = radio;
        this.music = music;
    }

    public Radio getRadio() {
        return radio;
    }

    public void setRadio(Radio radio) {
        this.radio = radio;
    }

    public CurrentSong withRadio(Radio radio) {
        this.radio = radio;
        return this;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public CurrentSong withMusic(Music music) {
        this.music = music;
        return this;
    }

}
