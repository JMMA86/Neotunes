package model;
import java.time.LocalDate;

public class Producer extends User {
    //constants

    //attributes
    private String name;
    private String urlImage;
    private int views;
    private int timeRep;

    //relations
    
    //methods
    public Producer(String nickname, String id, LocalDate date, String name, String urlImage) {
        super(nickname, id, date);
        this.name = name;
        this.urlImage = urlImage;
    }
    
}
