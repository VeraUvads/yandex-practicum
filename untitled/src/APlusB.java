import java.io.*;
import java.util.Scanner;

public class APlusB {
    public static void main(String[] args) {
        long result = 0;
        try (Scanner scanner = new Scanner(new File("input.txt"))) {
            result = scanner.nextInt() + scanner.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter pw = new PrintWriter("output.txt");
            pw.println(result);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}