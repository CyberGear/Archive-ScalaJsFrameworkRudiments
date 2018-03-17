package lt.markav.core

import lt.markav.core.widget.Error404Widget
import lt.markav.core.widget.core.Widget
import org.scalajs.dom
import org.scalajs.dom.raw.Element
import rx._

class WidgetRouter(container: Element,
                   override val routes: List[Route] = List.empty) extends Routable {

  private val `404` = Route("", new Error404Widget)
  val webUrl = Var(WebUrl.empty)
  dom.window.onhashchange = _ => {
    webUrl() = WebUrl()
  }

  private def route =
    if (webUrl.now.path.nonEmpty)
      routes.find(_.path == webUrl.now.path.head) match {
        case Some(route) => display(route.widget)
        case None => display(`404`.widget)
      }

  def withWidgets(routes: Route*): WidgetRouter = new WidgetRouter(container, routes.toList)

  def listen(): Unit = {
    display(routes.headOption.getOrElse(`404`).widget)
    webUrl.triggerLater {
      route
    }
  }

  private def display(widget: Widget) = {
    container.innerHTML = ""
    container.appendChild(widget.render)
  }

}
