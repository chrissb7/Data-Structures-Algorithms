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
