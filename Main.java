import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        while (true) {

            System.out.println("\n===== LIBRARY SYSTEM =====");
            System.out.println("1 Add Book");
            System.out.println("2 Add User");
            System.out.println("3 Existing User");
            System.out.println("4 View Available Books");
            System.out.println("5 Show Users");
            System.out.println("6 Exit");

            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear invalid input
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    lib.addBook(sc);
                    break;

                case 2:
                    lib.registerUser(sc);
                    break;

                case 3:
                    System.out.print("Enter User ID: ");
                    int existingUserId = sc.nextInt();
                    sc.nextLine();

                    try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
                        String line;
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

                case 4:
                    lib.viewBooks();
                    break;

                case 5:
                    System.out.println("\nRegistered Users:");
                    try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading users file: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}