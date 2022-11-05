package model;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContentProducer extends Producer {
    //constants

    //attributes

    //relations
    private ArrayList<Podcast> podcasts;
    
    //methods
    public ContentProducer(String nickname, String id, LocalDate date, String name, String urlImage) {
        super(nickname, id, date, name, urlImage);
        this.podcasts = new ArrayList<Podcast>();
    }
    
    public void addSong(Audio audio) {
        Podcast podcast = (Podcast) audio;
        podcasts.add(podcast);
    }

    public Podcast searchPodcast(String name) {
        Podcast podcast = null;
        boolean found = false;
        for (int i = 0; i < podcasts.size() && !found; i++) {
            if (podcasts.get(i).getName().equalsIgnoreCase(name)) {
                podcast = podcasts.get(i);
                found = true;
            }
        }
        return podcast;
    }
}
