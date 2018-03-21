package lt.markav.inadvisor.widget

import lt.markav.core.spaider.Path
import lt.markav.core.spaider.Widget

import scalatags.JsDom.all._

class DeepWidget(override val path: Path) extends Widget {
  override def contents: Tag = p("DeepWidget")
}
