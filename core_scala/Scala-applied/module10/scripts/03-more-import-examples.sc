import demo.food.{domain => fooddomain}
import demo.wine.{domain => winedomain}

import fooddomain.api.{Jello => Jelly, _}
import winedomain.api._

import domain.Logger.log
log("Happy Domain")

IceCream("Vanilla")
Jelly("green")
// Will not compile
// Jello("red")
Wine("Foo", 1987, "Cab Sav")