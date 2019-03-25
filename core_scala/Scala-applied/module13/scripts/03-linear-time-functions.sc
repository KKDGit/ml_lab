val nums1 = (1 to 5).toList

nums1.last
nums1.init
//last and init are like opposite of head and tail

nums1.length

nums1.reverse

nums1(3)

nums1.drop(3)
nums1.take(3)

val nums2 = (6 to 10).toList

val allNums = nums1 ::: nums2

allNums.drop(50)
allNums.take(50)
//Doesn't throw any exception

allNums.drop(8).headOption
allNums.drop(20).headOption
//Better to use options

val updated = allNums.updated(4, 100)
//to update 4th index
