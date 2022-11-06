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

    public Audio searchAudio(String name, Artist producer) {
        boolean found = false;
        Audio audio = null;
        for (int i = 0; i < audios.size() && !found; i++) {
            if (audios.get(i).getName().equalsIgnoreCase(name) && producer.searchSong(name) != null) {
                audio = audios.get(i);
                found = true;
            }
        }
        return audio;
    }

    public Audio searchAudio(String name, ContentProducer producer) {
        boolean found = false;
        Audio audio = null;
        for (int i = 0; i < audios.size() && !found; i++) {
            if (audios.get(i).getName().equalsIgnoreCase(name) && producer.searchPodcast(name) != null) {
                audio = audios.get(i);
                found = true;
            }
        }
        return audio;
    }

    public void addAudio(Audio audio) {
        audios.add(audio);
    }

    public void deleteAudio(Audio audio, Artist producer) {
        boolean found = false;
        for (int i = 0; i < audios.size() && !found; i++) {
            if (audios.get(i).getName().equalsIgnoreCase(audio.getName()) && producer.searchSong(audio.getName()) != null) {
                audios.remove(i);
                found = true;
            }
        }
    }

    public void deleteAudio(Audio audio, ContentProducer producer) {
        boolean found = false;
        for (int i = 0; i < audios.size() && !found; i++) {
            if (audios.get(i).getName().equalsIgnoreCase(audio.getName()) && producer.searchPodcast(audio.getName()) != null) {
                audios.remove(i);
                found = true;
            }
        }
    }

    public String getName() {
        return name;
    }
}
