val xs = (1 to 10).toVector

xs.count(_ % 2 == 0)
xs.diff(List(1,2,3))
xs.intersect(Seq(-1, 0, 2, 5, 11))
xs ++ xs
(xs ++ xs).distinct
xs.head
xs.headOption
xs.tail
xs.drop(5).headOption
xs.drop(100).headOption
xs.take(5)
xs.take(100)
xs.find(_ > 4)
xs.takeWhile(_ < 6)
xs.dropWhile(_ < 5)
xs.exists(_ > 100)
xs.forall(_ < 11)
xs.filter(_ % 3 == 0)
xs.partition(_ % 3 == 0)
xs.combinations(3).take(3).toVector
xs.permutations.take(3).toVector
xs.find(_ % 4 == 0)
xs.find(_ > 100)
xs.foldLeft(0)(_ + _)
xs.reduce(_ + _)
xs.sum
xs.product
xs.groupBy(_ % 2)
xs.grouped(3).take(3).toVector
xs.sliding(3).take(3).toVector
xs.indexOf(4)
xs.indexOf(50)
xs.max
xs.min
xs.mkString
xs.mkString(",")
xs.mkString("[", ",", "]")
xs.isEmpty
Nil.isEmpty
xs.patch(2, List(200,300,400), 2)
xs.slice(3, 6)
xs.sortBy(_ % 4)
xs.map(_ * -1)
xs.map(_ * -1).sorted
xs.sortWith((x, y) => x > y)
xs.span(_ != 4)
xs.splitAt(5)
xs.startsWith(Seq(1,2,3))
xs.startsWith(Seq(1,2,4))
