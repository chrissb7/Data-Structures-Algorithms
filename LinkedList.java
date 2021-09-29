import java.util.*;

public class LinkedList {
    public static void main(String args[]) throws Exception {

        String inputString = "4\n345\n856\n853\n173\n0 -1 1\n1 0 2\n2 1 3\n3 2 -1";
        Scanner myscanner = new Scanner(inputString);
//        Scanner myscanner = new Scanner(System.in);
        int num = Integer.parseInt(myscanner.nextLine());
        Link[] array = new Link[num];
        for (int i = 0; i < num; i++) {
            array[i] = new Link(Integer.parseInt(myscanner.nextLine()));
        }
        while (myscanner.hasNext()) {
            int select = myscanner.nextInt();     //0 current
            int previous = myscanner.nextInt();  //-1 previous
            int next = myscanner.nextInt();     // 1 next
            if (previous != -1) {
                array[select].previous = array[previous];
            }
            if (next != -1) {
                array[select].next = array[next];
            }
        }
        LinkedList mylist = new LinkedList();
        if (num > 0) {
            mylist.first = array[0];
            mylist.last = array[num - 1];
        }

        System.out.println(search(mylist));

    }

    //don't edit the code above, only edit the search method below
    //int highest = mylist.first.data;
    public static int search(LinkedList mylist) {
        /* return the highest value held in any of the links, 0 if defective or empty*/
        Link select = mylist.first;
        Link previous = null;
        int max=mylist.first.data;


        if (mylist.isEmpty()){
            return 0;
        }

        else {
            while (select.next != null) {
                if (select.previous != previous) {
                    return 0;
                }

                else {
                    if (max < select.next.data) {//If the data in max is less than the data in the first node
                        max = select.next.data;
                    } // Replace max.
                    previous = select;
                    select = select.next;

                }
            }
        }
        //don't edit the code below, only edit the search method above
        return max;
    }

    ////////////////////////////////////////////////////////////////////////////
    static class Link {
        public int data;
        public Link next;
        public Link previous;

        public Link(int input) {
            data = input;
            next = null;
            previous = null;
        }
    }

    /////////////////////////////////////////////////////////////////////////////
    static class LinkedList {
        public Link first;
        public Link last;

        public LinkedList() {
            first = null;
            last = null;
        }

        public boolean isEmpty() {
            return (first == null);
        }

        public void insertHead(Link insert) {
            if (isEmpty()) {
                first = insert;
                last = insert;
            } else {
                first.previous = insert;
                insert.next = first;
                first = insert;
            }
        }
    }
}
