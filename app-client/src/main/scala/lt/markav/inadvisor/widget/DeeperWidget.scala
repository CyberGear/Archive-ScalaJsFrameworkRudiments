package lt.markav.inadvisor.widget

import lt.markav.core.spaider.Path
import lt.markav.core.spaider.Widget
import org.scalajs.dom.Element

import scalatags.JsDom.TypedTag
import scalatags.JsDom.all._

class DeeperWidget(override val path: Path) extends Widget {
  override def contents: Tag = p("DeeperWidget")
}
