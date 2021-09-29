import java.util.*;
import java.math.*;
import java.math.BigInteger;
import java.lang.Object.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class hashTable{

    public static void main (String[] args){
        Scanner myscanner = new Scanner(System.in);
        int items = myscanner.nextInt()-1;
        myscanner.nextLine();
        String[] contents = new String[items];
        for(int i=0;i<items;i++){
            contents[i]=myscanner.nextLine();
        }
        String hash = myscanner.nextLine();
        int size=99991;
        Solution mysolution = new Solution();
        String[] hashtable=mysolution.fill(size, contents);
        HashTable mytable = new HashTable(hashtable);

        Solution mysolution2 = new Solution(); //prevents cheating by using memory
        for(int i=0;i<items;i++){
            int rand = (int)(Math.random()*items);
            String temp = contents[i];
            contents[i]=contents[rand];
            contents[rand]=temp;
        }
        int total=0;
        for(int i=0;i<items;i++){
            int slot = mysolution2.find(size, mytable, contents[i]);
            if(!hashtable[slot].equals(contents[i])){
                System.out.println("error!");
            }
        }
        long status=mytable.gettotal();
        System.out.println("Collisions: " + status);
        if(status>0){
            try{
                System.out.println("Here is your receipt: "+sha256(hash+mytable.gettotal()));
            }catch(NoSuchAlgorithmException e){};
        }
    }

    public static String sha256(String input) throws NoSuchAlgorithmException {
        byte[] in = hexStringToByteArray(input);
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(in);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        if(len%2==1){
            s=s+"@";
            len++;
        }
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}


class HashTable{

    private String[] hashTable;
    private long total=0;

    public HashTable(String[] input){
        hashTable = input;
    }

    public boolean check(int slot, String check){
        if(hashTable[slot].equals(check)){
            return true;
        }else{
            total++;
            return false;
        }
    }

    public long gettotal(){
        return total;
    }
}



class Solution{

    public int find(int size, HashTable mytable, String word){

        int total = 0;
        int moddy = size; // same as hashTable size

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);

            BigInteger letter1 =
                    new BigInteger(String.valueOf(getLetterValue(letter)));

            BigInteger powerProduct =
                    new BigInteger(String.valueOf((long) Math.pow(27, i)));


            BigInteger a = letter1.multiply(powerProduct);

            total += a.intValue();
        }

        int hashIndex = (int) (total % moddy);


        mytable.check(hashIndex, word);

        int collision=(int) mytable.gettotal();

        return collision;
    }



    public String[] fill(int size, String[] array){


        String[] hashTable = new String[size]; // create the table



        for (int j=0; j<array.length ; j++ ) {

            String input = array[j];

            int total = 0;
            int moddy = size;

            for (int i=0; i<input.length(); i++){
                char letter = input.charAt(i);

                BigInteger letter1 =
                        new BigInteger(String.valueOf(getLetterValue(letter)));

                BigInteger powerProduct =
                        new BigInteger(String.valueOf( (long) Math.pow( 27, i ) ));


                BigInteger a = letter1.multiply(powerProduct);

                total += a.intValue();
            } // end inner loop

            int hashIndex = (int) (total % moddy);


            if ( hashTable[hashIndex] != null ){
                while ( hashTable[hashIndex] != null){
                    hashIndex++;
                }
                hashTable[hashIndex] = input;
            }
            else {
                hashTable[hashIndex] = input;
            }
        }

        int c = 0;
        while ( c<hashTable.length ) {
            if ( hashTable[c] == null ){
                hashTable[c] = "";
                c++;
            } else c++;
        }

        return hashTable;
    }

    static int getLetterValue(char toFind){
        String alpha = "abcdefghijklmonpqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int pointer = 0;
        while (true){
            if ( alpha.charAt(pointer) == toFind ) {
                return pointer + 1;
            } else {
                pointer++;
            }
        }
    }

}
