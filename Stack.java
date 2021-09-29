import java.util.Scanner;

/* Manipulate a stack according to the given push and pop commands and then output the biggest number that is left on the stack. If a pop command is issued for an empty stack then nothing should happen.​


Sample Input

5
PUSH 4
PUSH 8
POP
PUSH 6
PUSH 2

Expected Output
6 */

public class Stack {

    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);

        int s = Integer.parseInt(scan.nextLine());

        int input = s;
        Stack theStack = new Stack(input);


        while (s > 0) {

            String n = scan.nextLine();

            if(n.contains(" ")){

                String[] parts=n.split(" ");
                String part1 = parts[0];
                String part2 = parts[1];

                int i = Integer.parseInt(part2);
                theStack.push(i);
            }
            else if(!n.contains(" ")&&!theStack.isEmpty()){
                theStack.pop();
            }

            s--;
        }

        if (!theStack.isEmpty()){
            int high = theStack.pop();
            while(!theStack.isEmpty()){
                int maybeHigher = theStack.pop();
                if (maybeHigher>high){
                    high = maybeHigher;
                }
                else{
                    continue;
                }
            }
            System.out.println(high);
        }
        else{
            System.out.println("empty");
        }
    }

    public static class Stack {
        //Set ints below to set up for methods
        private int maxSize; // size of stack array
        private int[] stackArray;
        private int top; // top of stack

        public Stack(int s) { // constructor
            maxSize = s; // set array size
            stackArray = new int[maxSize]; // create array
            top = -1; // no items yet
        }


        public void push(int j) { // put item on top of stack
            top++;
            stackArray[top] = j; // increment top, insert item
        }

        public int pop() { // take item from top of stack
            return stackArray[top--]; //access item, decrement top
        }

        public boolean isEmpty() { // true if stack is empty
            return (top == -1);
        }

    }

}
