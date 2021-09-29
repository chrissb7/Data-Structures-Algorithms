import java.util.Arrays;
import java.util.Scanner;

/*
In this task the input is two numbers, followed by a series of strings. Those strings must be sorted and then one particular string must be outputted. 
The first number denotes the quantity of strings to follow. The second number indicates which string should be outputted following the sort. 

For example, if it's a 3, then output the string that falls in third place. 
If it's a 5 then output the string that falls in fifth place.

The sorting that should be applied is as follows: sort words by their length, with the shortest words coming first. 
If two words have the same length, then sort them in reverse alphabetical order (i.e. with "zoo" coming ahead of "ape").
*/


public class sortedStringArray  {

    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);
        int num = Integer.parseInt(scan.nextLine());
        int num2 = Integer.parseInt(scan.nextLine());


        String[] array = new String[num];

        for (int i = 0; i < array.length; i++) {
            String word = scan.nextLine();
            array[i] = word;
        }


        int min;
        for (int i = 0; i < array.length - 1; i++) {

            min = i;

            for (int j = i + 1; j < array.length; j++) {

                if (array[j].length() < array[min].length()) {
                    min = j;
                } else if (array[min].length() == array[j].length()) {
                    if (isReverseAlphabetical(array[j], array[min])) {
                        min = j;
                    }
                }

            }
            String temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
        System.out.println(array[num2]);

    }

    public static boolean isReverseAlphabetical(String a, String b) {

        for (int i = 0; i < a.length(); i++) {

            if (Character.getNumericValue(a.charAt(i)) > Character.getNumericValue(b.charAt(i))) {

                return true;
            } else if (Character.getNumericValue(a.charAt(i)) < Character.getNumericValue(b.charAt(i))) {

                return false;
            }
        }
        return true;
    }


}
