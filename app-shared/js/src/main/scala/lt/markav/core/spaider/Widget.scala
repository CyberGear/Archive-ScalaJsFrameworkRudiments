package lt.markav.core.spaider

import org.scalajs.dom.raw.Node

trait Widget {
  def subPath: Path = Path()

  def route(path: Path)(implicit context: Context): Node =
    if (path.notEmpty) Router.route(path)
    else display

  def display: Node

}
