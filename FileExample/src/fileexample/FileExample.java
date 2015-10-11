/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fileexample;

/**
 *
 * @author yaw
 */
public class FileExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] names = {"Joe", "Amy", "Bill", "Kate"};
        FileStuff ex = new FileStuff(names);
        ex.printData();
        ex.load();
        ex.printData();
    }

}
