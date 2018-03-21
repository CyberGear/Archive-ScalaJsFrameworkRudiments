package lt.markav.inadvisor.widget

import lt.markav.core.spaider.Path
import lt.markav.core.spaider.Widget
import org.scalajs.dom.Node
import org.scalajs.dom.raw.Node

import scalatags.JsDom
import scalatags.JsDom.all._

class DeepWidget(override val subPath: Path) extends Widget {
  override def contents: JsDom.TypedTag[_ <: Node] = p("DeepWidget")
}
