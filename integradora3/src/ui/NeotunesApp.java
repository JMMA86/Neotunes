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
                    objMain.registerConsumer();
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
        "\n1. Register consumer" +
        "\nOption: ");
    }

    public void registerConsumer() {
        System.out.println("Enter a name: ");
        String name = input.nextLine();
    }
}