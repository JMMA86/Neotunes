package model;
import java.time.LocalDate;

/**
 * <b>Class: </b> Shop <br>
 */
public class Shop implements Comparable<Shop> {
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

    /**
     * <b>name: </b> getSong <br>
     * Returns the purchased song. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the song.
     * @return <b>song</b> Purchased song.
     */
    public Song getSong() {
        return song;
    }

    @Override
    public int compareTo(Shop o) {
        return song.getAmountSales().compareTo(o.getSong().getAmountSales());
    }
}
