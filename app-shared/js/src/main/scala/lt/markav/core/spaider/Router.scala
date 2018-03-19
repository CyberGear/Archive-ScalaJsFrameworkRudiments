package lt.markav.core.spaider

import org.scalajs.dom.raw.Node

object Router {

  def route(path: Path)(implicit context: Context): Node = {
    context.widgets.find(_.subPath == path.head) match {
      case Some(widget) => widget.route(path.tail)
      case None => new Widget {
        override def display: Node = {
          val element = org.scalajs.dom.document.createElement("h1")
          element.textContent = "404"
          element
        }
      }.display
    }
  }

}
