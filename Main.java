import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        while (true) {

            System.out.println("\n===== WELCOME TO LIBRARY SYSTEM =====");
            System.out.println("What would you like to perform today?");
            System.out.println("1 Manage Books");
            System.out.println("2 Manage Users");
            System.out.println("3 Exit");

            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear invalid input
                continue;
            }

            int mainChoice = sc.nextInt();
            sc.nextLine(); // Clear the buffer

            switch (mainChoice) {
                case 1:
                    manageBooks(sc, lib);
                    break;

                case 2:
                    manageUsers(sc, lib);
                    break;

                case 3:
                    System.out.println("Exiting system. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageBooks(Scanner sc, Library lib) {
        while (true) {
            System.out.println("\n===== MANAGE BOOKS =====");
            System.out.println("1 Add a New Book");
            System.out.println("2 Borrow a Book");
            System.out.println("3 Check Available Books");
            System.out.println("4 Go Back");

            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear invalid input
                continue;
            }

            int bookChoice = sc.nextInt();
            sc.nextLine(); // Clear the buffer

            switch (bookChoice) {
                case 1:
                    lib.addBook(sc);
                    break;

                case 2:
                    lib.borrowBook(sc);
                    break;

                case 3:
                    lib.viewBooks();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageUsers(Scanner sc, Library lib) {
        while (true) {
            System.out.println("\n===== MANAGE USERS =====");
            System.out.println("1 Add a New User");
            System.out.println("2 Existing User");
            System.out.println("3 Show All Users");
            System.out.println("4 Go Back");

            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear invalid input
                continue;
            }

            int userChoice = sc.nextInt();
            sc.nextLine(); // Clear the buffer

            switch (userChoice) {
                case 1:
                    lib.registerUser(sc);
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    int existingUserId = sc.nextInt();
                    sc.nextLine();

                    try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
                        String line = br.readLine(); // Skip header row
                        boolean userFound = false;
                        while ((line = br.readLine()) != null) {
                            String[] userDetails = line.split(",");
                            if (Integer.parseInt(userDetails[0]) == existingUserId) {
                                System.out.println("User Found: " + userDetails[1]);
                                userFound = true;
                                break;
                            }
                        }
                        if (!userFound) {
                            System.out.println("User not found.");
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading users file: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("\nRegistered Users:");
                    try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
                        String line = br.readLine(); // Skip header row
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading users file: " + e.getMessage());
                    }
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}