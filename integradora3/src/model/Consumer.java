package model;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <b>Class: </b> Consumer <br>
 */
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

    //methods
    /**
     * <b>name: </b> Consumer <br>
     * This is the constructor of the class "Consumer". <br>
     * @param nickname Nickname of the consumer.
     * @param id Id of the consumer.
     * @param date Registration date.
     */
    public Consumer(String nickname, String id, LocalDate date) {
        super(nickname, id, date);
    }
}
