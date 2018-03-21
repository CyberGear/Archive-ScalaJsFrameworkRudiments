package lt.markav.core.spaider

import org.scalajs.dom.Element

import scalatags.JsDom.TypedTag

trait Widget {
  type Tag = TypedTag[Element]
  private[this] implicit val widget: Widget = this
  val path: Path = Path()
  val widgets: List[Widget] = List.empty

  def route(path: Path)(implicit context: Context): Tag =
    if (path.nonEmpty) Router.route(path)
    else contents

  def contents: Tag

}
