import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
    // You can find more about the problem here: https://www.hackerrank.com/challenges/append-and-delete/problem
    /*
     * Complete the 'appendAndDelete' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. STRING t
     *  3. INTEGER k
     */

   //Note: Only the code written in the method appendAndDelete is my code.

    public static String appendAndDelete(String s, String t, int k) {
        int sum = 0, i = 0, 
        slen = s.length(), tlen = t.length();
        
      /*if a string can be cleared 
        and copied into the target string
        and the number of operations is
        still than k operations,
        the remainder would be filler deletions
        of an empty string
        can be used if two identical strings 
        need k (> 0) operations*/
        
        if((slen+tlen) <= k) 
            return "Yes";
        
      /*if strings are identical 
        and we have already checked
        that one string cannot be 
        cleared and copied into another*/
        if(s.compareTo(t) == 0) {
            if(k % 2 == 0) return "Yes";
            else return "No";
        }
        
        //assuming the strings are not identical
        while((i < slen) && (i < tlen) 
        && (s.charAt(i) == t.charAt(i))) i++;
        
        System.out.println("After While Loop: ");
        System.out.println("i= "+i+", sum= "+sum);
        
        sum += (slen-i);
        System.out.println("sum= "+sum);
        sum += (tlen-i);
        System.out.println("sum= "+sum);
        
      /*only if s was deleted entirely,
        can you use the filler deletions 
        on an empty string*/
        if((slen == (i+1)) && (sum <= k)) return "Yes";
      
      /*due to an observation when testing the solution
        the remaining moves (additions/deletions)
        must be even so that if the user wants to remove
        and add again over and over until the number of moves
        reaches k, they can.*/
        if((k > sum) && ((k-sum) % 2 == 0)) return "Yes";
      
        //otherwise, it has to be exact.
        if(sum == k) return "Yes";
        
        return "No";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String t = bufferedReader.readLine();

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.appendAndDelete(s, t, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
