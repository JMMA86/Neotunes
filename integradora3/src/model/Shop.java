package model;
import java.time.LocalDate;

/**
 * <b>Class: </b> Shop <br>
 */
public class Shop {
    //constants

    //attributes
    private LocalDate purchaseDate;
    private Song song;

    //relations
    
    //methods
    /**
     * <b>name: </b> Shop <br>
     * This is the constructor of the class "Shop". <br>
     * @param song Song that is going to be purchased.
     */
    public Shop(Song song) {
        this.purchaseDate = LocalDate.now();
        this.song = song;
    }
}
