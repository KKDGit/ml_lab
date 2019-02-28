# Question: Access the third value of key b from the dictionary.
#
# from pprint import pprint
#
# d = dict(a=list(range(1, 11)), b=list(range(11, 21)), c=list(range(21, 31)))
# Expected
# output:
#
# 13


from pprint import pprint

d = dict(a=list(range(1, 11)), b=list(range(11, 21)), c=list(range(21, 31)))
pprint(d)
print(d['b'][2])
