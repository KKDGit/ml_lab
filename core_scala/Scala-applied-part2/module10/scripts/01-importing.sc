import domain.Logger.log

log("hello world")

import demo.food.domain.api._

val iceCream = IceCream("Vanilla")

def thirdLetterOfDessert(dessert: IceCream): Char = {
  import dessert._
  import flavor._

  charAt(3)
}

thirdLetterOfDessert(iceCream)
