import demo.food.{domain => fooddomain}
import demo.wine.{domain => winedomain}
import domain.Logger

Logger.log("happy")

import fooddomain.api.{Jello => Jelly, _}
import winedomain.api._
IceCream("Vanilla")
Jelly("green")
// Will not compile
// Jello("red")
Wine("Foo", 1987, "Cab Sav")
