package ui;
import model.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
Program objective: Create a music streaming software that allows users to register consumers and producers,
audios and playlists; in addition to purchases, simulation of reproductions and display statistics.
@author Juan Manuel Marín Angarita
*/

/**
 * <b>Class: </b> NeotunesApp (main) <br>
 */
public class NeotunesApp {
    /**
     * <b>input: </b> Scanner variable to get user input.
     */
    private static Scanner input;
    /**
     * <b>controller: </b> Relation with the controller of the program.
     */
    private AdminController controller;
    
    /**
     * <b>name: </b> NeotunesApp <br>
     * This is the constructor of the class "NeotunesAPp" (main). <br>
     */
    public NeotunesApp() {
        input = new Scanner(System.in);
        controller = new AdminController();
    }

    /**
     * <b>name: </b> main <br>
     * Executes the program <br>
     * @param args - Main.
     */
    public static void main(String args[]) {
        NeotunesApp objMain = new NeotunesApp();
        System.out.println("Welcome to Neotunes!");
        int option = 0;
        
        objMain.tests();

        do {
            objMain.displayMenu();
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    objMain.registerUser();
                    break;
                case 2:
                    objMain.registerAudio();
                    break;
                case 3:
                    objMain.registerPlaylist();
                    break;
                case 4:
                    objMain.editPlaylist();
                    break;
                case 5:
                    objMain.sharePlaylist();
                    break;
                case 6:
                    objMain.simulateAudio();
                    break;
                case 7:
                    objMain.buySong();
                    break;
                case 8:
                    objMain.generateReport();
                    break;
                case 0:
                    System.out.println("\nExit done.");
                    break;
                default:
                    System.out.println("\nError. Invalid option.");
                    break;
            }
        } while (option != 0);
    }

    /**
     * <b>name: </b> tests <br>
     * Initializes the program with some preset registers. <br>
     */
    public void tests() {
        controller.addUser(1, "Juan", "1126597295");
        controller.addUser(1, "Peter", "Bruno Mars", "google.com", "125611651");
        controller.addUser(1, "Carolina", "Karol G", "youtube.com", "1056156165");
        controller.addUser(2, "Paco", "Bizarrap", "a", "1");
        controller.addAudio("Provenza", "google.com", "Provenza", 20, 1, "Karol G", 50);
        controller.addAudio("Universo", "google.com", "El fin del mundo", 1, "Bizarrap", 3500);
    }
    
    /**
     * <b>name: </b> displayMenu <br>
     * Shows the available options to execute in the program. <br>
     */
    public void displayMenu() {
        System.out.print("\n-Menu-" +
        "\n1. Register user" +
        "\n2. Register audio" +
        "\n3. Register playlist" +
        "\n4. Edit playlist" +
        "\n5. Share playlist" +
        "\n6. Simulate audio" +
        "\n7. Buy song" +
        "\n8. Generate report" +
        "\n0. Exit" +
        "\nOption: ");
    }

    /**
     * <b>name: </b> registerUser <br>
     * This function registers an user (consumers and producers) to the system. <br>
     */
    public void registerUser() {
        System.out.print("\n-User registration-" +
        "\nEnter nickname: ");
        String nickname = input.nextLine();
        System.out.print("Enter id: ");
        String id = input.nextLine();
        System.out.print("\nEnter the type of user to add:" +
        "\n1. Consumer" +
        "\n2. Producer" +
        "\nOption: ");
        int option = input.nextInt();
        int type = 0;
        switch (option) {
            case 1:
                input.nextLine();
                System.out.print("\n-Consumer selected-\nEnter a type of consumer: " +
                "\n1. Standard" +
                "\n2. Premium" +
                "\nOption: ");
                type = input.nextInt();
                System.out.println(controller.addUser(type, nickname, id));
                break;
            case 2:
                input.nextLine();
                System.out.print("\n-Producer selected-\nEnter name: ");
                String name = input.nextLine();
                System.out.print("Enter url image: ");
                String urlImage = input.nextLine();
                System.out.print("\nEnter a type of producer: " +
                "\n1. Artist" +
                "\n2. Content Producer" +
                "\nOption: "); 
                type = input.nextInt();
                input.nextLine();
                System.out.println(controller.addUser(type, name, nickname, urlImage, id));
                break;
            default:
                System.out.println("Error. Invalid option.");
                break;
        }
    }

    /**
     * <b>name: </b> registerAudio <br>
     * This function registers an audio (song or podcast) of a producer in the system. <br>
     */
    public void registerAudio() {
        System.out.print("\n-Audio registration-" +
        "\nEnter name: ");
        String name = input.nextLine();
        System.out.print("Enter url image: ");
        String urlImage = input.nextLine();
        System.out.print("\nEnter a type of audio: " +
        "\n1. Song" +
        "\n2. Podcast" +
        "\nOption: ");
        int audio = input.nextInt();
        System.out.print("\nEnter duration (only minutes): ");
        int minutes = input.nextInt();
        System.out.print("Enter duration (only seconds): ");
        int seconds = input.nextInt();
        switch (audio) {
            case 1:
                input.nextLine();
                System.out.print("\n-Song selected-\nEnter album: ");
                String album = input.nextLine();
                System.out.print("Enter price: ");
                double price = input.nextDouble();
                System.out.print("\nSelect genre:" +
                "\n1. Rock" +
                "\n2. Pop" +
                "\n3. Trap" +
                "\n4. House" +
                "\nOption: ");
                int genre = input.nextInt();
                input.nextLine();
                System.out.print("\nEnter its artist (nickname): ");
                String artist = input.nextLine();
                System.out.println(controller.addAudio(name, urlImage, album, price, genre, artist, minutes * seconds));
                break;
            case 2:
                input.nextLine();
                System.out.print("\n-Podcast selected-\nEnter description: ");
                String description = input.nextLine();
                System.out.print("\nSelect category:" +
                "\n1. Politic" +
                "\n2. Entertainment" +
                "\n3. Game" +
                "\n4. Fashion" +
                "\nOption: ");
                int category = input.nextInt();
                input.nextLine();
                System.out.print("\nEnter its content producer (nickname): ");
                String contentProducer = input.nextLine();
                System.out.println(controller.addAudio(name, urlImage, description, category, contentProducer, minutes * seconds));
                break;
            default:
                System.out.print("Error. Invalid type.");
                break;
        }  
    }

    /**
     * <b>name: </b> registerPlayist <br>
     * This function registers a playlist for a consumer. <br>
     */
    public void registerPlaylist() {
        System.out.print("\n-Playlist registration-\nEnter name (playlist): ");
        String name = input.nextLine();
        System.out.print("Enter user (nickname): ");
        String user = input.nextLine();
        System.out.println(controller.addPlaylist(name, user));
    }
    
    /**
     * <b>name: </b> editPlaylist <br>
     * This function modifies a playlist of a consumer with options as adding and deleting audio. <br>
     */
    public void editPlaylist() {
        System.out.print("\n-Playlist edition-" +
        "\nEnter name (playlist): ");
        String playlist = input.nextLine();
        System.out.print("\nSelect an option:" +
        "\n1. Add song" +
        "\n2. Delete song" +
        "\n3. Add podcast" +
        "\n4. Delete podcast" +
        "\nOption: ");
        int option = input.nextInt();
        input.nextLine();
        System.out.print("\nEnter audio (name): ");
        String audio = input.nextLine();
        System.out.print("Enter author (producer nickname): ");
        String producer = input.nextLine();
        System.out.print("Enter consumer (nickname): ");
        String consumer = input.nextLine();
        System.out.println(controller.editPlaylist(playlist, audio, consumer, producer, option));
    }

    /**
     * <b>name: </b> sharePlaylist <br>
     * This function creates an id of a playlist so that it can be shared between users. <br>
     */
    public void sharePlaylist() {
        System.out.print("\nEnter name of consumer: ");
        String name = input.nextLine();
        System.out.print("Enter playlist to share: ");
        String playlist = input.nextLine();
        System.out.println(controller.sharePlaylist(name, playlist));
    }

    /**
     * <b>name: </b> simulateAudio <br>
     * This function simulates the playback of an audio. <br>
     */
    public void simulateAudio() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int counter = 0;
            public void run() {
                System.out.print(".");
                counter++;
                if (counter == 3) {
                    cancel();
                }
            }
        };
        System.out.print("\nEnter consumer (nickname): ");
        String nickname = input.nextLine();
        System.out.print("Enter name of audio: ");
        String audio = input.nextLine();
        System.out.print("\nEnter a type of audio: " +
        "\n1. Song" +
        "\n2. Podcast" +
        "\nOption: ");
        int type = input.nextInt();
        input.nextLine();
        System.out.print("\nEnter name of producer: ");
        String producer = input.nextLine();
        String msj = controller.simulateAudio(nickname, audio, type, producer);
        if (msj.equalsIgnoreCase("Playing...")) {
            System.out.println("Playing " + audio + " from " + producer + "...");
            timer.schedule(task, 1000, 1000);
            try {
                Thread.sleep(4000);
            } catch (Exception x) {
                System.out.println(x);
            }
            System.out.println("\nReproduction done.");
        } else {
            System.out.println(msj);
        }
    }

    /**
     * <b>name: </b> buySong <br>
     * This feature allows the consumer user to purchase a song. <br>
     */
    public void buySong() {
        System.out.print("\nEnter consumer (nickname): ");
        String consumer = input.nextLine();
        System.out.print("Enter song name: ");
        String song = input.nextLine();
        System.out.print("Enter artist: ");
        String artist = input.nextLine();
        System.out.println(controller.buySong(consumer, song, artist));
    }

    /**
     * <b>name: </b> generateReport <br>
     * This function generates reports as requested. <br>
     */
    public void generateReport() {
        System.out.print("\nSelect report: " +
        "\n1. Report plays by audio type" +
        "\n2. Report genre of most listened song" +
        "\n3. Report most listened to podcast category" +
        "\n4. Report top 5 producers by type" +
        "\n5. Report top 10 audios by type" +
        "\n6. Report sales by song genre" +
        "\n7. Report top selling song" +
        "\nOption: ");
        int option = input.nextInt();
        String name = null;
        input.nextLine();
        switch (option) {
            case 1:
                System.out.println(controller.reportAudioViews());
                break;
            case 2:
                System.out.print("\nEnter consumer (nickname): ");
                name = input.nextLine();
                System.out.println(controller.reportBestGenre(name));
                break;
            case 3:
                System.out.print("\nEnter consumer (nickname): ");
                name = input.nextLine();
                System.out.println(controller.reportBestCategory(name));
                break;
            case 4:
                System.out.println(controller.reportTopProducers());
                break;
            case 5:
                System.out.println(controller.reportTopAudios());
                break;
            case 6:
                System.out.println(controller.reportGenderSales());
                break;
            case 7:
                System.out.println(controller.reportBestSong());
                break;
            default:
                System.out.println("Error. Invalid option.");
                break;
        }
    }
}