package com.example.woerk.a04_sangcollection;

import java.util.ArrayList;
import java.util.List;

public class Album {
    public static List<Song> songsCollection;
    public boolean addSong(Song song) {
        for (Song s : songsCollection) {
            if (song.getTracknumber() == s.getTracknumber())
                return false;
        }
        songsCollection.add(song);
        return true;
    }
    public boolean deleteSong(int index) {
        songsCollection.remove(index);
        return true;
    }
    public boolean updateSong(Song song) {
        for (Song s : songsCollection) {
            if (s.getTracknumber() == song.getTracknumber()) {
                s.setArtist(song.getArtist());
                s.setName(song.getName());
                s.setPlaytime(song.getPlaytime());
                return true;
            }
        }
        return false;
    }
    public List<Song> allSongs() {
        return Album.songsCollection;
    }
    public int totalPlaytime() {
        int playTime = 0;
        for (Song s : songsCollection) {
            playTime += s.getPlaytime();
        }
        return playTime;
    }
    public Album() {
        if (Album.songsCollection == null) {
            Album.songsCollection = new ArrayList<Song>();
        }
    }
}
