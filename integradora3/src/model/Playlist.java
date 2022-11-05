package model;
import java.util.ArrayList;

public class Playlist {
    //constants

    //attributes
    private String name;
    private ArrayList<Audio> audios;

    //relations
    
    //methods
    public Playlist(String name) {
        this.name = name;
        this.audios = new ArrayList<Audio>();
    }
}
