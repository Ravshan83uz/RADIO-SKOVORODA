package com.theunderdog.jsonparcing;
import java.io.Serializable;


public class JsonParcer implements Serializable {
    private String type;
    private String serverName;
    private String artist;
    private String track;
    private String genre;
    private String bitrate;
    private String promo;
    private String listeners;

    public String getType() {
        return type;
    }

    public void setType(String Type) {
        this.type = type;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String Servername) {
        this.serverName = serverName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTrack() {
        return track;
    }

    public void setProcessor(String track) {
        this.track = track;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }
}


