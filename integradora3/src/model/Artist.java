package model;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <b>Class: </b> Artist <br>
 */
public class Artist extends Producer implements Comparable<Artist> {    
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
     * @param views Initial views (0).
     */
    public Artist(String nickname, String id, LocalDate date, String name, String urlImage, int views) {
        super(nickname, id, date, name, urlImage, views);
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

    /**
     * <b>name: </b> updateSong <br>
     * Updates a song's stats as it's played. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Updates song.
     * @param song involved song.
     */
    public void updateSong(Song song) {
        boolean found = false;
        for (int i = 0; i < songs.size() && !found; i++) {
            if (songs.get(i).getName().equalsIgnoreCase(song.getName())) {
                songs.remove(i);
                songs.add(song);
                getTotalRep();
            }
        }
    }

    /**
     * <b>name: </b> getTotalRep <br>
     * Returns the views of an artist. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns views.
     * @return <b>views</b>. Views of the artist.
     */
    public int getTotalRep() {
        int views = super.getTotalRep();
        for (int i = 0; i < songs.size(); i++) {
            views += songs.get(i).getViews();
        }
        this.views = views;
        return views;
    }

    /**
     * <b>name: </b> getSongs <br>
     * Returns the list of songs of an artist. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns list.
     * @return <b>songs</b>. List of songs of the artist.
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }

    @Override
    public int compareTo(Artist o) {
        return views.compareTo(o.getTotalRep());
    }
}
