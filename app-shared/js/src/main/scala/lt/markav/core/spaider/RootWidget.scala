package lt.markav.core.spaider

import org.scalajs.dom.Element
import org.scalajs.dom.window

case class RootWidget(container: Element, override val widgets: List[Widget]) extends Widget {

  window.onhashchange = _ => webPath() = Path.current
  page.applyTo(container)

}