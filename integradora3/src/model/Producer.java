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
    private int views;
    private int timeRep;

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
     */
    public Producer(String nickname, String id, LocalDate date, String name, String urlImage) {
        super(nickname, id, date);
        this.name = name;
        this.urlImage = urlImage;
    }
    
}
