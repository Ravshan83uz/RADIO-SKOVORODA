package com.radioskovoroda;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSON {


    public class Example {

        @SerializedName("radio")
        @Expose
        private Radio radio;
        @SerializedName("music")
        @Expose
        private Music music;

        public Radio getRadio() {
            return radio;
        }

        public void setRadio(Radio radio) {
            this.radio = radio;
        }

        public Music getMusic() {
            return music;
        }

        public void setMusic(Music music) {
            this.music = music;
        }

    }
}
