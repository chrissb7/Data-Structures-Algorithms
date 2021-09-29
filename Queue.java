import java.util.ArrayList;
import java.util.Scanner;
/*

Manipulate a queue according to the given insert and remove commands and then output the string that is in the middle of the queue. 
If there is an even number of strings in the queue, thus two middle strings, output the one which is nearest the front. 
If a remove command is issued for an empty queue then nothing should happen.


Sample Input

INSERT this
INSERT is
INSERT how
INSERT to
REMOVE
INSERT do
REMOVE
INSERT it


Example Output

to


*/

public class Queue{

    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);


        Queue thisQueue = new Queue(20);

        while (scan.hasNextLine()) {

            String[] command = scan.nextLine().split(" ");
            switch (command[0]) {
                case "INSERT":
                    thisQueue.insert(command[1]);
                    break;
                case "REMOVE":
                    thisQueue.remove();
                    break;
            }


        }

        if(thisQueue.isEmpty()){
            System.out.println("empty");
        }

        else

        {System.out.println(thisQueue.getMiddleElement());
        }
    }

public static class Queue{

        private int maxSize;
        private String[] queueArray;
        private int front;
        private int rear;
        private int nItems;

        public Queue(int s){
            maxSize=s;
            queueArray=new String[maxSize];
            front=0;
            rear=-1;
            nItems=0;
        }

        public boolean insert(String item){
            rear++;
            nItems++;
            queueArray [rear] = item;
            return true;
        }

        public String remove(){
                if(nItems==0){
                    return null;
                }
            String element = queueArray[rear];
                front++;
                nItems--;
                return element;
        }

        public boolean isEmpty(){
            return (nItems==maxSize);
        }

        public int size(){
            return nItems;
        }

        public String getMiddleElement(){
            if(isEmpty()){
                return "empty";
            }

            int middleIndex = (front + rear)/2;
            return queueArray[middleIndex];
        }
}


}

