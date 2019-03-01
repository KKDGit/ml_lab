package demo.food.domain.api

trait Dessert

case class IceCream(flavor: String) extends Dessert
case class Jello(color: String) extends Dessert
