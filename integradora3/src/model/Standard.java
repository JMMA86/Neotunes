package model;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <b>Class: </b> Standard <br>
 */
public class Standard extends Consumer {    
    //constants

    //attributes

    //relations
    private Shop[] songs;
    private Playlist[] playlists;
    
    //methods
    /**
     * <b>name: </b> Standard <br>
     * This is the constructor of the class "Standard". <br>
     * @param nickname Nickname of the user.
     * @param id Id of the user.
     * @param date Registration date.
     */
    public Standard(String nickname, String id, LocalDate date) {
        super(nickname, id, date);
        this.songs = new Shop[LIMIT_SONGS];
        this.playlists = new Playlist[LIMIT_PLAYLISTS];
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
        for (int i = 0; i < LIMIT_PLAYLISTS && !found; i++) {
            if (playlists[i] != null && playlists[i].getName().equalsIgnoreCase(name)) {
                playlist = playlists[i];
                found = true;
            }
        }
        return playlist;
    }

    /**
     * <b>name: </b> addPlaylist <br>
     * Adds a playlist to a Standard consumer. <br>
     * <b>pre: </b> The creation of the playlst doesn't exceed the limit (20 playlists), and its name has not been previously registered. <br>
     * <b>post: </b> Adds the playlist if it's possible.
     * @param name Name of the playlist to be added.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String addPlaylist(String name) {
        String msj = "Limit reached.";
        Playlist playlist = new Playlist(name);
        boolean isAvailable = false;
        if (searchPlaylist(name) == null) {
            for (int i = 0; i < LIMIT_PLAYLISTS && !isAvailable; i++) {
                if (playlists[i] == null) {
                    playlists[i] = playlist;
                    isAvailable = true;
                    msj = "Added.";
                }
            }
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
        for (int i = 0; i < LIMIT_PLAYLISTS && !found; i++) {
            if (playlist.getName().equalsIgnoreCase(playlists[i].getName())) {
                playlists[i] = playlist;
                found = true;
            }
        }
    }
}
