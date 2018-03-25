package lt.markav.core.spaider

import lt.markav.core.util.Logging
import org.scalajs.dom.Element

import scala.annotation.tailrec
import scala.scalajs.js.JavaScriptException
import scalatags.JsDom.TypedTag
import scalatags.JsDom.all._

object Router extends Logging {

  case object `404Widget` extends Widget {
    override def contents: Tag = h1("404")
  }

  def route(path: Path)(implicit context: Widget): TypedTag[Element] = {
    validate(context.widgets)
    val (empty, filled) = context.widgets.partition(_.path.isEmpty)
    if (path.isEmpty) context.widgets.headOption.getOrElse(`404Widget`).contents
    else {
      val direct = findWidget(path, filled)
      val throughEmpty = empty.flatMap(e => findWidget(path, e.widgets))

      direct match {
        case Some(widget) => widget.route(path after widget.path)
        case None => throughEmpty match {
          case widget :: Nil => widget.route(path after widget.path)
          case Nil => `404Widget`.contents
          case _ :: _ => throw JavaScriptException(
            s"Duplicated endpoints in ${context.path} empty paths for $path"
          )
        }
      }
    }
  }

  private def validate(widgets: List[Widget])(implicit context: Widget): Unit = {
    val (empty, filled) = widgets.partition(_.path.isEmpty)
    val duplicated = filled.groupBy(_.path).filter(_._2.lengthCompare(1) > 0)
    if (duplicated.nonEmpty) throw JavaScriptException(
      s"Duplicated paths in ${context.path}: ${duplicated.keySet.mkString("[", ", ", "]")}"
    )
  }

  private def findWidget(path: Path, widgets: List[Widget]): Option[Widget] = {
    @tailrec
    def loop(p: Path, wid: Option[Widget] = None): Option[Widget] =
      if (p.isEmpty || wid.isDefined) wid
      else loop(p `../`, widgets.find(_.path == p))

    loop(path)
  }

}
