//You can find the challenge here: https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem?isFullScreen=false
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
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */
    //Note: Only the code within the method climbingLeaderboard() is mine. The rest was provided by the problem's creator.
    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        int n_p = player.size(), n_r = ranked.size(), n_rr, 
        idx=-1, j=0, 
        pscore, num, ranked_num;
        
        List<Integer> playerRanked = new ArrayList<>(n_p);
        List<Integer> tempList = new ArrayList<>();
        int rankedReversed[];
        
        tempList.add(j, ranked.get(n_r-1));
        j++;
        for(int i = n_r-2; i >= 0; i--) {
            num = tempList.get(j-1);
            ranked_num = ranked.get(i);
            if(num != ranked_num) {
                tempList.add(ranked_num);
                j++;
            }
        }
        
        rankedReversed = tempList.stream().mapToInt(Integer::intValue).toArray();
        n_rr = rankedReversed.length;
        
        for(int k = 0; k < n_p; k++) {
            pscore = player.get(k);
            playerRanked.add(k, 0);
            if(pscore >= (ranked_num = ranked.get(0))) playerRanked.set(k, 1);
            else if(pscore == (ranked_num = ranked.get(n_r-1))) playerRanked.set(k, n_rr);
            else if(pscore < (ranked_num = ranked.get(n_r-1))) playerRanked.set(k, (num=n_rr+1));
            else {
                idx = Arrays.binarySearch(rankedReversed, pscore);
                if(idx > -1) {
                    playerRanked.set(k, (num=n_rr-idx));
                } else {
                    playerRanked.set(k, (num=((n_rr-(Math.abs(idx+1)+1))+2)));
                }
            }   
        }
        
        return playerRanked;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

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
