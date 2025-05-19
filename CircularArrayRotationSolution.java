//Challenge can be found at: https://www.hackerrank.com/challenges/circular-array-rotation/problem?isFullScreen=true
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

    /*
     * Complete the 'circularArrayRotation' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER k
     *  3. INTEGER_ARRAY queries
     */
    //Below is the code I wrote to solve the problem. I only wrote what is inside this method.
    public static List<Integer> circularArrayRotation(List<Integer> a, int k, List<Integer> queries) {
        int pos0, posi, alen = a.size(), qlen = queries.size();
        List<Integer> res = new ArrayList<>();
        
        //determine where the first number will be after k shifts:
        //k (num shifts) % alen (length of list)
        pos0 = k % alen;

        //for every query, find the hypothetical position of one the list's elements
        for(int i = 0; i < qlen; i++) {
            if(pos0 != 0)
                posi = ((queries.get(i) - pos0) + alen) % alen;
            //I found through calculations that if the number of shifts you do
            //is equal to the number of elements in a list
            //the rotated list will be the same as the original list
            else
                posi = queries.get(i);
            res.add(a.get(posi));
        }
        
        return res;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        int q = Integer.parseInt(firstMultipleInput[2]);

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> queries = IntStream.range(0, q).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.circularArrayRotation(a, k, queries);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
