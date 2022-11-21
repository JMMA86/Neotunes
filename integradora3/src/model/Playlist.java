package model;
import java.util.ArrayList;
import java.util.Random;

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

    /**
     * <b>name: </b> sharePlaylist <br>
     * Generates an id for a playlist. <br>
     * <b>pre: </b> The playlist must contain at least one audio. <br>
     * <b>post: </b> Returns the id.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String sharePlaylist() {
        String msj = null;
        boolean songs = false;
        boolean podcasts = false;
        String id = "";
        int counter;
        //Matrix creation
        int[][] matrix = new int[6][6];
        Random rand = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = rand.nextInt(9);
            }
        }
        //Playlist content identification
        for (int i = 0; i < audios.size(); i++) {
            if (audios.get(i) instanceof Song) {
                songs = true;
            } else if (audios.get(i) instanceof Podcast) {
                podcasts = true;
            }
        }
        //Id creation
        if (songs && !podcasts) {
            //Matrix "N"
            counter = 5;
            while (counter != 0) {
                id += matrix[counter][0];
                counter--;
            }
            while (counter != 5) {
                id += matrix[counter][counter];
                counter++;
            }
            while (counter != -1) {
                id += matrix[counter][5];
                counter--;
            }
        } else if (!songs && podcasts) {
            //Matrix "T"
            counter = 0;
            while (counter != 2) {
                id += matrix[0][counter];
                counter++;
            }
            counter = 0;
            while (counter != 6) {
                id += matrix[counter][2];
                counter++;
            }
            counter--;
            while (counter != 0) {
                id += matrix[counter][3];
                counter--;
            }
            counter = 3;
            while (counter != 6) {
                id += matrix[0][counter];
                counter++;
            }
        } else if (songs && podcasts) {
            //Matrix "Interleaved"
            counter = 2;
            for (int i = matrix.length - 1; i >= 0; i--) {
                if (i > 1) {
                    if (counter == 1) {
                        id += matrix[i][5];
                        id += matrix[i][3];
                        id += matrix[i][1];
                        counter = 2;
                    } else {
                        id += matrix[i][4];
                        id += matrix[i][2];
                        id += matrix[i][0];
                        counter = 1;
                    }
                } else {
                    if (counter == 1) {
                        id += matrix[i][5];
                        id += matrix[i][3];
                        counter = 2;
                    } else {
                        id += matrix[i][4];
                        id += matrix[i][2];
                        counter = 1;
                    }
                }
            }
        } else {
            msj = "Error. No audios found.";
        }
        if (!id.equals("")) {
            msj = "\nId generated: " + id + "\n\nMatrix: " + "\n" + printMatrix(matrix);
        }
        return msj;
    }

    /**
     * <b>name: </b> printMatrix <br>
     * Convert a matrix into a message that can be displayed on the console. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the msj.
     * @param matrix Matrix to be converted.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String printMatrix(int[][] matrix) {
        String msj = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                msj += matrix[i][j] + " ";
            }
            msj += "\n";
        }
        return msj;
    }
}
