import java.io.*;

public class FileManager {

    public static void writeToFile(String fileName, String data) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine(); // Ensure data is written on a new line
        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        }

    }

}
