package model;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <b>Class: </b> Premium <br>
 */
public class Premium extends Consumer {
    //constants

    //attributes

    //relations
    private ArrayList<Shop> songs;
    private ArrayList<Playlist> playlists;
    
    //methods
    /**
     * <b>name: </b> Premium <br>
     * This is the constructor of the class "Premium". <br>
     * @param nickname Nickname of the user.
     * @param id Id of the user.
     * @param date Registration date.
     */
    public Premium(String nickname, String id, LocalDate date) {
        super(nickname, id, date);
        this.songs = new ArrayList<Shop>();
        this.playlists = new ArrayList<Playlist>();
    }

    /**
     * <b>name: </b> searchPlaylist <br>
     * Search for a playlist from a given name. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the playlist if it is found.
     * @param name Name of the playlist to be searched.
     * @return <b>playlist</b>. Contains the playlist if it is found.
     */
    public Playlist searchPlaylist(String name) {
        Playlist playlist = null;
        boolean found = false;
        for (int i = 0; i < playlists.size() && !found; i++) {
            if (playlists.get(i).getName().equalsIgnoreCase(name)) {
                playlist = playlists.get(i);
                found = true;
            }
        }
        return playlist;
    }
    
    /**
     * <b>name: </b> addPlaylist <br>
     * Adds a playlist to a Premium consumer. <br>
     * <b>pre: </b> The new playlist has a name that has not been previously registered. <br>
     * <b>post: </b> Adds the playlist.
     * @param name Name of the playlist to be added.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String addPlaylist(String name) {
        String msj = "Added.";
        Playlist playlist = new Playlist(name);
        if (searchPlaylist(name) == null) {
            playlists.add(playlist);
        } else {
            msj = "Repeated.";
        }
        return msj;
    }

    /**
     * <b>name: </b> updatePlaylist <br>
     * Apply changes for a playlist of this user. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Updates a playlist.
     * @param playlist Playlist to be updated.
     */
    public void updatePlaylist(Playlist playlist) {
        boolean found = false;
        for (int i = 0; i < playlists.size() && !found; i++) {
            if (playlist.getName().equalsIgnoreCase(playlists.get(i).getName())) {
                playlists.remove(i);
                playlists.add(playlist);
                found = true;
            }
        }
    }
}
