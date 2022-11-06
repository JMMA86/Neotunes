package model;
import java.util.ArrayList;

/**
 * <b>Class: </b> Playlist <br>
 */
public class Playlist {
    //constants

    //attributes
    private String name;
    private ArrayList<Audio> audios;

    //relations
    
    //methods
    /**
     * <b>name: </b> Playlist <br>
     * This is the constructor of the class "Playlist". <br>
     * @param name Name of the playlist.
     */
    public Playlist(String name) {
        this.name = name;
        this.audios = new ArrayList<Audio>();
    }

    /**
     * <b>name: </b> searchAudio <br>
     * Search for a song from a given name and artist. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the song if it is found.
     * @param name Name of the song to be searched.
     * @param producer Name of the artist of the song.
     * @return <b>audio</b> Song if it's found.
     */
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

    /**
     * <b>name: </b> searchAudio <br>
     * Search for a podcast from a given name and content creator. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the podcast if it is found.
     * @param name Name of the podcast to be searched.
     * @param producer Name of the content creator of the podcast.
     * @return <b>audio</b> Podcast if it's found.
     */
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

    /**
     * <b>name: </b> addAudio <br>
     * Adds an audio to the playlist. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Adds the audio to the playlist.
     * @param audio Audio to be added.
     */
    public void addAudio(Audio audio) {
        audios.add(audio);
    }

    /**
     * <b>name: </b> deteleAudio <br>
     * Deletes a song of the playlist. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Deletes the song.
     * @param audio Song to be deleted.
     * @param producer Artist of the song.
     */
    public void deleteAudio(Audio audio, Artist producer) {
        boolean found = false;
        for (int i = 0; i < audios.size() && !found; i++) {
            if (audios.get(i).getName().equalsIgnoreCase(audio.getName()) && producer.searchSong(audio.getName()) != null) {
                audios.remove(i);
                found = true;
            }
        }
    }

    /**
     * <b>name: </b> deteleAudio <br>
     * Deletes a podcast of the playlist. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Deletes the podcast.
     * @param audio Podcast to be deleted.
     * @param producer Content producer of the podcast.
     */
    public void deleteAudio(Audio audio, ContentProducer producer) {
        boolean found = false;
        for (int i = 0; i < audios.size() && !found; i++) {
            if (audios.get(i).getName().equalsIgnoreCase(audio.getName()) && producer.searchPodcast(audio.getName()) != null) {
                audios.remove(i);
                found = true;
            }
        }
    }

    /**
     * <b>name: </b> getName <br>
     * Returns the name of the playlist. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the name.
     * @return <b>name</b> Name of the playlist.
     */
    public String getName() {
        return name;
    }
}
