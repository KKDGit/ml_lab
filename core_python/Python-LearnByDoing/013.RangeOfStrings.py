# Exercise 13 - Ranges of Strings
# Section 1, Lecture 30
#
# Question: Complete the script so it generates the expected output using my_range  as
# input data. Please note that the items of the expected list output are all strings.
#
# my_range = range(1, 21)
#  Expected output:
#
# ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20']

my_range = range(1, 21)
my_str_list = [str(x) for x in my_range]
print(my_str_list)

print(list(map(str, [x for x in my_range])))
