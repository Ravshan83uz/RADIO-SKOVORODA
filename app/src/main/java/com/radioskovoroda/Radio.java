package com.radioskovoroda;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Radio {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("serverName")
    @Expose
    private String serverName;
    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("track")
    @Expose
    private String track;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("bitrate")
    @Expose
    private Integer bitrate;
    @SerializedName("promo")
    @Expose
    private Integer promo;
    @SerializedName("listeners")
    @Expose
    private Integer listeners;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Radio withType(String type) {
        this.type = type;
        return this;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Radio withServerName(String serverName) {
        this.serverName = serverName;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Radio withArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public Radio withTrack(String track) {
        this.track = track;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Radio withGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public Integer getBitrate() {
        return bitrate;
    }

    public void setBitrate(Integer bitrate) {
        this.bitrate = bitrate;
    }

    public Radio withBitrate(Integer bitrate) {
        this.bitrate = bitrate;
        return this;
    }

    public Integer getPromo() {
        return promo;
    }

    public void setPromo(Integer promo) {
        this.promo = promo;
    }

    public Radio withPromo(Integer promo) {
        this.promo = promo;
        return this;
    }

    public Integer getListeners() {
        return listeners;
    }

    public void setListeners(Integer listeners) {
        this.listeners = listeners;
    }

    public Radio withListeners(Integer listeners) {
        this.listeners = listeners;
        return this;
    }

}