package model;

/**
 * <b>Class: </b> Song <br>
 */
public class Song extends Audio implements Playable, Salable, Comparable<Song> {
    //constants

    //attributes
    private String album;
    private double price;
    /**
     * Sales of the song
     */
    protected Integer amountSales;

    //relations
    private Genre genre;

    //methods
    /**
     * <b>name: </b> Song <br>
     * This is the constructor of the class "Song". <br>
     * @param name Name of the song.
     * @param urlImage URL icon.
     * @param timeRep Length in seconds of the song.
     * @param album Album of the song.
     * @param price Price of the song.
     * @param genre Genre of the song (1: Rock, 2: Pop, 3: Trap, 4: House).
     */
    public Song(String name, String urlImage, int timeRep, String album, double price, int genre) {
        super(name, urlImage, timeRep);
        this.album = album;
        this.price = price;
        this.amountSales = 0;
        switch (genre) {
            case 1:
                this.genre = Genre.ROCK;
                break;
            case 2:
                this.genre = Genre.POP;
                break;
            case 3:
                this.genre = Genre.TRAP;
                break;
            case 4:
                this.genre = Genre.HOUSE;
                break;
        }    
    }

    /**
     * <b>name: </b> getGenre <br>
     * Returns the genre of the song. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the genre.
     * @return <b>genre</b> genre of the song.
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * <b>name: </b> getViews <br>
     * Returns views of the song. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the views.
     * @return <b>views</b> Views of the song.
     */
    public int getViews() {
        return views;
    }

    /**
     * <b>name: </b> getPrice <br>
     * Returns price of the song. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the price.
     * @return <b>price</b> Price of the song.
     */
    public double getPrice() {
        return price;
    }

    /**
     * <b>name: </b> getAmountSales <br>
     * Returns sales of the song. <br>
     * <b>pre: </b> Does not apply. <br>
     * <b>post: </b> Returns the sales.
     * @return <b>amountSales</b> Sales of the song.
     */
    public Integer getAmountSales() {
        return amountSales;
    }

    @Override
    public void playAudio() {
        views++;
    }

    @Override
    public void sell() {
        amountSales++;
    }

    @Override
    public int compareTo(Song o) {
        return views.compareTo(o.getViews());
    }
}
