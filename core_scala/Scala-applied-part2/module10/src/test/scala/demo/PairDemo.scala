package demo

import demo.food.domain.api.FindFood
import demo.wine.domain.api.PairWine
import domain.Logger

object PairDemo extends App {
  for {
    dessert <- FindFood.lookupFood("vanilla ice cream")
    wine <- PairWine.pairWineWithDessert(dessert)
  } Logger.log(s"$dessert pairs with $wine")
}
