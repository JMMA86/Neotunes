package model;

/**
 * <b>Class: </b> Audio <br>
 */
public class Audio {
    //constants

    //attributes
    private String name;
    private String urlImage;
    private int timeRep;
    /**
     * Views of an audio
     */
    protected Integer views;

    //relations
    
    //methods
    /**
     * <b>name: </b> Audio <br>
     * This is the constructor of the class "Audio". <br>
     * @param name Name of the audio.
     * @param urlImage URL icon.
     * @param timeRep Length in seconds of the audio.
     */
    public Audio(String name, String urlImage, int timeRep) {
        this.name = name;
        this.urlImage = urlImage;
        this.timeRep = timeRep;
        this.views = 0;
    }

    /**
     * <b>name: </b> getName <br>
     * Returns the name of the audio. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the name.
     * @return <b>name</b> Name of the audio.
     */
    public String getName() {
        return name;
    }
}
