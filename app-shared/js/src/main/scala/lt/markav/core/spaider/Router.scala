package lt.markav.core.spaider

import lt.markav.core.util.Logging
import org.scalajs.dom.Element

import scala.annotation.tailrec
import scalatags.JsDom.TypedTag
import scalatags.JsDom.all._

object Router extends Logging {

  private val `404Widget` = new Widget {
    override def contents: Tag = h1("404")
  }

  def route(path: Path)(implicit widget: Widget): TypedTag[Element] =
    if (path.isEmpty) widget.widgets.headOption.getOrElse(`404Widget`).contents
    else {
      @tailrec
      def loop(p: Path, wid: Option[Widget]): Option[Widget] =
        if (p.isEmpty || wid.isDefined) wid
        else loop(p `../`, widget.widgets.find(_.path == path))

      loop(path, None) match {
        case Some(wid) => wid.route(path after wid.path)
        case None => `404Widget`.contents
      }
    }

}
