package model;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.text.AbstractDocument.Content;

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
                        user.addSong(podcast);
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
}
