package lt.markav.core

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.ContentTypes.`text/html(UTF-8)`
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import lt.markav.core.template.IndexHtml
import lt.markav.core.utils.PrettyHtml.HtmlFormatter
import upickle.default

class Server(implicit val system: ActorSystem,
             implicit val materializer: ActorMaterializer) {

  private var route: Map[String, Route] = Map(
    "/" -> get {
      (pathSingleSlash & redirectToTrailingSlashIfMissing(StatusCodes.TemporaryRedirect)) {
        complete {
          HttpEntity(`text/html(UTF-8)`, new IndexHtml().page.prettyHtml)
        }
      } ~ getFromResourceDirectory("")
    }
  )

  def registerApiRouter[A](base: String, router: => Router.Router): Unit = {
    route = route + (s"$base/..." -> post {
      path(base / Segments) { segment =>
        entity(as[String]) { e =>
          complete {
            router(autowire.Core.Request(segment, default.read[Map[String, String]](e)))
          }
        }
      }
    })
  }

  def start(port: Int): Unit = {
    Http().bindAndHandle(route.values.reduce(_ ~ _), "0.0.0.0", port = port)
    route.keySet.foreach(println)
    println(s"Started: http://localhost:$port")
  }

}