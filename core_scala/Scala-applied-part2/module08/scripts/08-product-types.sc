(1, '2', "three") == (1, '2', "three")
(1, 'a', "three") == (1, '2', "three")

case class Triplet1(i: Int, ch: Char, str: String)

Triplet1(1, '2', "three") == Triplet1(1, '2', "three")
Triplet1(1, '2', "three") == Triplet1(2, '2', "three")
Triplet1(1, '2', "three") == (1, '2', "three")

case class Triplet2(i: Int, ch: Char, str: String)

Triplet2(1, '2', "three") == Triplet2(1, '2', "three")
Triplet2(1, '2', "three") == Triplet2(2, '2', "three")
Triplet1(1, '2', "three") == Triplet2(1, '2', "three")
Triplet2(1, '2', "three") == (1, '2', "three")


val triplet2 = Triplet2(1, '2', "three")

triplet2.productIterator.toList
triplet2.productArity
triplet2.productElement(2)
triplet2.productPrefix

val tup3 = (1, '2', "three")

tup3.productIterator.toList
tup3.productArity
tup3.productElement(2)
tup3.productPrefix
