package demo.food.domain.internal

import demo.food.domain.api.Dessert

import scala.collection.mutable

private[domain] class FoodDB {
  private[this] val desserts = mutable.Map.empty[String, Dessert]

  private[domain] def addDessert(name: String, dessert: Dessert): Unit =
    desserts.put(name, dessert)

  def lookupDessert(name: String): Option[Dessert] = desserts.get(name)
}
