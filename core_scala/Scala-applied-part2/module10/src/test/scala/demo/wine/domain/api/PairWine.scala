package demo.wine.domain.api

import demo.food._
import domain.api.Dessert
import demo.wine.domain.internal.PairingDB

object PairWine {
  import _root_.domain.Logger.log
  def pairWineWithDessert(dessert: Dessert): Option[Wine] = {
    log(s"Attempting to pair $dessert")
    PairingDB.pairWineWithDessert(dessert)
  }
}
