val nums1 = (1 to 5).toList

nums1.last
nums1.init
nums1.length

nums1.reverse

nums1(3)

nums1.drop(3)
nums1.take(3)

val nums2 = (6 to 10).toList

val allNums = nums1 ::: nums2

allNums.drop(50)
allNums.take(50)

allNums.drop(8).headOption
allNums.drop(20).headOption

val updated = allNums.updated(4, 100)
