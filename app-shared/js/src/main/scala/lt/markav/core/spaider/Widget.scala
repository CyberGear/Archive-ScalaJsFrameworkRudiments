package lt.markav.core.spaider

import org.scalajs.dom.Node

import scalatags.JsDom.TypedTag

trait Widget {
  val subPath: Path = Path()

  def route(path: Path)(implicit context: Context): TypedTag[_ <: Node] =
    if (path.nonEmpty) Router.route(path)
    else contents

  def contents: TypedTag[_ <: Node]

}
