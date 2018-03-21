package lt.markav.inadvisor.widget

import lt.markav.core.spaider.Path
import lt.markav.core.spaider.Widget
import org.scalajs.dom.Node
import rx._

import scalatags.JsDom
import scalatags.JsDom.all._

class HomePageWidget(override val subPath: Path) extends Widget {
  override def contents: JsDom.TypedTag[_ <: Node] = p("HomePageWidget")
}
