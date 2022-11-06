package model;
import java.time.LocalDate;
import java.util.ArrayList;

public class Standard extends Consumer {    
    //constants

    //attributes

    //relations
    private Shop[] songs;
    private Playlist[] playlists;
    
    //methods
    public Standard(String nickname, String id, LocalDate date) {
        super(nickname, id, date);
        this.songs = new Shop[LIMIT_SONGS];
        this.playlists = new Playlist[LIMIT_PLAYLISTS];
    }

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
