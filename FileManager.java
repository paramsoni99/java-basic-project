import java.io.*;

public class FileManager {

    public static void writeToFile(String fileName, String data) {

        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(data + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("File Error: " + e.getMessage());
        }

    }

}
