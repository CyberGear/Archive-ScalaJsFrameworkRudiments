package lt.markav.core.spaider

import org.scalajs.dom.Node

import scalatags.JsDom.TypedTag
import scalatags.JsDom.all._

object Router {

  private val `404Widget` = new Widget {
    override def contents: TypedTag[_ <: Node] = h1("404")
  }

  def route(path: Path)(implicit context: Context): TypedTag[_ <: Node] =
    if (path.isEmpty) context.widgets.headOption.getOrElse(`404Widget`).contents
    else context.widgets.find(path startsWith _.subPath) match {
      case Some(widget) => widget.route(path after widget.subPath)
      case None => `404Widget`.contents
    }

}
