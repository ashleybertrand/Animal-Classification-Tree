/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outlab5;

import java.util.*;

/**
 *
 * @author Ashley Bertrand
 */
public class BinaryTree {

    private Node root;
    private Node firstLeft;
    private Node firstRight;
    private Node current;
    private Node newChar;
    private Node newAnimal;
    private String characteristic;
    private String animal;
    private String traitsSoFar = "";
    private ArrayList<String> tempList;

    //Constructor used for testing
    public BinaryTree() {
        root = new Node("furry");
        firstLeft = new Node("dog");
        firstRight = new Node("snake");
        current = root;
        root.setLeft(firstLeft);
        root.setRight(firstRight);
        firstLeft.setParent(root);
        firstRight.setParent(root);
    }

    public BinaryTree(String[] inputData) {
        root = new Node(inputData[0]);
        tempList = new ArrayList<>();
        int lineNum = 1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (lineNum * 2 < inputData.length) {
                if (!inputData[lineNum * 2].equals(" ")) {
                    //children in file appear on lines n*2 and n*2+1, where n is the line number of the parent
                    Node tempNode1 = new Node(inputData[lineNum * 2 - 1]);
                    Node tempNode2 = new Node(inputData[lineNum * 2]);

                    //setting corresponding parents and children
                    node.setLeft(tempNode1);
                    node.setRight(tempNode2);
                    tempNode1.setParent(node);
                    tempNode2.setParent(node);
                    
                    //adding children nodes to queue
                    queue.add(tempNode1);
                    queue.add(tempNode2);
                } else {
                    //nodes that have no children will appear as blank lines in file
                    node.setLeft(null);
                    node.setRight(null);
                }
            }
            lineNum++;
        }
    }
    
    public ArrayList<String> firstRun() {
        run();
        return tempList;
    }

    public void run() {
        current = root;
        System.out.print("Do you have another animal to identify? (Y/N) > ");
        traitsSoFar = "";
        Scanner val = new Scanner(System.in);
        String choice = val.nextLine();
        if (choice.equalsIgnoreCase("Y")) {
            execute();
        } else {
            Queue<Node> queue = new LinkedList<>();
            if (root != null) {
                //modifying queue and tempList for root
                queue.add(root);
                tempList.add(root.getName());
                //modifying queue and tempList for the rest of the tree
                while (!queue.isEmpty()) {
                    Node node = queue.remove();
                    if (node.getLeft() == null) {
                        tempList.add(" ");
                        tempList.add(" ");
                    } else {
                        tempList.add(node.getLeft().getName());
                        tempList.add(node.getRight().getName());
                        queue.add(node.getLeft());
                        queue.add(node.getRight());
                    }
                }
            }
        }
    }

    public void execute() {
        Scanner val = new Scanner(System.in);
        System.out.print("Is this animal " + current.getName() + "? (Y/N) > ");     //current is pointing to a characteristic
        String choice = val.next();

        if (choice.equalsIgnoreCase("Y")) {
            traitsSoFar = traitsSoFar + current.getName() + ", ";   //building the characteristic string
            current = current.getLeft();
            if (current.getLeft() == null) {    //current is pointing to an animal
                System.out.print("Is this animal a " + current.getName() + "? (Y/N) > ");
                String nextChoice = val.next();
                if (nextChoice.equalsIgnoreCase("Y")) {     //animal is correctly identified
                    System.out.println("Good.\n");
                    run();
                } else if (nextChoice.equalsIgnoreCase("N")) {      //animal does not currently exist in tree
                    current.getParent().setLeft(newChar);
                    insert(true);
                }
            } else {    //current is pointing to a characteristic
                execute();
            }
        } else if (choice.equalsIgnoreCase("N")) {
            traitsSoFar = traitsSoFar + "not " + current.getName() + ", ";      //building the characteristic string
            current = current.getRight();
            if (current.getLeft() == null) {    //current is pointing to an animal
                System.out.print("Is this animal a " + current.getName() + "? (Y/N) > ");
                String nextChoice = val.next();
                if (nextChoice.equalsIgnoreCase("Y")) {     //animal is correctly identifiec
                    System.out.println("Good.\n");
                    run();
                } else if (nextChoice.equalsIgnoreCase("N")) {      //animal does not currently exist in tree
                    current.getParent().setRight(newChar);
                    insert(false);
                }
            } else {    //current is pointing to a characteristic
                execute();
            }
        }
    }

    public void insert(boolean yes) {
        Scanner val = new Scanner(System.in);
        traitsSoFar = traitsSoFar.substring(0, traitsSoFar.length() - 2);       //formatting
        System.out.println("I don't know any " + traitsSoFar + " animals that aren't a " + current.getName() + ".");
        System.out.print("What is the new animal? > ");
        animal = val.next();

        System.out.print("What characteristic does a " + animal + " have that a " + current.getName() + " does not? > ");
        characteristic = val.next();
        System.out.println();

        newChar = new Node(characteristic);
        newAnimal = new Node(animal);

        newChar.setParent(current.getParent());

        if (yes) {
            current.getParent().setLeft(newChar);       //furry's left child is squeaky
        } else {
            current.getParent().setRight(newChar);      //furry's right child is aquatic
        }

        newChar.setLeft(newAnimal);                     //aquatic's left child is fish
        newAnimal.setParent(newChar);                   //fish's parent is aquatic

        newChar.setRight(current);                      //aquatic's right child is snake
        current.setParent(newChar);                     //snake's parent is aquatic

        current = root;                                 //moving to the top of the tree
        run();
    }
}
