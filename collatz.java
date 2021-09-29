/*
Let's call "Collatz length" the number of steps needed to reduce a number to 1. 
The Collatz length of 1 is zero, since no steps are needed. Whereas the Collatz length of 4 is two, since two steps are needed: 4 -> 2-> 1. 

Every number will have a Collatz length. The goal is to sort a set of numbers in a given range by their Collatz sequence, and then output the xth number in the sorted list. 
If two numbers have the same Collatz length, then the smaller number should come first.

Example Input

4

9

3


Example Output

5 (because it is the 3rd element in the list)

Here is the list:

4 (Collatz length of 2)

8 (Collatz length of 3)

5 (Collatz length of 5)

6 (Collatz length of 8)

7 (Collatz length of 16)

9 (Collatz length of 19)

*/


import java.util.Scanner;

class collatz{
    public static void main(String[] args){

        String sampleInput = "45\n55\n3";
        Scanner scan = new Scanner(sampleInput);

        long lower = scan.nextLong();
        long upper = scan.nextLong();
        int index = scan.nextInt();

        int arraySize = (int)(upper-lower+1);

        C[] array = new C[arraySize];

        int arrayPos = 0; // keep track of the array postion for inserting

        while (lower <= upper){
            long comparable = lower;
            long count = 0;
            // comparable is changing in this loop
            while (comparable != 1){
                if (comparable % 2 == 0){ // if it's even
                    comparable = comparable / 2; // divide by 2
                    count++; // one more step is added
                }
                else{ // if it's odd
                    comparable = comparable * 3 + 1;
                    count++; // one more step is added
                }
            } // end inner while

            C c = new C(lower, count);
            array[arrayPos] = c;

            arrayPos++; // move to next postion
            lower++;
        } // end outer while


        int left = 0;
        int right = array.length - 1;

        RecqSort(array, left, right);
        bubbleSort(array);

//        for (C n : array) System.out.println(n);

        System.out.println(array[index].count);

        // System.out.println(array[index - 1]);
    } // end main

    static void RecqSort(C arr[], int left, int right){
        if (left < right){
            /* pi is parting index, arr[pi] is now at right place */
            int p = (int) Createpart(arr, left, right);

            RecqSort(arr, left, p - 1);
            RecqSort(arr, p + 1, right);
        }
    }

    static long Createpart(C arr[], int left, int right){
        // pivot (Element to be placed at right position)
        long pivot = arr[right].count;

        int i = (left - 1);  // Index of smaller element and indicates the right position of pivot found so far

        for (int j = left; j <= right - 1; j++){
            // If current element is smaller than the pivot
            if (arr[j].count < pivot){
                i++;    // increment index of smaller element

                C temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        C temp = arr[i+1];
        arr[i+1] = arr[right];
        arr[right] = temp;

        return (i + 1);
    }
    public static C[] bubbleSort(C[] array){
        for (int j=0; j<array.length; j++){
            int i = 0;
            while (i < array.length-1){
                if (array[i].count  == array[i+1].count){
                    if (array[i].number > array[i+1].number){
                        C temp = array[i+1];
                        array[i+1] = array[i];
                        array[i] = temp;
                    }
                }
                i++;

            }
        }
        return array;

    }
}
class C{
    public long number;
    public long count;

    C(long n, long c){
        number = n;
        count = c;
    }

}


