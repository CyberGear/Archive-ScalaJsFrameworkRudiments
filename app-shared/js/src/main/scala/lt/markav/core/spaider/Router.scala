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
    if (path.isEmpty) {
      val widget = context.widgets.headOption.getOrElse(`404Widget`)
      println(s"[info] Routing to '$path' goes to '${widget.getClass.getSimpleName}'")
      widget.contents
    }
    else {
      @tailrec
      def loop(p: Path, wid: Option[Widget]): Option[Widget] =
        if (p.isEmpty || wid.isDefined) wid
        else loop(p `../`, context.widgets.find(_.path == p))

      loop(path, None) match {
        case Some(widget) =>
          println(s"[info] Routing to '${path after widget.path}'")
          widget.route(path after widget.path)
        case None =>
          println(s"[info] Routing to '404'")
          `404Widget`.contents
      }
    }
  }

}
