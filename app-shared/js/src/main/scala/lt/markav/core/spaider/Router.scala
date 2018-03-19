package lt.markav.core.spaider

import org.scalajs.dom.raw.Node

import scalatags.JsDom.all._

object Router {

  private val `404Widget` = new Widget {
    override def display: Node = h1("404").render
  }

  def route(path: Path)(implicit context: Context): Node =
    if (path.isEmpty) context.widgets.headOption.getOrElse(`404Widget`).display
    else context.widgets.find(path startsWith _.subPath) match {
      case Some(widget) => widget.route(path after widget.subPath)
      case None => `404Widget`.display
    }

}
