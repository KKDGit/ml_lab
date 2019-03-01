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

class ImportantSystem(implicit allResources: AllResources) {
  val dbQuery = new DBQuery() // injected implicitly
  val wsCall = new WSCall()   // injected implicitly

  def doItAll(): Unit = {
    dbQuery.doIt()
    wsCall.doAnother()
  }
}

val system = new ImportantSystem()(StdAllResources)

system.doItAll()