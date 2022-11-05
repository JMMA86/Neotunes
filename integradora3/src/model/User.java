package model;
import java.time.LocalDate;

public class User {
    //constants

    //attributes
    private String nickname;
    private String id;
    private LocalDate date;

    //relations
    
    //methods
    public User(String nickname, String id, LocalDate date) {
        this.nickname = nickname;
        this.id = id;
        this.date = date;
    }

    public String getNickname() {
        return nickname;
    }
}
