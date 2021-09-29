/* The goal is to convert a character String into a binary string Huffman encoding. 


As we've seen, the way a Huffman tree is built is not deterministic, because when you have multiple trees with the same lowest weighting, 
you can choose any two of those to connect together, and you can put either one on the left or right. 

So let's make it deterministic by saying - where multiple trees have equal frequency, always connect the two trees containing the two lowest value ASCII characters, 
and always put the lowest of those two on the left. 

For instance, every ASCII character is associated with a number, so every tree will have a lowest value ASCII character in it - (e.g. capital A is the lowest ASCII letter). 
Always connect the two trees that have the lowest characters anywhere inside them, with the lowest on the left. Now everybody should end up with the same trees and the same coding. 
This as simple as saying: take the Priority Queue and as well as sorting on frequency, sort on lowest character as well. 
When two are popped from the Priority Queue, put the first to be popped on the left.


Example Input

hello

Example Output

1111100010 (try it on paper for yourself) */


import java.util.*;

public class BinaryTree {
    public static void main(String args[]) throws Exception {
        Scanner in = new Scanner(System.in);
        String sentence = in .nextLine();

        int[] array = new int[256]; //an array to store all the frequencies

        for (int i = 0; i < sentence.length(); i++) { //go through the sentence
            array[(int) sentence.charAt(i)]++; //increment the appropriate frequencies
        }

        PriorityQueue < Tree > PQ = new PriorityQueue < Tree > (); //make a priority queue to hold the forest of trees

        for (int i = 0; i < array.length; i++) { //go through frequency array

            if (array[i] > 0) { //print out non-zero frequencies - cast to a char

                //Get the char value at the array position e.g. int 97 is char a when converted
                char forTree = (char) i;
                //For tree attribute frequency
                int frequency = array[i];


              /*Creates a new Tree object every loop. Each tree Does not need a unique name as each position of [i]
              points to a new memory location.
               */
                Tree myTree = new Tree();


                //Each tree must have a node and a new node is completed every loop.
                Node newNode = new Node();

                /*New node must have a letter pointing to it. Leftchild & Rightchild need nothing until next section of
                algorithm*/

                newNode.letter = forTree;
                newNode.smallestLetter =forTree;

                /*Tree frequency attribute is set by having the having the mytree object equal to
                the frequency(defined above)*/

                myTree.frequency = frequency;

                /*The root of the myTree points to a new node. Remember, this new node that is created everytime the
                loop goes around and then the node is referenced by the new tree created everytime. */

                myTree.root = newNode;

                /*Add the tree in to the priority queue.
                To reiterate in 3 steps:
                    1.New tree and node created each loop
                    2.New node inherits values of the casted char
                    3.New tree object node attribute points to newly created node and the frequency attribute is taken
                    from the int at array[i]
                */

                //Add the tree into the priority queue

                PQ.add(myTree);
            }
        }



        while (PQ.size() > 1) { //while there are two or more Trees left in the forest

            Tree combo = new Tree();

            Tree firstTree = PQ.poll();
            Tree secondTree = PQ.poll();

            Node myNode = new Node();
            combo.root = myNode;
            combo.frequency= firstTree.frequency + secondTree.frequency;

            myNode.smallestLetter=firstTree.root.smallestLetter;
            myNode.leftChild = firstTree.root;
            myNode.rightChild = secondTree.root;

            PQ.add(combo);

            //FILL THIS IN:
            //IMPLEMENT THE HUFFMAN ALGORITHM
            //when you're making the new combined tree, don't forget to create a new root node (or else you'll get a null pointer exception)
            //make sure to set the smallestLetter of the new combined tree to what it should be
            //the first tree to come out of PQ goes on the left, the second on the right

        }

        Tree HuffmanTree = PQ.poll(); //now there's only one tree left - get its codes

        for(int j=0; j<sentence.length(); j++)
        {
            System.out.print(HuffmanTree.getCode(sentence.charAt(j)));
        }
        //FILL THIS IN:

        //get all the codes for the letters and print them out
        //call the getCode() method on the HuffmanTree Tree object for each letter in the sentence


    }
}


class Node {

    public char letter = '@'; //stores letter
    public char smallestLetter = '@';  //a nice idea is to track the smallest letter in the tree in a special variable like this

    public Node leftChild; // this node's left child
    public Node rightChild; // this node's right child

} // end class Node


class Tree implements Comparable < Tree > {
    public Node root; // first node of tree
    public int frequency = 0;

    public Tree() // constructor
    {
        root = null;
    } // no nodes in tree yet

    //the PriorityQueue needs to be able to somehow rank the objects in it
    //thus, the objects in the PriorityQueue must implement an interface called Comparable
    //the interface requires you to write a compareTo() method so here it is:

    public int compareTo(Tree object) {
        if (frequency - object.frequency > 0) { //compare the cumulative frequencies of the tree
            return 1;
        } else if (frequency - object.frequency < 0) {
            return -1; //return 1 or -1 depending on whether these frequencies are bigger or smaller
        } else {
            // Sort based on letters
            char a = this.root.smallestLetter;
            char b = object.root.smallestLetter;

            if (a > b) {
                return 1;
            } else if (a < b) {
                return -1;
            }
            return 0;
        }
    }

    String path = "error"; //this variable will track the path to the letter we're looking for

    public String getCode(char letter) { //we want the code for this letter

        return this._getCode(letter, this.root, ""); //return the path that results
    }

    private String _getCode(char letter, Node current, String path) {
        if (current == null) {
            return null;
        }
        if (current.letter == letter) {
            return path;
        }

        String leftPath = this._getCode(letter, current.leftChild, path + "0");
        if (leftPath != null) {
            return leftPath;
        }

        String rightPath = this._getCode(letter, current.rightChild, path + "1");
        return rightPath;
    }

} // end class Tree

