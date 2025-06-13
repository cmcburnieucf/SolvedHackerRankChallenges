#!/bin/python3

import math
import os
import random
import re
import sys

# To find more information on the challenge, look here: https://www.hackerrank.com/challenges/permutation-equation/problem?isFullScreen=false

#
# Complete the 'permutationEquation' function below.
#
# The function is expected to return an INTEGER_ARRAY.
# The function accepts INTEGER_ARRAY p as parameter.
#

# Only the code within the below function is my code.
def permutationEquation(p):
    p_map = {}
    res = []
    l = len(p)+1
    
    for index, num in enumerate(p):
        p_map[num] = (index+1)
    
    for i in range(1, l):
        res.append(p_map.get(p_map.get(i)))
    
    return res

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input().strip())

    p = list(map(int, input().rstrip().split()))

    result = permutationEquation(p)

    fptr.write('\n'.join(map(str, result)))
    fptr.write('\n')

    fptr.close()
