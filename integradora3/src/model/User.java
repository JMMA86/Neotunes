package model;
import java.time.LocalDate;

/**
 * <b>Class: </b> User <br>
 */
public class User {
    //constants

    //attributes
    private String nickname;
    private String id;
    private LocalDate date;

    //relations
    
    //methods
    /**
     * <b>name: </b> User <br>
     * This is the constructor of the class "User". <br>
     * @param nickname Nickname of new user.
     * @param id Id of new user.
     * @param date Registration date.
     */
    public User(String nickname, String id, LocalDate date) {
        this.nickname = nickname;
        this.id = id;
        this.date = date;
    }

    /**
     * <b>name: </b> getNickname <br>
     * Returns the nickname of an user. <br>
     * @return <b>nickname</b> Nickname of the user.
     */
    public String getNickname() {
        return nickname;
    }
}
