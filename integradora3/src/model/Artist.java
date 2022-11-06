package model;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <b>Class: </b> Artist <br>
 */
public class Artist extends Producer {    
    //constants

    //attributes

    //relations
    private ArrayList<Song> songs;
    
    //methods
    /**
     * <b>name: </b> Artist <br>
     * This is the constructor of the class "Artist". <br>
     * @param nickname Nickname of the artist.
     * @param id Id of the artist.
     * @param date Registration date.
     * @param name Name of the artist.
     * @param urlImage URL icon.
     */
    public Artist(String nickname, String id, LocalDate date, String name, String urlImage) {
        super(nickname, id, date, name, urlImage);
        this.songs = new ArrayList<Song>();
    }

    /**
     * <b>name: </b> addSong <br>
     * Adds a song to an artist. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Adds the song.
     * @param audio Audio to be added.
     */
    public void addSong(Audio audio) {
        Song song = (Song) audio;
        songs.add(song);
    }

    /**
     * <b>name: </b> searchSong <br>
     * Search for a song from a given name. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the song if it is found.
     * @param name Name of the song to be searched.
     * @return <b>song</b>. Contains the song if it is found.
     */
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
