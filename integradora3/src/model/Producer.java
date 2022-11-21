package model;
import java.time.LocalDate;

/**
 * <b>Class: </b> Producer <br>
 */
public class Producer extends User {
    //constants

    //attributes
    private String name;
    private String urlImage;
    private int timeRep;
    /**
     * Views of a producer
     */
    protected Integer views;

    //relations

    //methods
    /**
     * <b>name: </b> Producer <br>
     * This is the constructor of the class "Producer". <br>
     * @param nickname Nickname of the producer.
     * @param id Id of the producer.
     * @param date Registration date.
     * @param name Name of the producer.
     * @param urlImage User's icon.
     * @param views Initial views (0).
     */
    public Producer(String nickname, String id, LocalDate date, String name, String urlImage, int views) {
        super(nickname, id, date);
        this.name = name;
        this.urlImage = urlImage;
        this.views = views;
    }

    /**
     * <b>name: </b> addAudio <br>
     * Adds an audio to a producer. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Adds the audio.
     * @param audio Audio to be added.
     */
    public void addAudio(Audio audio) {
    }

    /**
     * <b>name: </b> searchAudio <br>
     * Search for an audio from a given name. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the audio if it is found.
     * @param name Name of the audio to be searched.
     * @return <b>audio</b>. Contains the audio if it is found.
     */
    public Audio searchAudio(String name) {
        Audio audio = null;
        return audio;
    }

    /**
     * <b>name: </b> getTotalRep <br>
     * Returns the views of a producer. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns views.
     * @return <b>views</b>. Views of the producer.
     */
    public int getTotalRep() {
        views = 0;
        return views;
    }
    
}
