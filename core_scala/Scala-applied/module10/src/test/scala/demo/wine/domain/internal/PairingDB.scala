package demo.wine.domain.internal

import demo.food.domain.api.{Dessert, IceCream}
import demo.wine.domain.api.Wine

private[domain] object PairingDB {
  private[this] val winePairs =
    Map[Dessert, Wine](IceCream("Vanilla") -> Wine("Solis Old Vine Zin", 2014, "Zinfandel"))

  def pairWineWithDessert(dessert: Dessert): Option[Wine] = winePairs.get(dessert)
}
