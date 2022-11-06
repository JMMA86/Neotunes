package model;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <b>Class: </b> ContentProducer <br>
 */
public class ContentProducer extends Producer {
    //constants

    //attributes

    //relations
    private ArrayList<Podcast> podcasts;
    
    //methods
    /**
     * <b>name: </b> ContentProducer <br>
     * This is the constructor of the class "ContentProducer". <br>
     * @param nickname Nickname of the producer.
     * @param id Id of the producer.
     * @param date Registration date.
     * @param name Name of the producer.
     * @param urlImage User's icon.
     */
    public ContentProducer(String nickname, String id, LocalDate date, String name, String urlImage) {
        super(nickname, id, date, name, urlImage);
        this.podcasts = new ArrayList<Podcast>();
    }
    
    /**
     * <b>name: </b> addPodcast <br>
     * Adds a podcast to a creator content. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Adds the podcast.
     * @param audio Podcast to be added.
     */
    public void addPodcast(Audio audio) {
        Podcast podcast = (Podcast) audio;
        podcasts.add(podcast);
    }

    /**
     * <b>name: </b> searchPodcast <br>
     * Search for a podcast from a given name. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the podcast if it is found.
     * @param name Name of the podcast to be searched.
     * @return <b>podcast</b>. Contains the podcast if it is found.
     */
    public Podcast searchPodcast(String name) {
        Podcast podcast = null;
        boolean found = false;
        for (int i = 0; i < podcasts.size() && !found; i++) {
            if (podcasts.get(i).getName().equalsIgnoreCase(name)) {
                podcast = podcasts.get(i);
                found = true;
            }
        }
        return podcast;
    }
}
