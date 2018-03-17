package lt.markav.inadvisor

import lt.markav.core.PageIds
import lt.markav.core.Route
import lt.markav.core.WidgetRouter
import lt.markav.core.widget.HomePageWidget
import org.scalajs.dom.document.getElementById
import org.scalajs.dom.raw.Element

object Client extends App {

  private val mainContainer: Element = getElementById(PageIds.MainContainer)

  new WidgetRouter(mainContainer)
    .withWidgets(
      Route("home", new HomePageWidget())
    )
    .listen()

}