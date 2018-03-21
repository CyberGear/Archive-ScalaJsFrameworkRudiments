package lt.markav.core.spaider

import org.scalajs.dom.Element
import org.scalajs.dom.Node

import scalatags.JsDom.TypedTag
import scalatags.JsDom.all._

object Router {

  private val `404Widget` = new Widget {
    override def contents: Tag = h1("404")
  }

  def route(path: Path)(implicit widget: Widget): TypedTag[Element] =
    if (path.isEmpty) widget.widgets.headOption.getOrElse(`404Widget`).contents
    else widget.widgets.sortBy(_.path.depth).find(_.path startsWith path) match {
      case Some(wid) => wid.route(wid.path after path)
      case None => `404Widget`.contents
    }

}
