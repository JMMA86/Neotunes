package model;

/**
 * <b>Class: </b> Podcast <br>
 */
public class Podcast extends Audio implements Playable{
    //constants

    //attributes
    private String description;

    //relations
    private Category category;
    
    //methods
    /**
     * <b>name: </b> Level <br>
     * This is the constructor of the class "Podcast". <br>
     * @param name Name of the podcast.
     * @param urlImage URL icon.
     * @param timeRep Length in seconds of the podcast.
     * @param description Description of the podcast.
     * @param category Category of the podcast (1: Politics, 2: Entertainment, 3: Video Games, 4: Fashion).
     */
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
