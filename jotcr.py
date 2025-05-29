#!/bin/python3

import math
import os
import random
import re
import sys

# You can find the challenge here: 
# https://www.hackerrank.com/challenges/jumping-on-the-clouds-revisited/problem?isFullScreen=true
# Only the code inside the below function is my work
def jumpingOnClouds(c, k):
    tot = 100
    n = len(c)
    
    i = k % n
    while i != 0:
        if(c[i] == 1):
            tot = tot - 3
        else:
            tot = tot - 1
        
        i = (i + k) % n
        
    if(c[i] == 1):
        tot = tot - 3
    else:
        tot = tot - 1
    
    return tot

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    nk = input().split()

    n = int(nk[0])

    k = int(nk[1])

    c = list(map(int, input().rstrip().split()))

    result = jumpingOnClouds(c, k)

    fptr.write(str(result) + '\n')

    fptr.close()
