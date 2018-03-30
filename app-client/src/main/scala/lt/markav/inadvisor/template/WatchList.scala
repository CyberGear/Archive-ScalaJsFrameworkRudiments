package lt.markav.inadvisor.template
import lt.markav.core.style.Mdl
import org.scalajs.dom.html.Element

import scalatags.JsDom
import scalatags.JsDom.all._

case class WatchList(items: WatchListItem*) extends TagsGroup {
  override def generate: JsDom.TypedTag[Element] = ul(Mdl.list,
    items.map(_.generate)
  )
}
