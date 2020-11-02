trait DBConfig {
  val dbName: String
}

trait WSConfig {
  val wsName: String
}

trait StdDBConfig extends DBConfig {
  override val dbName: String = "SomeDB"
}

object StdDBConfig extends StdDBConfig
//selfless trait pattern

trait StdWSConfig extends WSConfig {
  override val wsName: String = "SomeWS"
}

object StdWSConfig extends StdWSConfig


class DBQuery(implicit dBConfig: DBConfig) {
  def doIt(): Unit = {
    println(s"Doing stuff on ${dBConfig.dbName}")
  }
}

class WSCall(implicit wSConfig: WSConfig) {
  def doAnother(): Unit = {
    println(s"Doing stuff with service ${wSConfig}")
  }
}

trait AllResources extends DBConfig with WSConfig

object StdAllResources extends AllResources with StdWSConfig with StdDBConfig
//we can use implicit here and not to pass to
//val system = new ImportantSystem() at 49

class ImportantSystem(implicit allResources: AllResources) {
  //Instead of AllResources if DBConfig or WSConfig is given
  //it will not work
  val dbQuery = new DBQuery() // injected implicitly
  val wsCall = new WSCall()   // injected implicitly

  def doItAll(): Unit = {
    dbQuery.doIt()
    wsCall.doAnother()
  }
}

val system = new ImportantSystem()(StdAllResources)

system.doItAll()