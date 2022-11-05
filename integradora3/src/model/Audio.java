package model;

public class Audio {
    //constants

    //attributes
    private String name;
    private String urlImage;
    private int timeRep;
    private int views;

    //relations
    
    //methods
    public Audio(String name, String urlImage, int timeRep) {
        this.name = name;
        this.urlImage = urlImage;
        this.timeRep = timeRep;
    }

    public String getName() {
        return name;
    }
}
