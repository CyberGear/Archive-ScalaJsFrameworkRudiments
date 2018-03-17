package lt.markav.core.widget

import lt.markav.core.widget.core.Widget
import org.scalajs.dom.raw.Node
import scalatags.JsDom.all._

class HomePageWidget extends Widget {
  override def render: Node = div(h1("demo Home Page"), ";)").render
}
