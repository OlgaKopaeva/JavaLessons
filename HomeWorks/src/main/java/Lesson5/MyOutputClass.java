package Lesson5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MyOutputClass {

    public static void writeToMyList(ArrayList<?> newNumbers, File file, boolean append) throws IOException {
        try (FileWriter out = new FileWriter(file, append)) {
            for (int i = 0; i < newNumbers.size(); i++) {
                out.write(newNumbers.get(i).toString());
                if (i != newNumbers.size() - 1) {
                    out.write(';');
                }
            }
            out.write(10);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void readFromMyList(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int x = 0;
            while ((x = reader.read()) != -1) {
                String s = reader.readLine();
                System.out.println((char) x + s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        File file = new File("./file.csv");
        file.createNewFile();

        ArrayList<String> headers = new ArrayList<String>(Arrays.asList("ID", "Number1", "Number2"));
        ArrayList<Integer> newNumbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        ArrayList<Integer> newNumbers1 = new ArrayList<Integer>(Arrays.asList(4, 5, 6));
        writeToMyList(headers, file, false);
        writeToMyList(newNumbers, file, true);
        writeToMyList(newNumbers1, file, true);
        readFromMyList(file);

    }
}