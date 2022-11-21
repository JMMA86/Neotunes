package model;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <b>Class: </b> Consumer <br>
 */
public class Consumer extends User {
    //constants
    /**
     * Purchase limit for standard users
     */
    public int LIMIT_SONGS = 100;
    /**
     * Playlist limit for standard users
     */
    public int LIMIT_PLAYLISTS = 20;

    //attributes
    private String podcastMostListenedCategory;
    private String songMostListenedGenre;

    //relations
    private ArrayList<Genre> genres;
    private ArrayList<Integer> numGenres;
    private ArrayList<Category> categories;
    private ArrayList<Integer> numCategories;

    //methods
    /**
     * <b>name: </b> Consumer <br>
     * This is the constructor of the class "Consumer". <br>
     * @param nickname Nickname of the consumer.
     * @param id Id of the consumer.
     * @param date Registration date.
     */
    public Consumer(String nickname, String id, LocalDate date) {
        super(nickname, id, date);
        this.genres = new ArrayList<Genre>();
        this.numGenres = new ArrayList<Integer>();
        this.categories = new ArrayList<Category>();
        this.numCategories = new ArrayList<Integer>();
    }

    /**
     * <b>name: </b> addPlaylist <br>
     * Adds a playlist to a consumer. <br>
     * <b>pre: </b> Defined by type of consumer. <br>
     * <b>post: </b> Adds the playlist if it's possible.
     * @param name Name of the playlist to be added.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String addPlaylist(String name) {
        String msj = "Added.";
        return msj;
    }

    /**
     * <b>name: </b> searchPlaylist <br>
     * Search for a playlist from a given name. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the playlist if it is found.
     * @param name Name of the playlist to be searched.
     * @return <b>playlist</b>. Contains the playlist if it is found.
     */
    public Playlist searchPlaylist(String name) {
        Playlist playlist = null;
        return playlist;
    }

    /**
     * <b>name: </b> updatePlaylist <br>
     * Apply changes for a playlist of this user. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Updates a playlist.
     * @param playlist Playlist to be updated.
     */
    public void updatePlaylist(Playlist playlist) {
    }

    /**
     * <b>name: </b> updateStats <br>
     * Update user statistics regarding activity on the platform (for songs). <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Updates user.
     * @param audio Song involved in the last activity.
     */
    public void updateStats(Song audio) {
        boolean found = false;
        Genre genreName;
        int genreCounter;
        for (int i = 0; i < genres.size() && !found; i++) {
            if (genres.get(i) == audio.getGenre()) {
                genreName = genres.get(i);
                genreCounter = numGenres.get(i);
                genres.remove(i);
                numGenres.remove(i);
                genreCounter++;
                genres.add(genreName);
                numGenres.add(genreCounter);
                found = true;
            }
        }
        if (!found) {
            genres.add(audio.getGenre());
            numGenres.add(1);
        }
        genreCounter = 0;
        for (int i = 0; i < genres.size(); i++) {
            if (numGenres.get(i) > genreCounter) {
                genreCounter = numGenres.get(i);
                genreName = genres.get(i);
                switch (genreName) {
                    case ROCK:
                        songMostListenedGenre = "Rock";
                        break;
                    case POP:
                        songMostListenedGenre = "Pop";
                        break;
                    case TRAP:
                        songMostListenedGenre = "Trap";
                        break;
                    case HOUSE:
                        songMostListenedGenre = "House";
                        break;
                }
            }
        }
    }

    /**
     * <b>name: </b> updateStats <br>
     * Update user statistics regarding activity on the platform (for podcasts). <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Updates user.
     * @param audio Podcast involved in the last activity.
     */
    public void updateStats(Podcast audio) {
        boolean found = false;
        Category categoryName;
        int categoryCounter;
        for (int i = 0; i < categories.size() && !found; i++) {
            if (categories.get(i) == audio.getCategory()) {
                categoryName = categories.get(i);
                categoryCounter = numCategories.get(i);
                categories.remove(i);
                numCategories.remove(i);
                categoryCounter++;
                categories.add(categoryName);
                numCategories.add(categoryCounter);
                found = true;
            }
        }
        if (!found) {
            categories.add(audio.getCategory());
            numCategories.add(1);
        }
        categoryCounter = 0;
        for (int i = 0; i < categories.size(); i++) {
            if (numCategories.get(i) > categoryCounter) {
                categoryCounter = numCategories.get(i);
                categoryName = categories.get(i);
                switch (categoryName) {
                    case POLITIC:
                        podcastMostListenedCategory = "Politic";
                        break;
                    case ENTERTAINMENT:
                        podcastMostListenedCategory = "Entertainment";
                        break;
                    case GAME:
                        podcastMostListenedCategory = "Game";
                        break;
                    case FASHION:
                        podcastMostListenedCategory = "Fashion";
                        break;
                }
            }
        }
    }

    /**
     * <b>name: </b> getPodcastMostListenedCategory <br>
     * Returns the podcast category most listened to by this user. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the category.
     * @return <b>podcastMostListenedCategory</b> Podcast category.
     */
    public String getPodcastMostListenedCategory() {
        return podcastMostListenedCategory;
    }

    /**
     * <b>name: </b> getViewsGenre <br>
     * Returns the number of views of a song genre by a user. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the views.
     * @param genreName Gender to consult.
     * @return <b>views</b> Number of views.
     */
    public int getViewsGenre(String genreName) {
        int views = 0;
        Genre genre = null;
        switch (genreName) {
            case "Rock":
                genre = Genre.ROCK;
                break;
            case "Pop":
                genre = Genre.POP;
                break;
            case "Trap":
                genre = Genre.TRAP;
                break;
            case "House":
                genre = Genre.HOUSE;
                break;
        }
        for (int i = 0; i < genres.size(); i++) {
            if (genres.get(i) == genre) {
                views = numGenres.get(i);
            }
        }
        return views;
    }

    /**
     *<b>name: </b> getViewsCategory <br>
     * Returns the number of views of a podcast category by a user. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the views.
     * @param categoryName Category to consult.
     * @return <b>views</b> Number of views.
     */
    public int getViewsCategory(String categoryName) {
        int views = 0;
        Category category = null;
        switch (categoryName) {
            case "Politic":
                category = Category.POLITIC;
                break;
            case "Entertainment":
                category = Category.ENTERTAINMENT;
                break;
            case "Game":
                category = Category.GAME;
                break;
            case "Fashion":
                category = Category.FASHION;
                break;
        }
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i) == category) {
                views = numCategories.get(i);
            }
        }
        return views;
    }

    /**
     * <b>name: </b> getSongMostListenedGenre <br>
     * Returns the music genre most listened to by the user. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the genre.
     * @return <b>songMostListenedGenre</b> Song genre.
     */
    public String getSongMostListenedGenre() {
        return songMostListenedGenre;
    }

    /**
     * <b>name: </b> buySong <br>
     * Purchase a song for a user. <br>
     * <b>pre: </b> The song must exist. <br>
     * <b>post: </b> The purchase is made.
     * @param song Song to buy.
     * @return <b>msj</b>. Contains the result of the operation.
     */
    public String buySong(Song song) {
        String msj = "Succesfully purchased.";
        return msj;
    }
}
