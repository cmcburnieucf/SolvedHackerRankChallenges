//You can find the challenge here: https://www.hackerrank.com/challenges/magic-square-forming/problem?isFullScreen=true
#include <bits/stdc++.h>

using namespace std;

string ltrim(const string &);
string rtrim(const string &);
vector<string> split(const string &);

/*
 * Complete the 'formingMagicSquare' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts 2D_INTEGER_ARRAY s as parameter.
 */
//Note 1: Only the code within the formingMagicSquare function is mine.
//The rest of the code was written by the problem's creator.

/*Note 2: I do believe that my code is not efficient for forming all n x n magic squares 
(where n is a positive, non-zero integer).
However, because I wanted to contain my code within one function 
and I did not know the method to do this efficiently,
I decided to use a magic square, rotate it around and reflect it to generate 8 possible combinations
and determine the cost from there.
Credit to geeksforgeeks's article "Minimum changes needed to make a 3*3 matrix magic square"
and https://study.com/academy/lesson/how-to-solve-magic-squares.html
for showing me the connection between all 8 magic squares.
*/
int formingMagicSquare(vector<vector<int>> s) {
    int best_cost=0;
    int magicSquares[8][3][3] = {{{8,1,6},{3,5,7},{4,9,2}}, {{6,1,8},{7,5,3},{2,9,4}}};
    int costs[8] = {0,0,0,0,0,0,0,0};

    //generate all possible magic squares by:
    for(int k=2; k<8; k+=2) {
        //rotating counter-clockwise
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                magicSquares[k][(abs((j-2)%3))][i] = magicSquares[k-2][i][j];
            }
        }

        //reflecting the rotated matrix
        for(int i = 0; i < 3; i++) {
            magicSquares[k+1][i][0] = magicSquares[k][i][2];
            magicSquares[k+1][i][1] = magicSquares[k][i][1];
            magicSquares[k+1][i][2] = magicSquares[k][i][0];
        }
    }
    
    //calculate the costs of converting given matrix to each magic square
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            for(int k = 0; k < 8; k++) {
                costs[k] += abs(magicSquares[k][i][j] - s[i][j]);
            }
        }
    }

    best_cost = costs[0];
    for(int i = 1; i < 8; i++) {
        if(costs[i] < best_cost) best_cost = costs[i];
    }

    return best_cost;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    vector<vector<int>> s(3);

    for (int i = 0; i < 3; i++) {
        s[i].resize(3);

        string s_row_temp_temp;
        getline(cin, s_row_temp_temp);

        vector<string> s_row_temp = split(rtrim(s_row_temp_temp));

        for (int j = 0; j < 3; j++) {
            int s_row_item = stoi(s_row_temp[j]);

            s[i][j] = s_row_item;
        }
    }

    int result = formingMagicSquare(s);

    fout << result << "\n";

    fout.close();

    return 0;
}

string ltrim(const string &str) {
    string s(str);

    s.erase(
        s.begin(),
        find_if(s.begin(), s.end(), not1(ptr_fun<int, int>(isspace)))
    );

    return s;
}

string rtrim(const string &str) {
    string s(str);

    s.erase(
        find_if(s.rbegin(), s.rend(), not1(ptr_fun<int, int>(isspace))).base(),
        s.end()
    );

    return s;
}

vector<string> split(const string &str) {
    vector<string> tokens;

    string::size_type start = 0;
    string::size_type end = 0;

    while ((end = str.find(" ", start)) != string::npos) {
        tokens.push_back(str.substr(start, end - start));

        start = end + 1;
    }

    tokens.push_back(str.substr(start));

    return tokens;
}
