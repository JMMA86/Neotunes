package ui;
import model.*;
import java.util.Scanner;

public class NeotunesApp {
    private static Scanner input;
    private AdminController controller;

    public NeotunesApp() {
        input = new Scanner(System.in);
        controller = new AdminController();
    }

    public static void main(String args[]) {
        NeotunesApp objMain = new NeotunesApp();
        System.out.println("Welcome to Neotunes!");
        int option = 0;

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
                case 0:
                    System.out.println("\nExit done.");
                    break;
                default:
                    System.out.println("\nError. Invalid option.");
                    break;
            }
        } while (option != 0);
    }

    public void displayMenu() {
        System.out.print("\n-Menu-" +
        "\n1. Register user" +
        "\n2. Register audio" +
        "\n3. Register playlist" +
        "\n4. Edit playlist" +
        "\n0. Exit" +
        "\nOption: ");
    }

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

    public void registerPlaylist() {
        System.out.print("\n-Playlist registration-\nEnter name (playlist): ");
        String name = input.nextLine();
        System.out.print("Enter user (nickname): ");
        String user = input.nextLine();
        System.out.println(controller.addPlaylist(name, user));
    }
    
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
}