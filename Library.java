import java.util.*;
import java.io.*;

public class Library {

    public void addBook(Scanner sc) {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        Book b = new Book(id, title, author, true);

        FileManager.writeToFile("books.csv", b.toCSV());

        System.out.println("Book added successfully.");

    }

    public void viewBooks() {
        try (BufferedReader br = new BufferedReader(new FileReader("books.csv"))) {
            String line = br.readLine(); // Skip header row
            System.out.println("\nAvailable Books:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }
    }

    public void registerUser(Scanner sc) {

        System.out.print("Enter User ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        User u = new User(id, name);

        FileManager.writeToFile("users.csv", id + "," + name);

        System.out.println("User registered successfully.");

    }

    public void borrowBook(Scanner sc) {

        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Borrow Date (yyyy-mm-dd): ");
        String borrowDate = sc.nextLine();

        System.out.print("Enter Due Date (yyyy-mm-dd): ");
        String dueDate = sc.nextLine();

        try {
            File inputFile = new File("books.csv");
            File tempFile = new File("books_temp.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line = reader.readLine(); // Skip header row
            boolean bookFound = false;

            while ((line = reader.readLine()) != null) {
                String[] bookDetails = line.split(",");
                try {
                    if (Integer.parseInt(bookDetails[0]) == bookId) {
                        bookDetails[3] = "false"; // Mark as taken
                        bookFound = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid book ID format in file: " + bookDetails[0]);
                }
                writer.write(String.join(",", bookDetails) + "\n");
            }

            reader.close();
            writer.close();

            if (bookFound) {
                tempFile.renameTo(inputFile);
                System.out.println("Book borrowing recorded and marked as taken.");
            } else {
                tempFile.delete();
                System.out.println("Book not found.");
            }

        } catch (IOException e) {
            System.out.println("Error processing books file: " + e.getMessage());
        }
    }
}