package model;
import java.time.LocalDate;

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
        Playlist playlist = super.searchPlaylist(name);
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
     * <b>pre: </b> The creation of the playlist doesn't exceed the limit (20 playlists), and its name has not been previously registered. <br>
     * <b>post: </b> Adds the playlist if it's possible.
     * @param name Name of the playlist to be added.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String addPlaylist(String name) {
        String msj = super.addPlaylist(name);
        Playlist playlist = new Playlist(name);
        boolean isAvailable = false;
        if (searchPlaylist(name) == null) {
            for (int i = 0; i < LIMIT_PLAYLISTS && !isAvailable; i++) {
                if (playlists[i] == null) {
                    playlists[i] = playlist;
                    isAvailable = true;
                    msj = "Added.";
                } else {
                    msj = "Limit reached.";
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
        super.updatePlaylist(playlist);
        boolean found = false;
        for (int i = 0; i < LIMIT_PLAYLISTS && !found; i++) {
            if (playlist.getName().equalsIgnoreCase(playlists[i].getName())) {
                playlists[i] = playlist;
                found = true;
            }
        }
    }

    /**
     * <b>name: </b> buySong <br>
     * Purchase a song for a user. <br>
     * <b>pre: </b> The song must exist. Purchase must not exceed 100 songs. <br>
     * <b>post: </b> The purchase is made.
     * @param song Song to buy.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String buySong(Song song) {
        String msj = super.buySong(song);
        boolean done = false;
        boolean found = false;
        for (int i = 0; i < songs.length && !found; i++) {
            if (songs[i] != null && songs[i].getSong() == song) {
                msj = "Error. Song already bought.";
                found = true;
            }
        }
        if (!found) {
            if (songs[songs.length - 1] == null) {
                for (int i = 0; i < songs.length && !done; i++) {
                    if (songs[i] == null) {
                        songs[i] = new Shop(song);
                        done = true;
                    }
                }
            } else {
                msj = "Error. Limit reached for this user.";
            }
        }
        return msj;
    }

    /**
     * <b>name: </b> getSongs <br>
     * Returns the list of songs purchased by the user. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the list.
     * @return <b>songs</b> List of songs.
     */
    public Shop[] getSongs() {
        return songs;
    }
}
