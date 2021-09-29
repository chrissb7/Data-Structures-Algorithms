import java.util.Scanner;
import java.util.Random;
/*
Two integers are coprime if the only positive integer that divides into both of them is 1. In other words, the greatest common divisor of two coprime numbers is 1.

If two numbers x and y are selected randomly, the odds that they will be coprime with e ach other is 61% (6 divided by PI^2). 
But what if we put some restrictions on the two numbers? 
For example, if x is a randomly selected number that is divisible by 7 and y is a randomly selected number that is divisible by 13? 
In this case, the odds that they are coprime is 49%.​

In this question you are given a divisor of the first number and a divisor of the second number. 
You must output an integer from 0 to 100, which is the percentage probability that the two randomly selected numbers are coprime. 
*/

public class CoPrime {
    public static void main(String args[])  {

        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();

        Random rd = new Random();

        int coPrimeCount = 0;

        for (int i = 0; i <= 10000000; i++) {

            int a = rd.nextInt(10000) * x;
            int b = rd.nextInt(10000) * y;

            if (gcd(a, b) == 1) coPrimeCount++;

        }
        double ratio = (double) coPrimeCount / 10000000;
        System.out.println(Math.round(ratio * 100));
    }

    static int gcd(int a, int b) {
        {
            if (b != 0) {
                return gcd(b,
                        a % b);
            } else {
                return a;
            }
        }
    }
}
