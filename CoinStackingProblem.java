/*
The task is to take in the radius of a coin, the thickness of a coin, and a target distance for how much the coin tower should lean over the edge of a table​

You then output the minimum height of that tower of coins

This is known as the block stacking problem (https://en.wikipedia.org/wiki/Block-stacking_problem)


*/

public class CoinStackingProblem {
    public static void main(String args[]) {

        blockStack(12.6, 1.5, 18.4);


    }


    public static void blockStack(double radius, double height, double coinStackDistance)
    {
        final double diameter = radius*2;
        double sum=0;
        int coin=1;
        int dividend=2;


        while (sum<=coinStackDistance){

            //sum+=diameter*(1/((float)dividend*(float)coin));
            sum+=diameter*(1/((float)dividend*(float)coin));
            coin++;


        }
        

        long coinHeight = (long) (coin*height);
        System.out.println(coinHeight);

    }


}
