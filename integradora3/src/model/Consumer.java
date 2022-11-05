package model;
import java.time.LocalDate;
import java.util.ArrayList;

public class Consumer extends User {
    //constants
    public int LIMIT_SONGS = 100;
    public int LIMIT_PLAYLISTS = 20;

    //attributes
    private int podcastTimeRep;
    private Category podcastMostListenedCategory;
    private String podcastMostListenedCreator;
    private int songTimeRep;
    private Genre songMostListenedGenre;
    private String songMostListenedArtist;

    //relations
    private ArrayList<String> listenedSongs;
    private ArrayList<Integer> numListSongs;
    private ArrayList<Shop> songs;
    private ArrayList<Playlist> playlists;

    //methods
    public Consumer(String nickname, String id, LocalDate date) {
        super(nickname, id, date);
        this.songs = new ArrayList<Shop>();
        this.playlists = new ArrayList<Playlist>();
    }
}
