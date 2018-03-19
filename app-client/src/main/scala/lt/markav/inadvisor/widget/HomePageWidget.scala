package lt.markav.inadvisor.widget

import lt.markav.core.spaider.Path
import lt.markav.core.spaider.Widget
import org.scalajs.dom.raw.Node

import scalatags.JsDom.all._

class HomePageWidget extends Widget {
  override def subPath: Path = "hey"
  override def display: Node = h1("Hey ;)").render
}
