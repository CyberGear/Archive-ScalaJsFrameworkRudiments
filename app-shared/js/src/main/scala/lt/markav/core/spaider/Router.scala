package lt.markav.core.spaider

import lt.markav.core.util.Logging
import org.scalajs.dom.Element

import scala.annotation.tailrec
import scalatags.JsDom.TypedTag
import scalatags.JsDom.all._

object Router extends Logging {

  case object `404Widget` extends Widget {
    override def contents: Tag = h1("404")
  }

  def route(path: Path)(implicit context: Widget): TypedTag[Element] = {
    if (path.isEmpty) context.widgets.headOption.getOrElse(`404Widget`).contents
    else {
      @tailrec
      def loop(p: Path, wid: Option[Widget] = None): Option[Widget] =
        if (p.isEmpty || wid.isDefined) wid
        else loop(p `../`, context.widgets.find(_.path == p))

      loop(path) match {
        case Some(widget) => widget.route(path after widget.path)
        case None => `404Widget`.contents
      }
    }
  }

}
