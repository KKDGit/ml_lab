val conversionKilosToPounds = 2.20462262185
val conversionCMToFeet = 30.48
class Person(val name: String,
             var weightInPounds: Double,
             var heightInCMs: Double) {

  def weightInKilos: Double =
    weightInPounds/conversionKilosToPounds
  
  def weightInKilos_=(newWeight: Double): Unit = {
  	weightInPounds = newWeight * conversionKilosToPounds
  }

  def heightInFeet: Double =
    heightInCMs/conversionCMToFeet

  def heightInFeet_=(newHeight: Double): Unit = {
    heightInCMs = newHeight * conversionCMToFeet
  }
}

val fred = new Person(
  "Fred",
  120,
  170)

fred.weightInPounds

fred.weightInKilos

fred.weightInKilos = 60
fred.weightInKilos_=(60)

fred.weightInKilos

fred.weightInPounds

fred.weightInPounds = 125

fred.weightInPounds

fred.weightInKilos

fred.heightInCMs
fred.heightInFeet

fred.heightInCMs = 180
fred.heightInFeet

fred.heightInFeet = 5.5
fred.heightInCMs
fred.heightInFeet

fred.heightInFeet = 5.413385826771654
fred.heightInCMs
fred.heightInFeet