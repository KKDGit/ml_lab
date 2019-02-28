# Question: Filter the dictionary by removing all items with a value of greater than 1.
#
# d = {"a": 1, "b": 2, "c": 3}
# Expected output:
#
# {'a': 1}

d = {"a": 1, "b": 2, "c": 3}
d_filt = {}

for key,value in d.items():
    if value <= 1:
       d_filt[key] = value

print(d_filt)

d_filt1 = dict((key, value) for key, value in d.items() if value <= 1)
print(d_filt1)
