package model;
import java.time.LocalDate;
import java.util.ArrayList;

public class Premium extends Consumer {
    //constants

    //attributes

    //relations
    private ArrayList<Shop> songs;
    private ArrayList<Playlist> playlists;
    
    //methods
    public Premium(String nickname, String id, LocalDate date) {
        super(nickname, id, date);
        this.songs = new ArrayList<Shop>();
        this.playlists = new ArrayList<Playlist>();
    }

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
