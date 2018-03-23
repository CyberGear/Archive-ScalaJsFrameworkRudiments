package lt.markav.core.spaider

import org.scalajs.dom.Element
import lt.markav.core.framer.TagsImplicits._
import rx.Ctx
import rx.Rx
import rx.Var

import scalatags.JsDom.TypedTag
import scalatags.JsDom.all._

trait Widget {
  type Tag = TypedTag[Element]
  protected implicit val widget: Widget = this
  protected implicit val ctx: Ctx.Owner = Ctx.Owner.safe()

  val path: Path = Path()
  val widgets: List[Widget] = List.empty

  val webPath = Var(Path())
  val page: Frag = Rx { Router.route(webPath()) }

  def route(path: Path): Tag = {
    if (widgets.nonEmpty) webPath() = path
    contents
  }

  def contents: Tag = div(page)

}
