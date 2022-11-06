package model;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdminController {
    //constants

    //attributes

    //relations
    private ArrayList<User> users;
    
    //methods
    public AdminController() {
        this.users = new ArrayList<User>();
    }

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

    public String addUser(int type, String name, String nickname, String urlImage, String id) {
        String msj = "Added succesfully.";
        LocalDate date = LocalDate.now();
        User user = null;
        if (searchUser(nickname) == null) {
            switch (type) {
                case 1:
                    user = new Artist(nickname, id, date, name, urlImage);
                    users.add(user);
                    break;
                case 2:
                    user = new ContentProducer(nickname, id, date, name, urlImage);
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

    public String addPlaylist(String name, String user) {
        String msj = "Added correctly.";
        boolean found = false;
        if (searchUser(user) != null) {
            for (int i = 0; i < users.size() && !found; i++) {
                if (users.get(i).getNickname().equalsIgnoreCase(user)) {
                    if (users.get(i) instanceof Producer) {
                        msj = "Error. This user is a producer.";
                    } else {
                        if (users.get(i) instanceof Standard) {
                            Standard consumer = (Standard) users.get(i);
                            String operationResult = consumer.addPlaylist(name);
                            if (operationResult.equalsIgnoreCase("Repeated.")) {
                                msj = "Error. This user has a playlist with the same name.";
                            } else if (operationResult.equalsIgnoreCase("Limit reached.")){
                                msj = "Error. Limit reached for this user.";
                            } else {
                                users.remove(i);
                                users.add(consumer);
                            }
                        } else if (users.get(i) instanceof Premium) {
                            Premium consumer = (Premium) users.get(i);
                            if (consumer.addPlaylist(name).equalsIgnoreCase("Repeated.")) {
                                msj = "Error. This user has a playlist with the same name.";
                            } else {
                                users.remove(i);
                                users.add(consumer);
                            }
                        }
                    }
                }
            }
        } else {
            msj = "Error. User does not exist.";
        }
        return msj;
    }
    
    public String editPlaylist(String playlistName, String audioName, String consumerName, String producerName, int option) {
        /* Option:
        "1. Add song" +
        "2. Delete song" +
        "3. Add podcast" +
        "4. Delete podcast" +

        1. Verificar que el consumidor y el productor existen
        2. Verificar que la canción existe
        3. Ejecutar opción
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
            if (searchUser(consumerName) instanceof Standard) {
                Standard consumerObj = (Standard) searchUser(consumerName);
                consumer = consumerObj;
            } else if (searchUser(consumerName) instanceof Premium) {
                Premium consumerObj = (Premium) searchUser(consumerName);
                consumer = consumerObj;
            }
            if (searchUser(producerName) instanceof Artist) {
                Artist producerObj = (Artist) searchUser(producerName);
                producer = producerObj;
            } else if (searchUser(producerName) instanceof ContentProducer) {
                ContentProducer producerObj = (ContentProducer) searchUser(producerName);
                producer = producerObj;
            }
            switch (changes) {
                case 1:
                    //Iniciar búsqueda de la canción
                    if (producer instanceof Artist) {
                        Artist producerObj = (Artist) producer;
                        if (producerObj.searchSong(audioName) != null) {
                            //song = canción encontrada
                            song = producerObj.searchSong(audioName);
                            //Iniciar búsqueda de la playlist del usuario
                            if (consumer instanceof Standard) {
                                Standard consumerObj = (Standard) consumer;
                                if (consumerObj.searchPlaylist(playlistName) != null) {
                                    Playlist editablePlaylist = consumerObj.searchPlaylist(playlistName);
                                    if (option == 1) {
                                        if (editablePlaylist.searchAudio(audioName, producerObj) == null) {
                                            editablePlaylist.addAudio(song);
                                            consumerObj.updatePlaylist(editablePlaylist);
                                        } else {
                                            msj = "Error. This song exists in that playlist.";
                                        }
                                    } else if (option == 2) {
                                        if (editablePlaylist.searchAudio(audioName, producerObj) != null) {
                                            editablePlaylist.deleteAudio(song, producerObj);
                                            consumerObj.updatePlaylist(editablePlaylist);
                                        } else {
                                            msj = "Error. Song not found in the playlist.";
                                        }
                                    }
                                } else {
                                    msj = "Error. Playlist not found.";
                                }
                            } else if (consumer instanceof Premium) {
                                Premium consumerObj = (Premium) consumer;
                                if (consumerObj.searchPlaylist(playlistName) != null) {
                                    Playlist editablePlaylist = consumerObj.searchPlaylist(playlistName);
                                    if (option == 1) {
                                        if (editablePlaylist.searchAudio(audioName, producerObj) == null) {
                                            editablePlaylist.addAudio(song);
                                            consumerObj.updatePlaylist(editablePlaylist);
                                        } else {
                                            msj = "Error. This song exists in that playlist.";
                                        }
                                    } else if (option == 2) {
                                        if (editablePlaylist.searchAudio(audioName, producerObj) != null) {
                                            editablePlaylist.deleteAudio(song, producerObj);
                                            consumerObj.updatePlaylist(editablePlaylist);
                                        } else {
                                            msj = "Error. Song not found in the playlist.";
                                        }
                                    }
                                } else {
                                    msj = "Error. Playlist not found.";
                                }
                            }
                        } else {
                            msj = "Error. Song not found.";
                        }
                    } else {
                        msj = "Error. This producer is not an artist.";
                    }
                    break;
                case 2:
                    //Iniciar búsqueda del podcast
                    if (producer instanceof ContentProducer) {
                        ContentProducer producerObj = (ContentProducer) producer;
                        if (producerObj.searchPodcast(audioName) != null) {
                            //podcast = podcast encontrado
                            podcast = producerObj.searchPodcast(audioName);
                            //Iniciar búsqueda de la playlist del usuario
                            if (consumer instanceof Standard) {
                                Standard consumerObj = (Standard) consumer;
                                if (consumerObj.searchPlaylist(playlistName) != null) {
                                    Playlist editablePlaylist = consumerObj.searchPlaylist(playlistName);
                                    if (option == 3) {
                                        if (editablePlaylist.searchAudio(audioName, producerObj) == null) {
                                            editablePlaylist.addAudio(podcast);
                                            consumerObj.updatePlaylist(editablePlaylist);
                                        } else {
                                            msj = "Error. This podcast exists in that playlist.";
                                        }
                                    } else if (option == 4) {
                                        if (editablePlaylist.searchAudio(audioName, producerObj) != null) {
                                            editablePlaylist.deleteAudio(podcast, producerObj);
                                            consumerObj.updatePlaylist(editablePlaylist);
                                        } else {
                                            msj = "Error. Podcast not found in the playlist.";
                                        }
                                    }
                                } else {
                                    msj = "Error. Playlist not found.";
                                }
                            } else if (consumer instanceof Premium) {
                                Premium consumerObj = (Premium) consumer;
                                if (consumerObj.searchPlaylist(playlistName) != null) {
                                    Playlist editablePlaylist = consumerObj.searchPlaylist(playlistName);
                                    if (option == 3) {
                                        if (editablePlaylist.searchAudio(audioName, producerObj) == null) {
                                            editablePlaylist.addAudio(podcast);
                                            consumerObj.updatePlaylist(editablePlaylist);
                                        } else {
                                            msj = "Error. This podcast exists in that playlist.";
                                        }
                                    } else if (option == 4) {
                                        if (editablePlaylist.searchAudio(audioName, producerObj) != null) {
                                            editablePlaylist.deleteAudio(podcast, producerObj);
                                            consumerObj.updatePlaylist(editablePlaylist);
                                        } else {
                                            msj = "Error. Podcast not found in the playlist.";
                                        }
                                    }
                                } else {
                                    msj = "Error. Playlist not found.";
                                }
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
}
