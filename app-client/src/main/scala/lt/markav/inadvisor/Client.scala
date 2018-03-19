package lt.markav.inadvisor

import lt.markav.core.PageIds
import lt.markav.core.spaider.SimpleRouter
import lt.markav.inadvisor.widget.HomePageWidget
import org.scalajs.dom.document.getElementById
import org.scalajs.dom.raw.Element

object Client extends App {

  private val mainContainer: Element = getElementById(PageIds.MainContainer)

  new SimpleRouter(mainContainer, List(
    new HomePageWidget
  ))

}