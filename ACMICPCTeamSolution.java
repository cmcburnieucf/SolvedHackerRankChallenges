//The Challenge can be found here: https://www.hackerrank.com/challenges/acm-icpc-team/problem?isFullScreen=false
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
     * Complete the 'acmTeam' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY topic as parameter.
     */
    //Note: Only the code in the acmTeam method is mine.
    public static List<Integer> acmTeam(List<String> topic) {
        List<Integer> res = new ArrayList<>();
        int num_members = topic.size(), 
            num_topics = (topic.get(0)).length(), 
            sum, best_sum = -1, best_teams = 0;
        
        int table[][] = new int[num_members][num_members];
        
        for(int i = 0; i < num_members; i++) {
            table[i][i] = -1;
        }
        
        for(int i = 0; i < num_members; i++) {
            for(int j = (num_members-1); j > i; j--) {
                if((table[i][j] != 0)) continue;
                else {
                    sum = 0;
                    for(int k = 0; k < num_topics; k++) {
                        if((topic.get(i).charAt(k) == '1') || 
                        (topic.get(j).charAt(k) == '1')) sum++;
                    }
                    
                    table[i][j] = table[j][i] = sum;
                    if(sum > best_sum) {
                        best_sum = sum;
                    }
                }
            }
        }
        
        for(int i = 0; i < num_members; i++) {
            for(int j = (num_members-1); j > i; j--) {
                if(table[i][j] == best_sum) best_teams++;
            }
        }
        
        res.add(0, best_sum);
        res.add(1, best_teams);
        
        return res;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<String> topic = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<Integer> result = Result.acmTeam(topic);

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
