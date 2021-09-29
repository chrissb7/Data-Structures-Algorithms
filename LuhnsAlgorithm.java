/* Also known as the “mod 10” algorithm, a checksum formula used to validate credit card numbers

From the rightmost digit, which is the check digit, and moving left, double the value of every second digit. 

If the result of this doubling operation is greater than 9 (e.g., 8 × 2 = 16), then add the digits of the product (e.g., 16: 1 + 6 = 7, 18: 1 + 8 = 9) 
or alternatively subtract 9 from the product (e.g., 16: 16 - 9 = 7, 18: 18 - 9 = 9).

Take the sum of all the digits.

If the total modulo 10 is equal to 0 (if the total ends in zero) then the number is valid according to the Luhn formula; else it is not valid. */





public class LuhnsAlgorithm {

    public static void main(String[] args) {

        String input = "5531006517734657";
        System.out.println(validateCreditCardNumber(input));

    }


    public static boolean validateCreditCardNumber(String input)
    {
        int[] creditCardInt = new int[input.length()];

        for (int i=0; i<input.length(); i++)
        {
            creditCardInt[i] = Integer.parseInt(input.substring(i, i+1));
        }

        for (int i = creditCardInt.length-2; i >= 0; i = i - 2) {
            int tempValue = creditCardInt[i];
            tempValue = tempValue * 2;
            if (tempValue > 9)
            {
                tempValue = tempValue % 10+1;
            }
            creditCardInt[i] = tempValue;
        }





    }
}
