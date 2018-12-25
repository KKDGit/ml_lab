letters = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j"]
# Expected output:
#
# ['a', 'c', 'e', 'g', 'i']

print([l for i, l in enumerate(letters) if i % 2 == 0])
print(letters[::2])

