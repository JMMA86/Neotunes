package model;
import java.time.LocalDate;

public class Shop {
    //constants

    //attributes
    private LocalDate purchaseDate;
    private Song song;

    //relations
    
    //methods
    public Shop(Song song) {
        this.purchaseDate = LocalDate.now();
        this.song = song;
    }
}
