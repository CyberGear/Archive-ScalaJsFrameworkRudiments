package lt.markav.core.widget.core

import lt.markav.core.{Routable, Route}
import org.scalajs.dom.raw.Node

abstract class Widget(override val routes: List[Route] = List.empty) extends Routable {
  def render: Node
}
