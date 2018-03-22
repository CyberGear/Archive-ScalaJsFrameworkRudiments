package lt.markav.inadvisor.widget

import lt.markav.core.spaider.Widget

import scalatags.JsDom.all._

class MainFrameWidget(override val widgets: List[Widget]) extends Widget {

  override def contents: Tag = div(p("MainFrameWidget"), page)

}
