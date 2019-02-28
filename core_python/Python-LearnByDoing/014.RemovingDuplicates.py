# Exercise 14 - Removing Duplicates
# Section 1, Lecture 32
#
# Question: Complete the script so that it removes duplicate items from list a .
#
# a = ["1", 1, "1", 2]
# Expected output:
#
#   ['1', 2, 1]

my_set = set()
a = ["1", 1, "1", 2]

for x in a:
    my_set.add(x)

print(my_set)

print(list(set(a)))

# The drawback here is that the original order of the items is lost.
# If you need to preserve the order you may want to use the solution in Answer 2 below.
#
# Answer 2:

from collections import OrderedDict

a = ["1", 1, "1", 2]
print(OrderedDict.fromkeys(a))
b = list(OrderedDict.fromkeys(a))
print(b)

a = ["1", 1, "1", 2]
b = []
for i in a:
    if i not in b:
        b.append(i)
print(b)
