 val evens = List(2,4,6,8,10,12)

 evens(0)
 evens(1)
 evens.head
 evens.tail
 evens

 val my_list1 = List("a",2,true)
 val my_list2 = List(List(1,2,3),List(4,5,6))
 val my_list3 = List(1,2,3.0)
 val my_list4 = List(("a",1),("b",2),("c",3))

 val my_list = List(3,6,1,7,10)

 my_list.sorted
 //my_list2.sorted
 my_list.size
 my_list.max
 my_list.min
 my_list.sum
 my_list.product

 val x = List(1,2,3,4)
 x.drop(2)
 x.takeRight(3)


////LISTS
//// Lists are an immutable sequence of elements (singly linked list)
//
//// Basic List of Numbers
//scala> val evens = List(2,4,6,8,10,12)
//evens: List[Int] = List(2, 4, 6, 8, 10, 12)
//
//// Indexing Items (Starts at zero)
//scala> evens(0)
//res0: Int = 2
//
//scala> evens(1)
//res1: Int = 4
//
//// Head and Tail
//scala> evens.head
//res3: Int = 2
//
//scala> evens.tail
//res4: List[Int] = List(4, 6, 8, 10, 12)
//
//scala> evens
//res5: List[Int] = List(2, 4, 6, 8, 10, 12)
//
//// Element Types
//
////Any
//scala> val my_list = List("a",2,true)
//my_list: List[Any] = List(a, 2, true)
//
//// Nested
//scala> val my_list = List(List(1,2,3),List(4,5,6))
//my_list: List[List[Int]] = List(List(1, 2, 3), List(4, 5, 6))
//
//// Double and Int
//scala> val my_list = List(1,2,3.0)
//my_list: List[Double] = List(1.0, 2.0, 3.0)
//
//// List of Tuple Pairs
//scala> val my_list = List(("a",1),("b",2),("c",3))
//my_list: List[(String, Int)] = List((a,1), (b,2), (c,3))
//
//// List Operations
//scala> val my_list = List(3,6,1,7,10)
//my_list: List[Int] = List(3, 6, 1, 7, 10)
//
//scala> my_list.sorted
//res7: List[Int] = List(1, 3, 6, 7, 10)
//
//scala> my_list.size
//res8: Int = 5
//
//scala> my_list.max
//res9: Int = 10
//
//scala> my_list.min
//res39: Int = 1
//
//scala> my_list.sum
//res40: Int = 27
//
//scala> my_list.product
//res41: Int = 1260
//
//// Using drop for slicing:
//scala> val x = List(1,2,3,4)
//x: List[Int] = List(1, 2, 3, 4)
//
//scala> x.drop(2)
//res3: List[Int] = List(3, 4)
//
//scala> x.takeRight(3)
//res4: List[Int] = List(2, 3, 4)
//// Use Tab to explore the other methods!
