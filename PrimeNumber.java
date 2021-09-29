import java.util.Scanner;

// Check if inputted number is a prime number

public class PrimeNumber {

    public static void main(String args[]){

        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        int num2 = scan.nextInt();


        int prime=0;
        if(num2>num)
        {
            for (int i= num; i<=num2; i++)
            {

                    if(isPrime(i)){
                        prime++;
                    }


            }
            System.out.println(prime);
        }

        int prime2 = 0;
        if (num>num2)

        {
            for (int i= num2; i<=num; i++)
            {

                    if(isPrime(i)){
                        prime2++;
                    }


            }
            System.out.println(prime2);
        }

    }

    private static boolean isPrime(int x) {
        // This is a guard statement that will protect against unwanted value(s)
        if(x <= 1){
            return false;
        } else {
            for(int i = 2; i <= x/2; i++){
                if(x % i == 0){
                    return false;
                }
            }
        }
        //The loop has finished. Every divisible number (i in if statement) has been checked. Therefore the int x is a prime number
        return true;
    }
}
