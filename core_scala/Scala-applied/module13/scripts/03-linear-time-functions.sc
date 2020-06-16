val nums1 = (1 to 5).toList
// operations depend on length of List
nums1.last
nums1.init
//last and init are like opposite of head and tail
nums1.length
nums1.reverse

// operations depend on parameter passed
val nums2 = (6 to 10).toList
val allNums = nums1 ::: nums2

nums1(3)
nums1.drop(3)
nums1.take(3)
nums1.takeRight(2)

//allNums(50) -> throws exception
allNums.drop(50)
allNums.take(50)
//Doesn't throw any exception

allNums.drop(8).headOption
allNums.drop(20).headOption
Nil.headOption
//Better to use options

val updated = allNums.updated(4, 100)
//to update 4th index

