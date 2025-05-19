#Challenge can be found at: https://www.hackerrank.com/challenges/matrix-script/problem?isFullScreen=true
#import math
#import os
#import random
import re
#import sys
#Note: I found the some of the above imports unnecessary and slow down the program
# so, I decided to comment them out.

first_multiple_input = input().rstrip().split()

n = int(first_multiple_input[0])

m = int(first_multiple_input[1])

matrix = []

for _ in range(n):
    matrix_item = input()
    matrix.append(matrix_item)

#The below is all the code I developed to solve the challenge
s = ""

for i in range(m):
    for j in range(n):
        s += (matrix[j][i])


p = r'([a-zA-Z0-9])([^a-zA-Z0-9]+)([a-zA-Z0-9])'
matches = re.finditer(p, s)

for mp in matches:
    i = s.find(mp.group(0))
    s = s.replace(mp.group(0), (s[i]+" "+s[i+3]))

print(s)
