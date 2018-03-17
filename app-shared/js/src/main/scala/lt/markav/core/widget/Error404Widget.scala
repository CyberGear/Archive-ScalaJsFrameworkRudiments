package lt.markav.core.widget

import lt.markav.core.widget.core.Widget
import org.scalajs.dom.raw.Node
import scalatags.JsDom.all._

class Error404Widget extends Widget {
  override def render: Node = div(h1("Page Not Found (404)"), br, p("Sorry :>")).render
}
