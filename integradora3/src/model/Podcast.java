package model;

public class Podcast extends Audio implements Playable{
    //constants

    //attributes
    private String description;

    //relations
    private Category category;
    
    //methods
    public Podcast(String name, String urlImage, int timeRep, String description, int category) {
        super(name, urlImage, timeRep);
        this.description = description;
        switch (category) {
            case 1:
                this.category = Category.POLITIC;
                break;
            case 2:
                this.category = Category.ENTERTAINMENT;
                break;
            case 3:
                this.category = Category.GAME;
                break;
            case 4:
                this.category = Category.FASHION;
                break;
        }  
    }
    
}
