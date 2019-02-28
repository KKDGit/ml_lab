# Exercise for reference:
#
# d = {"Name": "John", "Surname": "Smith"}
# print(d["Smith"])
# Answer:
#
# There is no key Smith  in the dictionary. Smith  is a value. You want to use Surname  if you want to access Smith :
#
# print(d["Surname"])
# Explanation:
#
# A KeyError always means Python could not find a key with the name shown next to KeyError (e.g. Smith ).
d1 = {1: "John", 2: "Smith"}
print(d1[2])

d2 = dict(a='John', b='SS')
print(d2['b'])

d = {"Name": "John", "Surname": "Smith"}
print(d["Surname"])
print(d["Smith"])

