/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outlab5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Ashley Bertrand
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] names = {"Joe", "Amy", "Bill", "Kate"};
        FileStuff ex = new FileStuff(names);
        ex.load();
        BinaryTree tree = new BinaryTree(ex.getData());
        ArrayList<String> list = new ArrayList<>();
        list = tree.firstRun();
        ex.save(list);
    }
}
