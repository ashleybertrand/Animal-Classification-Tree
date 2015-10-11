/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outlab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Ashley Bertrand
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
    
    public String[] getData() {
        return data;
    }
    
    public void load() {
        try {
            FileReader file = new FileReader("example.txt");
            Scanner inFile = new Scanner(file);

            ArrayList<String> tempList = new ArrayList<>();
            while (inFile.hasNextLine()) {
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

    public void save(ArrayList<String> data) {
        Scanner in = new Scanner(System.in);
        try {
            PrintWriter outFile = new PrintWriter(new File("example.txt"));

            for (int i = 0; i < data.size(); i++) {
                outFile.println(data.get(i));
            }
            outFile.close();
        } catch (Exception e) {
            System.out.println("Cannot find that file");
        }
    }
}