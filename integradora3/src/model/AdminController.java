package model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

/**
 * <b>Class: </b> AdminController <br>
 */
public class AdminController {
    //constants

    //attributes

    //relations
    private ArrayList<User> users;
    
    //methods
    /**
     * <b>name: </b> AdminController <br>
     * This is the constructor of the class "AdminController".
     */
    public AdminController() {
        this.users = new ArrayList<User>();
    }

    /**
     * <b>name: </b> searchUser <br>
     * Checks if an user is registered under a given nickname. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the object of the user if found.
     * @param nickname Nickname of the user to look for.
     * @return <b>user</b> User if it's found.
     */
    public User searchUser(String nickname) {
        boolean found = false;
        User user = null;
        for (int i = 0; i < users.size() && !found; i++) {
            if (users.get(i).getNickname().equalsIgnoreCase(nickname)) {
                user = users.get(i);
                found = true;
            }
        }
        return user;
    }

    /**
     * <b>name: </b> addUser <br>
     * Adds a consumer user to the system. <br>
     * <b>pre: </b> The user's nickname is not registered currently. <br>
     * <b>post: </b> The system adds the new user.
     * @param type Defines if the consumer is type Standard or Premium.
     * @param nickname Nickname of the user to register.
     * @param id Id of the user to register.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String addUser(int type, String nickname, String id) {
        String msj = "Added succesfully.";
        LocalDate date = LocalDate.now();
        User user = null;
        if (searchUser(nickname) == null) {
            switch (type) {
                case 1:
                    user = new Standard(nickname, id, date);
                    users.add(user);
                    break;
                case 2:
                    user = new Premium(nickname, id, date);
                    users.add(user);
                    break;
                default:
                    msj = "Error. Invalid type.";
                    break;
            }
        } else {
            msj = "Error. There's registered an user with the same nickname.";
        }
        return msj;
    }

    /**
     * <b>name: </b> addUser <br>
     * Adds a producer user to the system. <br>
     * <b>pre: </b> The user's nickname is not registered currently. <br>
     * <b>post: </b> The system adds the new user.
     * @param type Defines if the producer is an Artist or a Content Creator.
     * @param name Name of the user to register.
     * @param nickname Nickname of the user to register.
     * @param urlImage URL of the user's icon.
     * @param id Id of the user to register.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String addUser(int type, String name, String nickname, String urlImage, String id) {
        String msj = "Added succesfully.";
        LocalDate date = LocalDate.now();
        User user = null;
        if (searchUser(nickname) == null) {
            switch (type) {
                case 1:
                    user = new Artist(nickname, id, date, name, urlImage, 0);
                    users.add(user);
                    break;
                case 2:
                    user = new ContentProducer(nickname, id, date, name, urlImage, 0);
                    users.add(user);
                    break;
                default:
                    msj = "Error. Invalid type.";
                    break;
            }
        } else {
            msj = "Error. There's registered an user with the same nickname.";
        }
        return msj;
    }

    /**
     * <b>name: </b> addAudio <br>
     * Adds a song to the system. <br>
     * <b>pre: </b> The artist who is adding the song doesn't have other with the same name. <br>
     * <b>post: </b> The system adds the new song.
     * @param name Name of the song.
     * @param urlImage Url icon of the song.
     * @param album Album of the song.
     * @param price Price of the song.
     * @param genre Genre of the song (1: Rock, 2: Pop, 3: Trap, 4: House).
     * @param artist Artist who is adding the song.
     * @param timeRep Duration in seconds of the song.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String addAudio(String name, String urlImage, String album, double price, int genre, String artist, int timeRep) {
        String msj = "Added correctly.";
        boolean found = false;
        if (searchUser(artist) == null || searchUser(artist) instanceof ContentProducer) {
            msj = "Error. Artist not found.";
        } else {
            Audio song = new Song(name, urlImage, timeRep, album, price, genre);
            for (int i = 0; i < users.size() && !found; i++) {
                if (users.get(i).getNickname().equalsIgnoreCase(artist)) {
                    Artist user = (Artist) users.get(i);
                    if (user.searchSong(name) == null) {
                        user.addSong(song);
                        users.remove(users.get(i));
                        users.add(user);
                        found = true;
                    } else {
                        msj = "Error. This artist has a song with the same name.";
                    }
                }
            }
        }
        return msj;
    }

    /**
     * <b>name: </b> addAudio <br>
     * Adds a podcast to the system. <br>
     * <b>pre: </b> The creator content who is adding the podcast doesn't have other with the same name. <br>
     * <b>post: </b> The system adds the new podcast.
     * @param name Name of the podcast.
     * @param urlImage Url icon of the podcast.
     * @param description Description of what is the podcast about.
     * @param category Category of the podcast (1: Politics, 2: Entertainment, 3: Video Games, 4: Fashion).
     * @param contentProducer Content producer who is adding the podcast.
     * @param timeRep Duration in seconds of the podcast.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String addAudio(String name, String urlImage, String description, int category, String contentProducer, int timeRep) {
        String msj = "Added correctly.";
        boolean found = false;
        if (searchUser(contentProducer) == null || searchUser(contentProducer) instanceof Artist) {
            msj = "Error. Content producer not found.";
        } else {
            Audio podcast = new Podcast(name, urlImage, timeRep, description, category);
            for (int i = 0; i < users.size() && !found; i++) {
                if (users.get(i).getNickname().equalsIgnoreCase(contentProducer)) {
                    ContentProducer user = (ContentProducer) users.get(i);
                    if (user.searchPodcast(name) == null) {
                        user.addPodcast(podcast);
                        users.remove(users.get(i));
                        users.add(user);
                        found = true;
                    } else {
                        msj = "Error. This content producer has a podcast with the same name.";
                    }
                }
            }
        }
        return msj;
    }

    /**
     * <b>name: </b> addPlaylist <br>
     * Adds a playlist to the system. <br>
     * <b>pre: </b> The user who is adding the playlist is a consumer and doesn't have other playlist with the same name. <br>
     * <b>post: </b> The system adds the new playlist to the user.
     * @param name Name of the new playlist.
     * @param user Name of the consumer who is adding the playlist.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String addPlaylist(String name, String user) {
        String msj = "Added correctly.";
        boolean found = false;
        for (int i = 0; i < users.size() && !found; i++) {
            if (users.get(i).getNickname().equalsIgnoreCase(user)) {
                Consumer objConsumer = (Consumer) users.get(i);
                found = true;
                if (users.get(i) instanceof Producer) {
                    msj = "Error. This user is a producer.";
                } else {
                    String operationResult = objConsumer.addPlaylist(name);
                    if (operationResult.equalsIgnoreCase("Repeated.")) {
                        msj = "Error. This user has a playlist with the same name.";
                    } else if (operationResult.equalsIgnoreCase("Limit reached.")){
                        msj = "Error. Limit reached for this user.";
                    } else {
                        users.remove(i);
                        users.add(objConsumer);
                    }
                }
            }
        }
        if (!found) {
            msj = "Error. User does not exist.";
        }
        return msj;
    }
    
    /**
     * <b>name: </b> editPlaylist <br>
     * Modifies a playlist of a consumer. <br>
     * <b>pre: </b> The user exists and is a consumer; this user's playlist exists; if the consumer is standard, he will not be able to add more than 20 audios; the audio exists. <br>
     * <b>post: </b> The system amodifies a playlist depending of the users request.
     * @param playlistName Playlist to be modified.
     * @param audioName Audio to be added or deleted.
     * @param consumerName User who is modifying its playlist.
     * @param producerName Producer of the audio.
     * @param option Request of the user.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String editPlaylist(String playlistName, String audioName, String consumerName, String producerName, int option) {
        /* Option:
        "1. Add song" +
        "2. Delete song" +
        "3. Add podcast" +
        "4. Delete podcast" +

        1. Verify that the consumer and producer exist
        2. Verify that the song exists
        3. Execute option
         */
        int changes = 0;
        if (option == 1 || option == 2) {
            changes = 1;
        } else if (option == 3 || option == 4) {
            changes = 2;
        }
        Consumer consumer = null;
        Producer producer = null;
        Song song = null;
        Podcast podcast = null;
        //Playlist playlist = null;
        String msj = "Changes realized succesfully.";
        if (searchUser(consumerName) != null && searchUser(consumerName) instanceof Consumer && searchUser(producerName) != null && searchUser(producerName) instanceof Producer) {
            consumer = (Consumer) searchUser(consumerName);
            producer = (Producer) searchUser(producerName);
            switch (changes) {
                case 1:
                    //Start song search
                    if (producer instanceof Artist) {
                        Artist producerObj = (Artist) producer;
                        if (producerObj.searchSong(audioName) != null) {
                            //song = found song
                            song = producerObj.searchSong(audioName);
                            //Start user playlist search
                            if (consumer.searchPlaylist(playlistName) != null) {
                                Playlist editablePlaylist = consumer.searchPlaylist(playlistName);
                                //Edit playlist
                                if (option == 1) {
                                    if (editablePlaylist.searchAudio(audioName, producerObj) == null) {
                                        editablePlaylist.addAudio(song);
                                        consumer.updatePlaylist(editablePlaylist);
                                    } else {
                                        msj = "Error. This song exists in that playlist.";
                                    }
                                } else if (option == 2) {
                                    if (editablePlaylist.searchAudio(audioName, producerObj) != null) {
                                        editablePlaylist.deleteAudio(song, producerObj);
                                        consumer.updatePlaylist(editablePlaylist);
                                    } else {
                                        msj = "Error. Song not found in the playlist.";
                                    }
                                }
                            } else {
                                msj = "Error. Playlist not found.";
                            }
                        } else {
                            msj = "Error. Song not found.";
                        }
                    } else {
                        msj = "Error. This producer is not an artist.";
                    }
                    break;
                case 2:
                    //Start podcast search
                    if (producer instanceof ContentProducer) {
                        ContentProducer producerObj = (ContentProducer) producer;
                        if (producerObj.searchPodcast(audioName) != null) {
                            //podcast = found podcast
                            podcast = producerObj.searchPodcast(audioName);
                            //Start user playlist search
                            if (consumer.searchPlaylist(playlistName) != null) {
                                Playlist editablePlaylist = consumer.searchPlaylist(playlistName);
                                //Edit playlist
                                if (option == 3) {
                                    if (editablePlaylist.searchAudio(audioName, producerObj) == null) {
                                        editablePlaylist.addAudio(podcast);
                                        consumer.updatePlaylist(editablePlaylist);
                                    } else {
                                        msj = "Error. This podcast exists in that playlist.";
                                    }
                                } else if (option == 4) {
                                    if (editablePlaylist.searchAudio(audioName, producerObj) != null) {
                                        editablePlaylist.deleteAudio(podcast, producerObj);
                                        consumer.updatePlaylist(editablePlaylist);
                                    } else {
                                        msj = "Error. Podcast not found in the playlist.";
                                    }
                                }
                            } else {
                                msj = "Error. Playlist not found.";
                            }
                        } else {
                            msj = "Error. Podcast not found.";
                        }
                    } else {
                        msj = "Error. This producer is not a content creator.";
                    }
                    break;
                default:
                    msj = "Error. Invalid option.";
                    break;
            }
        } else {
            msj = "Error. Consumer or producer does not exist.";
        }
        return msj;
    }

    /**
     * <b>name: </b> sharePlaylist <br>
     * Creates an id for a playlist to share it. <br>
     * <b>pre: </b> The playlist exists and has content. <br>
     * <b>post: </b> The system generated an id for the playlist.
     * @param name User of the playlist.
     * @param playlist Name of the playlist.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String sharePlaylist(String name, String playlist) {
        String msj = null;
        if (searchUser(name) != null) {
            if (searchUser(name) instanceof Consumer) {
                Consumer userObj = (Consumer) searchUser(name);
                if (userObj instanceof Standard) {
                    Standard user = (Standard) userObj;
                    if (user.searchPlaylist(playlist) != null) {
                        msj = user.searchPlaylist(playlist).sharePlaylist();
                    } else {
                        msj = "Error. Playlist not found.";
                    }
                } else {
                    Premium user = (Premium) userObj;
                    if (user.searchPlaylist(playlist) != null) {
                        msj = user.searchPlaylist(playlist).sharePlaylist();
                    } else {
                        msj = "Error. Playlist not found.";
                    }
                }
            } else {
                msj = "Error. This user is not a consumer.";
            }
        } else {
            msj = "Error. This user does not exist.";
        }
        return msj;
    }

    /**
     * <b>name: </b> simulateAudio <br>
     * Simulates the playback of an audio. <br>
     * <b>pre: </b> Audio, producer and consumer exist. <br>
     * <b>post: </b> The system simulates its reproduction and updates its statistics.
     * @param nickname Consumer.
     * @param audio Name of the audio.
     * @param type 1 or 2 to play a song or podcast respectively.
     * @param producer Nickname of producer.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String simulateAudio(String nickname, String audio, int type, String producer) {
        String msj = "Playing...";
        if (searchUser(nickname) != null && searchUser(nickname) instanceof Consumer) {
            Consumer objConsumer = (Consumer) searchUser(nickname);
            if (type == 1) {
                if (searchUser(producer) != null && searchUser(producer) instanceof Artist) {
                    Artist objProducer = (Artist) searchUser(producer);
                    if (objProducer.searchSong(audio) != null) {
                        Song objAudio = (Song) objProducer.searchSong(audio);
                        objAudio.playAudio();
                        objConsumer.updateStats(objAudio);
                        objProducer.updateSong(objAudio);
                        int counter = 0;
                        for (int i = 0; i < users.size() && counter != 2; i++) {
                            if (users.get(i).getNickname().equalsIgnoreCase(nickname) && users.get(i) instanceof Consumer) {
                                users.remove(i);
                                users.add(objConsumer);
                                counter++;
                            }
                            if (users.get(i).getNickname().equalsIgnoreCase(producer) && users.get(i) instanceof Artist) {
                                users.remove(i);
                                users.add(objProducer);
                                counter++;
                            }
                        }
                    } else {
                        msj = "Error. Audio not found.";
                    }
                } else {
                    msj = "Error. The producer does not exist or is not valid.";
                }
            } else if(type == 2) {
                if (searchUser(producer) != null && searchUser(producer) instanceof ContentProducer) {
                    ContentProducer objProducer = (ContentProducer) searchUser(producer);
                    if (objProducer.searchPodcast(audio) != null) {
                        Podcast objAudio = (Podcast) objProducer.searchPodcast(audio);
                        objAudio.playAudio();
                        objConsumer.updateStats(objAudio);
                        objProducer.updatePodcast(objAudio);
                        int counter = 0;
                        for (int i = 0; i < users.size() && counter != 2; i++) {
                            if (users.get(i).getNickname().equalsIgnoreCase(nickname) && users.get(i) instanceof Consumer) {
                                users.remove(i);
                                users.add(objConsumer);
                                counter++;
                            }
                            if (users.get(i).getNickname().equalsIgnoreCase(producer) && users.get(i) instanceof ContentProducer) {
                                users.remove(i);
                                users.add(objProducer);
                                counter++;
                            }
                        }
                    } else {
                        msj = "Error. Audio not found.";
                    }
                } else {
                    msj = "Error. The producer does not exist or is not valid.";
                }
            } else {
                msj = "Error. Invalid type of audio.";
            }
        } else {
            msj = "Error. User not found.";
        }
        return msj;
    }

    /**
     * <b>name: </b> buySong <br>
     * Buy a song for a certain consumer. <br>
     * <b>pre: </b> Song, artist and consumer exist. <br>
     * <b>post: </b> The system generates the purchase of the song and updates its statistics.
     * @param consumer Nickname of consumer.
     * @param song Name of the song.
     * @param artist Nickname of the artist.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String buySong(String consumer, String song, String artist) {
        String msj;
        if (searchUser(consumer) != null && searchUser(consumer) instanceof Consumer) {
            Consumer objConsumer = (Consumer) searchUser(consumer);
            if (searchUser(artist) != null && searchUser(artist) instanceof Artist) {
                Artist objArtist = (Artist) searchUser(artist);
                if (objArtist.searchSong(song) != null) {
                    Song objSong = objArtist.searchSong(song);
                    msj = objConsumer.buySong(objSong);
                    if (msj.equalsIgnoreCase("Succesfully purchased.")) {
                        objSong.sell();
                        objArtist.updateSong(objSong);
                        boolean found = false;
                        for (int i = 0; i < users.size() && !found; i++) {
                            if (users.get(i).getNickname().equalsIgnoreCase(artist) && users.get(i) instanceof Artist) {
                                users.remove(i);
                                users.add(objArtist);
                            }
                        }
                    }
                } else {
                    msj = "Error. Song not found.";
                }
            } else {
                msj = "Error. Artist not found.";
            }
        } else {
            msj = "Error. User not found.";
        }
        return msj;
    }

    /**
     * <b>name: </b> reportAudioViews <br>
     * Generates a report of the reproductions by type of audio on the entire platform. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> The system generates the report.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String reportAudioViews() {
        String msj = null;
        int repSongs = 0;
        int repPodcasts = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Producer) {
                Producer objProducer = (Producer) users.get(i);
                if (users.get(i) instanceof Artist) {
                    repSongs += objProducer.getTotalRep();
                } else if(users.get(i) instanceof ContentProducer) {
                    repPodcasts += objProducer.getTotalRep();
                }
            }
            msj = "\nSongs reproductions: " + repSongs + "\nPodcast reproductions: " + repPodcasts;
        }
        return msj;
    }

    /**
     * <b>name: </b> reportBestGenre <br>
     * Generates a report of the most listened to song genre for a user and for the entire platform. <br>
     * <b>pre: </b> The user must exist. <br>
     * <b>post: </b> The system generates the report.
     * @param user User to analyze.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String reportBestGenre(String user) {
        String msj = null;
        int counter = 0;
        int[] genres = {0, 0, 0, 0};
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Consumer) {
                Consumer objConsumer = (Consumer) users.get(i);
                if (objConsumer.getSongMostListenedGenre() != null && objConsumer.getSongMostListenedGenre().equalsIgnoreCase("Rock")) {
                    genres[0] += objConsumer.getViewsGenre("Rock");
                } else if (objConsumer.getSongMostListenedGenre() != null && objConsumer.getSongMostListenedGenre().equalsIgnoreCase("Pop")) {
                    genres[1] += objConsumer.getViewsGenre("Pop");
                } else if (objConsumer.getSongMostListenedGenre() != null && objConsumer.getSongMostListenedGenre().equalsIgnoreCase("Trap")) {
                    genres[2] += objConsumer.getViewsGenre("Trap");
                } else if (objConsumer.getSongMostListenedGenre() != null && objConsumer.getSongMostListenedGenre().equalsIgnoreCase("House")) {
                    genres[3] += objConsumer.getViewsGenre("House");
                }
            }
        }
        for (int i = 0; i < genres.length; i++) {
            if (genres[i] > counter) {
                counter = genres[i];
                switch (i) {
                    case 0:
                        msj = "Most listened genre: Rock (" + genres[i] + " views).";
                        break;
                    case 1:
                        msj = "Most listened genre: Pop (" + genres[i] + " views).";
                        break;
                    case 2:
                        msj = "Most listened genre: Trap (" + genres[i] + " views).";
                        break;
                    case 3:
                        msj = "Most listened genre: House (" + genres[i] + " views).";
                        break;
                }
            } else if (counter == 0) {
                msj = "No one has heard any song yet.";
            }
        }
        if (searchUser(user) != null && searchUser(user) instanceof Consumer) {
            Consumer objConsumer = (Consumer) searchUser(user);
            if (objConsumer.getSongMostListenedGenre() != null) {
                msj += "\nUser's most listened genre: " + objConsumer.getSongMostListenedGenre() + " (" + objConsumer.getViewsGenre(objConsumer.getSongMostListenedGenre()) + " views).";
            } else {
                msj += "\nThis user has not listened to any song yet.";
            }
        } else {
            msj += "\nUser not found.";
        }
        return msj;
    }

    /**
     * <b>name: </b> reportBestCategory <br>
     * Generates a report of the most listened to podcast category for a user and for the entire platform. <br>
     * <b>pre: </b> The user must exist. <br>
     * <b>post: </b> The system generates the report.
     * @param user User to analyze.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String reportBestCategory(String user) {
        int counter = 0;
        String msj = null;
        int[] categories = {0, 0, 0, 0};
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Consumer) {
                Consumer objConsumer = (Consumer) users.get(i);
                if (objConsumer.getPodcastMostListenedCategory() != null && objConsumer.getPodcastMostListenedCategory().equalsIgnoreCase("Politic")) {
                    categories[0] += objConsumer.getViewsCategory("Politic");
                } else if (objConsumer.getPodcastMostListenedCategory() != null && objConsumer.getPodcastMostListenedCategory().equalsIgnoreCase("Entertainment")) {
                    categories[1] += objConsumer.getViewsCategory("Entertainment");
                } else if (objConsumer.getPodcastMostListenedCategory() != null && objConsumer.getPodcastMostListenedCategory().equalsIgnoreCase("Game")) {
                    categories[2] += objConsumer.getViewsCategory("Game");
                } else if (objConsumer.getPodcastMostListenedCategory() != null && objConsumer.getPodcastMostListenedCategory().equalsIgnoreCase("Fashion")) {
                    categories[3] += objConsumer.getViewsCategory("Fashion");
                }
            }
        }
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] > counter) {
                counter = categories[i];
                switch (i) {
                    case 0:
                        msj = "Most listened category: Politic (" + categories[i] + " views).";
                        break;
                    case 1:
                        msj = "Most listened category: Entertainment (" + categories[i] + " views).";
                        break;
                    case 2:
                        msj = "Most listened category: Game (" + categories[i] + " views).";
                        break;
                    case 3:
                        msj = "Most listened category: Fashion (" + categories[i] + " views).";
                        break;
                }
            } else if (counter == 0) {
                msj = "No one has heard any podcast yet.";
            }
        }
        if (searchUser(user) != null && searchUser(user) instanceof Consumer) {
            Consumer objConsumer = (Consumer) searchUser(user);
            if (objConsumer.getPodcastMostListenedCategory() != null) {
                msj += "\nUser's most listened category: " + objConsumer.getPodcastMostListenedCategory() + " (" + objConsumer.getViewsCategory(objConsumer.getPodcastMostListenedCategory()) + " views).";
            } else {
                msj += "\nThis user has not listened to any podcast yet.";
            }
        } else {
            msj += "\nUser not found.";
        }
        return msj;
    }

    /**
     * <b>name: </b> reportTopProducers <br>
     * Generates a report of the top 5 most listened to producers on the platform. <br>
     * <b>pre: </b> Does not apply <br>
     * <b>post: </b> The system generates the report.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String reportTopProducers() {
        String msj = "\n-Top 5 Artists-";
        ArrayList<Artist> artists = new ArrayList<Artist>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Artist) {
                Artist objArtist = (Artist) users.get(i);
                artists.add(objArtist);
            }
        }
        if (artists.size() != 0) {
            Collections.sort(artists, Collections.reverseOrder());
            for (int i = 0; i < artists.size() && i < 5; i++) {
                msj += "\n" + (i+1) + ". " + artists.get(i).getNickname() + ": " + artists.get(i).getTotalRep() + " views.";
            }
        } else {
            msj = "\nNo artists registered.";
        }
        ArrayList<ContentProducer> producers = new ArrayList<ContentProducer>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof ContentProducer) {
                ContentProducer objProducer = (ContentProducer) users.get(i);
                producers.add(objProducer);
            }
        }
        if (producers.size() != 0) {
            msj += "\n\n-Top 5 Content Producers-";
            Collections.sort(producers, Collections.reverseOrder());
            for (int i = 0; i < producers.size() && i < 5; i++) {
                msj += "\n" + (i+1) + ". " + producers.get(i).getNickname() + ": " + producers.get(i).getTotalRep() + " views.";
            }
        } else {
            msj += "\n\nNo content producers registered.";
        }
        return msj;
    }

    /**
     * <b>name: </b> reportTopAudios <br>
     * Generates a report of the top 5 most listened to audios on the entire platform. <br>
     * <b>pre: </b> Does not apply <br>
     * <b>post: </b> The system generates the report.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String reportTopAudios() {
        String msj = "\n-Top 10 Songs-";
        ArrayList<Song> songs = new ArrayList<Song>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Artist) {
                Artist objArtist = (Artist) users.get(i);
                for (int j = 0; j < objArtist.getSongs().size(); j++) {
                    songs.add(objArtist.getSongs().get(j));
                }
            }
        }
        if (songs.size() != 0) {
            Collections.sort(songs, Collections.reverseOrder());
            for (int i = 0; i < songs.size() && i < 10; i++) {
                switch (songs.get(i).getGenre()) {
                    case ROCK:
                        msj += "\n" + (i+1) + ". " + songs.get(i).getName() + ". Genre: Rock (" + songs.get(i).getViews() + " views).";
                        break;
                    case POP:
                        msj += "\n" + (i+1) + ". " + songs.get(i).getName() + ". Genre: Pop (" + songs.get(i).getViews() + " views).";
                        break;
                    case TRAP:
                        msj += "\n" + (i+1) + ". " + songs.get(i).getName() + ". Genre: Trap (" + songs.get(i).getViews() + " views).";
                        break;
                    case HOUSE:
                        msj += "\n" + (i+1) + ". " + songs.get(i).getName() + ". Genre: House (" + songs.get(i).getViews() + " views).";
                        break;
                }
            }
        } else {
            msj = "\nNo songs registered.";
        }
        ArrayList<Podcast> podcasts = new ArrayList<Podcast>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof ContentProducer) {
                ContentProducer objProducer = (ContentProducer) users.get(i);
                for (int j = 0; j < objProducer.getPodcasts().size(); j++) {
                    podcasts.add(objProducer.getPodcasts().get(j));
                }
            }
        }
        if (podcasts.size() != 0) {
            msj += "\n\n-Top 10 Podcasts-";
            Collections.sort(podcasts, Collections.reverseOrder());
            for (int i = 0; i < podcasts.size() && i < 10; i++) {
                switch (podcasts.get(i).getCategory()) {
                    case POLITIC:
                        msj += "\n" + (i+1) + ". " + podcasts.get(i).getName() + ". Category: Politic (" + podcasts.get(i).getViews() + " views).";
                        break;
                    case ENTERTAINMENT:
                        msj += "\n" + (i+1) + ". " + podcasts.get(i).getName() + ". Category: Entertainment (" + podcasts.get(i).getViews() + " views).";
                        break;
                    case GAME:
                        msj += "\n" + (i+1) + ". " + podcasts.get(i).getName() + ". Category: Game (" + podcasts.get(i).getViews() + " views).";
                        break;
                    case FASHION:
                        msj += "\n" + (i+1) + ". " + podcasts.get(i).getName() + ". Category: Fashion (" + podcasts.get(i).getViews() + " views).";
                        break;
                }
            }
        } else {
            msj += "\n\nNo podcasts registered.";
        }
        return msj;
    }

    /**
     * <b>name: </b> reportGenderSales <br>
     * Generates a report of sales recorded by song genre. <br>
     * <b>pre: </b> Does not apply <br>
     * <b>post: </b> The system generates the report.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String reportGenderSales() {
        String msj = "\n-Gender stats-";
        int[] genres = new int[] {0, 0, 0, 0};
        double[] sales = new double[] {0, 0, 0, 0};
        ArrayList<Shop> songs = new ArrayList<Shop>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Standard) {
                Standard objConsumer = (Standard) users.get(i);
                for (int j = 0; j < objConsumer.getSongs().length; j++) {
                    if (objConsumer.getSongs()[j] != null) {
                        songs.add(objConsumer.getSongs()[j]);
                    }
                }
            } else if(users.get(i) instanceof Premium) {
                Premium objConsumer = (Premium) users.get(i);
                for (int j = 0; j < objConsumer.getSongs().size(); j++) {
                    songs.add(objConsumer.getSongs().get(j));
                }
            }
        }
        for (int i = 0; i < songs.size(); i++) {
            switch (songs.get(i).getSong().getGenre()) {
                case ROCK:
                    genres[0]++;
                    sales[0] += songs.get(i).getSong().getPrice();
                    break;
                case POP:
                    genres[1]++;
                    sales[1] += songs.get(i).getSong().getPrice();
                    break;
                case TRAP:
                    genres[2]++;
                    sales[2] += songs.get(i).getSong().getPrice();
                    break;
                case HOUSE:
                    genres[3]++;
                    sales[3] += songs.get(i).getSong().getPrice();
                    break;
            }
        }
        msj += "\nRock: " + genres[0] + " songs sold, " + sales[0] + "$ in sales.";
        msj += "\nPop: " + genres[1] + " songs sold, " + sales[1] + "$ in sales.";
        msj += "\nTrap: " + genres[2] + " songs sold, " + sales[2] + "$ in sales.";
        msj += "\nHouse: " + genres[3] + " songs sold, " + sales[3] + "$ in sales.";
        return msj;
    }

    /**
     * <b>name: </b> reportBestSong <br>
     * Generates a report of the best-selling song on the platform. <br>
     * <b>pre: </b> Does not apply <br>
     * <b>post: </b> The system generates the report.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String reportBestSong() {
        String msj = "\nBest selling song: ";
        ArrayList<Shop> songs = new ArrayList<Shop>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Standard) {
                Standard objConsumer = (Standard) users.get(i);
                for (int j = 0; j < objConsumer.getSongs().length; j++) {
                    if (objConsumer.getSongs()[j] != null) {
                        songs.add(objConsumer.getSongs()[j]);
                    }
                }
            } else if(users.get(i) instanceof Premium) {
                Premium objConsumer = (Premium) users.get(i);
                for (int j = 0; j < objConsumer.getSongs().size(); j++) {
                    songs.add(objConsumer.getSongs().get(j));
                }
            }
        }
        if (songs.size() != 0) {
            Collections.sort(songs, Collections.reverseOrder());
            msj += songs.get(0).getSong().getName() + ". \nTimes sold: " + songs.get(0).getSong().getAmountSales() + ". \nTotal sales: " + (songs.get(0).getSong().getPrice() * songs.get(0).getSong().getAmountSales()) + ".";
        } else {
            msj = "\nNo song has been purchased.";
        }
        return msj;
    }
}