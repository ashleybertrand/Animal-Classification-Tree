package fileexample;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author yaw
 */
public class FileStuff {
    private String[] data;
    public FileStuff(String[] data) {
        this.data = data;
    }
    
    public void printData() {
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
        System.out.println();
    }
    
    public void load() {
        try {
            FileReader file = new FileReader("example.txt");
            Scanner inFile = new Scanner(file);

            ArrayList<String> tempList = new ArrayList<>();
            while (inFile.hasNext()) {
                String value = inFile.nextLine();
                tempList.add(value);
            }
            inFile.close();
            data = tempList.toArray(new String[tempList.size()]);
        } catch (FileNotFoundException e) {
            //do whatever you want to happen if the file doesn't exist yet.
            System.out.println("File not found.");
        }
    }

    public void save() {
        Scanner in = new Scanner(System.in);
        try {
            PrintWriter outFile = new PrintWriter("example.txt");

            for (int i = 0; i < data.length; i++) {
                outFile.println(data[i]);
            }
            outFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find that file");
        }
    }
}
