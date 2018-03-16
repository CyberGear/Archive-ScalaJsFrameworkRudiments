package lt.markav.inadvisor

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import lt.markav.core.{Router, Server}
import lt.markav.core.api.{AccountApi, AlarmsApi}

import scala.concurrent.ExecutionContext.Implicits.global

object Launcher extends App {

  implicit val system: ActorSystem = ActorSystem.create()
  implicit val materializer: ActorMaterializer = ActorMaterializer.create(system)

  private val server = new Server()

  server.registerApiRouter("AlarmsApi", Router.route[AlarmsApi](new AlarmsApiImpl))
  server.registerApiRouter("AccountApi", Router.route[AccountApi](Impl))

  server.start(8080)
}

object Impl extends AccountApi{
  override def label(): String = s"Second Api"
}

