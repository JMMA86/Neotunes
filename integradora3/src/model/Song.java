package model;

public class Song extends Audio implements Playable, Salable {
    //constants

    //attributes
    private String album;
    private double price;
    private int amountSales;

    //relations
    private Genre genre;

    //methods
    public Song(String name, String urlImage, int timeRep, String album, double price, int genre) {
        super(name, urlImage, timeRep);
        this.album = album;
        this.price = price;
        switch (genre) {
            case 1:
                this.genre = Genre.ROCK;
                break;
            case 2:
                this.genre = Genre.POP;
                break;
            case 3:
                this.genre = Genre.TRAP;
                break;
            case 4:
                this.genre = Genre.HOUSE;
                break;
        }    
    }
}
