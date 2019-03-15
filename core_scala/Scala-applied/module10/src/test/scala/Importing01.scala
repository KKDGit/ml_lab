import domain.Logger.log
case class Importing01()

object Importing01 {
  def main(args: Array[String]): Unit = {
    log("hello world")

    import demo.food.domain.api._
    val iceCream = IceCream("Vanilla")
    println(iceCream)


    def thirdLetterOfDessert(dessert: IceCream): Char = {
      import dessert._
      import flavor._
      charAt(3)
    }
    println(thirdLetterOfDessert(iceCream))
  }

}


