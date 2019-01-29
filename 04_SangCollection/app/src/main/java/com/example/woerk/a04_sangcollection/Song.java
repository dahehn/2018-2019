package com.example.woerk.a04_sangcollection;

import java.io.Serializable;

public class Song implements Serializable {

    //fields
    int tracknumber;
    int playtime;
    String name;
    String artist;

    //ctor
    public Song(int tracknumber, String artist, String name, int playTime) {
        this.artist=artist;
        this.name=name;
        this.playtime=playTime;
        this.tracknumber=tracknumber;
    }

    //get
    public String getArtist() {
        return artist;
    }
    public String getName() {
        return name;
    }
    public int getPlaytime() {
        return playtime;
    }
    public int getTracknumber() {
        return tracknumber;
    }

    //set
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPlaytime(int playtime) {
        this.playtime = playtime;
    }

    //methods

}
