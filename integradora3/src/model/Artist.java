package model;
import java.time.LocalDate;
import java.util.ArrayList;

public class Artist extends Producer {    
    //constants

    //attributes

    //relations
    private ArrayList<Song> songs;
    
    //methods
    public Artist(String nickname, String id, LocalDate date, String name, String urlImage) {
        super(nickname, id, date, name, urlImage);
        this.songs = new ArrayList<Song>();
    }

    public void addSong(Audio audio) {
        Song song = (Song) audio;
        songs.add(song);
    }

    public Song searchSong(String name) {
        Song song = null;
        boolean found = false;
        for (int i = 0; i < songs.size() && !found; i++) {
            if (songs.get(i).getName().equalsIgnoreCase(name)) {
                song = songs.get(i);
                found = true;
            }
        }
        return song;
    }
}
