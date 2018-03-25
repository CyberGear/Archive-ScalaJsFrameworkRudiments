package lt.markav.inadvisor.widget

import lt.markav.core.spaider.Path
import lt.markav.core.spaider.Widget

import scalatags.JsDom.all._

case class AlarmsWidget(override val path: Path = "alarms") extends Widget {
  override def contents: Tag = span("alarms")
}